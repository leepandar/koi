package com.koi.iam.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.google.common.collect.Maps;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.entity.Entity;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.dynamic.DynamicDataSourceHandler;
import com.koi.common.db.dynamic.core.EventAction;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.db.properties.DatabaseProperties;
import com.koi.common.db.properties.MultiTenantType;
import com.koi.common.db.utils.TenantHelper;
import com.koi.common.security.utils.PasswordEncoderHelper;
import com.koi.iam.system.domain.entity.*;
import com.koi.iam.system.mapper.*;
import com.koi.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.tenant.domain.entity.TenantDict;
import com.koi.iam.tenant.domain.entity.TenantDictItem;
import com.koi.iam.tenant.domain.entity.TenantSetting;
import com.koi.iam.tenant.mapper.TenantDictItemMapper;
import com.koi.iam.tenant.mapper.TenantDictMapper;
import com.koi.iam.tenant.mapper.TenantMapper;
import com.koi.iam.tenant.mapper.TenantSettingMapper;
import com.koi.iam.base.domain.entity.AreaEntity;
import com.koi.iam.base.domain.entity.SysDict;
import com.koi.iam.base.domain.entity.SysDictItem;
import com.koi.iam.base.mapper.AreaMapper;
import com.koi.iam.base.mapper.SysDictItemMapper;
import com.koi.iam.base.mapper.SysDictMapper;
import com.koi.iam.tenant.domain.dto.req.TenantConfigReq;
import com.koi.iam.tenant.domain.dto.req.TenantSaveReq;
import com.koi.iam.tenant.domain.dto.req.TenantSettingReq;
import com.koi.iam.tenant.service.DbSettingService;
import com.koi.iam.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantServiceImpl extends SuperServiceImpl<TenantMapper, Tenant> implements TenantService {

    private final AuthenticationContext context;
    private final TenantSettingMapper tenantSettingMapper;
    private final AreaMapper areaMapper;
    private final RoleMapper roleMapper;
    private final RoleResMapper roleResMapper;
    private final UserRoleMapper userRoleMapper;
    private final DbSettingService dbSettingService;
    private final DatabaseProperties properties;
    private final UserMapper userMapper;
    private final OrgMapper orgMapper;
    private final SysDictMapper dictMapper;
    private final SysDictItemMapper dictItemMapper;
    private final TenantDictMapper tenantDictMapper;
    private final TenantDictItemMapper tenantDictItemMapper;

    /**
     * 保存租户
     *
     * @param req 租户信息
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(TenantSaveReq req) {
        // 随机生成租户编码
        // String tenantCode = RandomUtil.randomNumbers(4);
        Long nameCount = this.baseMapper.selectCount(Tenant::getName, req.getName());
        if (nameCount > 0) {
            throw CheckedException.badRequest("租户名称重复");
        }
        Long codeCount = this.baseMapper.selectCount(Tenant::getCode, req.getCode());
        if (codeCount > 0) {
            throw CheckedException.badRequest("租户编码重复");
        }
        Tenant tenant = BeanUtil.toBean(req, Tenant.class);
        tenant.setProvinceName(getNameById(tenant.getProvinceId()));
        tenant.setCityName(getNameById(tenant.getCityId()));
        tenant.setDistrictName(getNameById(tenant.getDistrictId()));
        this.baseMapper.insert(tenant);
    }

    /**
     * 修改租户
     *
     * @param id  id
     * @param req 租户信息
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, TenantSaveReq req) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("租户不存在"));
        Long nameCount = this.baseMapper.selectCount(Wraps.<Tenant>lbQ().eq(Tenant::getName, req.getName()).ne(Tenant::getId, id));
        if (nameCount > 0) {
            throw CheckedException.badRequest("租户名称重复");
        }
        Long codeCount = this.baseMapper.selectCount(Wraps.<Tenant>lbQ().eq(Tenant::getCode, req.getCode()).ne(Tenant::getId, id));
        if (codeCount > 0) {
            throw CheckedException.badRequest("租户编码重复");
        }
        Tenant bean = BeanUtilPlus.toBean(id, req, Tenant.class);
        bean.setProvinceName(getNameById(tenant.getProvinceId()));
        bean.setCityName(getNameById(tenant.getCityId()));
        bean.setDistrictName(getNameById(tenant.getDistrictId()));
        this.baseMapper.updateById(bean);
    }

    /**
     * 租户配置
     *
     * @param tenantId tenantId
     * @param req      租户配置
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void tenantConfig(Long tenantId, TenantConfigReq req) {
        // final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(tenantId))
        // .orElseThrow(() -> CheckedException.notFound("租户不存在"));
        // if (!tenant.getStatus()) {
        // throw CheckedException.badRequest("租户未启用");
        // }
        // if (StringUtils.equals(tenant.getCode(), properties.getMultiTenant().getSuperTenantCode())) {
        // throw CheckedException.badRequest("超级租户,禁止操作");
        // }
        // TenantConfig tenantConfig = this.tenantConfigMapper.selectOne(TenantConfig::getTenantId, tenantId);
        // if (tenantConfig == null) {
        // tenantConfigMapper.insert(TenantConfig.builder().tenantId(tenantId).datasourceId(req.getDatasourceId()).build());
        // } else {
        // tenantConfigMapper.updateById(TenantConfig.builder().id(tenantConfig.getId()).datasourceId(req.getDatasourceId()).build());
        // }
        // // 先创建
        // dynamicDatasourceService.publishEvent(EventAction.INIT, tenant.getId());
        // if (!req.isLazy()) {
        // initSqlScript(tenantId);
        // }
    }

    /**
     * 初始化SQL脚本
     *
     * @param id id
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void initSqlScript(Long id) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("租户信息不存在"));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户未启用");
        }
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (isSuperTenant(tenant, multiTenant)) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }

        if (multiTenant.getType() == MultiTenantType.COLUMN) {
            initColumnTypeTenant(tenant);
        } else if (multiTenant.getType() == MultiTenantType.DATASOURCE) {
            initDatasourceTypeTenant(tenant);
        }
    }

    /**
     * 字典刷新
     *
     * @param tenantId tenantId
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void refreshTenantDict(Long tenantId) {
        validTenant(tenantId);
        // 查询超管 所有字典数据
        List<SysDict> dictList = TenantHelper.executeWithMaster(() -> dictMapper.selectList(SysDict::getType, 1));
        if (CollUtil.isEmpty(dictList)) {
            log.warn("未查询到有效的数据字典");
            return;
        }
        List<TenantDict> dictTypeList = dictList.stream().map(x -> {
            TenantDict dict = BeanUtil.toBean(x, TenantDict.class);
            dict.setId(null);
            dict.setLastModifiedTime(Instant.now());
            dict.setLastModifiedBy(context.userId());
            dict.setLastModifiedName(context.nickName());
            return dict;
        }).toList();
        List<Long> dictIdList = dictList.stream().map(Entity::getId).toList();
        List<TenantDictItem> dictDataList = TenantHelper.executeWithMaster(() -> dictItemMapper.selectList(Wraps.<SysDictItem>lbQ().in(SysDictItem::getDictId, dictIdList)))
                .stream()
                .map(x -> {
                    TenantDictItem item = BeanUtil.toBean(x, TenantDictItem.class);
                    item.setId(null);
                    item.setTenantId(tenantId);
                    item.setLastModifiedTime(Instant.now());
                    item.setLastModifiedBy(context.userId());
                    item.setLastModifiedName(context.nickName());
                    return item;
                }).toList();
        // 理论上如果是管理员刷新租户字典那么需要给租户的数据给删除然后重新添加
        this.tenantDictMapper.delete(Wraps.<TenantDict>lbQ().eq(TenantDict::getTenantId, tenantId));
        this.tenantDictItemMapper.delete(Wraps.<TenantDictItem>lbQ().eq(TenantDictItem::getTenantId, tenantId));
        // 将新数据写入到租户字典表中
        this.tenantDictMapper.insertBatchSomeColumn(dictTypeList);
        this.tenantDictItemMapper.insertBatchSomeColumn(dictDataList);
    }

    /**
     * 租户设置信息
     *
     * @param tenantId 租户ID
     * @return
     */
    @Override
    public TenantSettingResp settingInfo(Long tenantId) {
        TenantSetting setting = this.tenantSettingMapper.selectOne(TenantSetting::getTenantId, tenantId);
        return BeanUtil.toBean(setting, TenantSettingResp.class);
    }

    /**
     * 保存租户设置
     *
     * @param tenantId 租户ID
     * @param req      设置信息
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void saveSetting(Long tenantId, TenantSettingReq req) {
        validTenant(tenantId);
        String siteUrl = req.getSiteUrl();
        if (StrUtil.isNotBlank(siteUrl)) {
            Long count = this.tenantSettingMapper.selectCount(Wraps.<TenantSetting>lbQ()
                    .ne(TenantSetting::getTenantId, tenantId).eq(TenantSetting::getSiteUrl, req.getSiteUrl()));
            if (count != null && count > 0) {
                throw CheckedException.badRequest("该租户站点已存在");
            }
        }
        TenantSetting setting = this.tenantSettingMapper.selectOne(TenantSetting::getTenantId, tenantId);
        var bean = BeanUtil.toBean(req, TenantSetting.class);
        bean.setTenantId(tenantId);
        if (setting == null) {
            this.tenantSettingMapper.insert(bean);
        } else {
            bean.setId(setting.getId());
            this.tenantSettingMapper.updateById(bean);
        }
        dbSettingService.publishEvent(EventAction.INIT, tenantId);
    }

    private String getNameById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        final AreaEntity areaEntity = areaMapper.selectById(id);
        if (Objects.isNull(areaEntity)) {
            return null;
        }
        return areaEntity.getName();
    }

    private boolean isSuperTenant(Tenant tenant, DatabaseProperties.MultiTenant multiTenant) {
        return StringUtils.equals(tenant.getCode(), multiTenant.getSuperTenantCode());
    }

    private Role selectTenantAdminRole() {
        return Optional.ofNullable(roleMapper.selectOne(Wraps.<Role>lbQ()
                        .eq(Role::getCode, "TENANT-ADMIN")))
                .orElseThrow(() -> CheckedException.notFound("内置租户管理员角色不存在"));
    }

    private void clearTenantData(Tenant tenant, List<User> users) {
        final List<Long> userIdList = users.stream().map(User::getId).distinct().collect(Collectors.toList());
        log.warn("开始清除租户 - {} 的系统数据,危险动作", tenant.getName());
        if (CollUtil.isNotEmpty(userIdList)) {
            // 等于0全表会删。
            this.userRoleMapper.delete(Wraps.<UserRole>lbQ().in(UserRole::getUserId, userIdList));
        }
        this.userMapper.deleteByTenantId(tenant.getId());
        this.roleMapper.deleteByTenantId(tenant.getId());
        this.orgMapper.deleteByTenantId(tenant.getId());
    }


    private void initColumnTypeTenant(Tenant tenant) {
        final Role role = selectTenantAdminRole();
        final List<User> users = this.userMapper.selectByTenantId(tenant.getId());
        if (CollUtil.isNotEmpty(users)) {
            clearTenantData(tenant, users);
        }
        initializeTenantData(tenant, role);
    }

    private void initDatasourceTypeTenant(Tenant tenant) {
        DynamicDataSourceHandler dynamicDataSourceHandler = SpringUtil.getBean(DynamicDataSourceHandler.class);
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("tenant_id", tenant.getId());
        variables.put("tenant_name", tenant.getName());
        dynamicDataSourceHandler.initSqlScript(tenant.getCode(), variables);
        final Role role = selectTenantAdminRole();
        List<RoleRes> list = this.roleResMapper.selectList(RoleRes::getRoleId, role.getId());
        TenantHelper.executeWithTenantDb(tenant.getCode(), () -> {
            final List<User> users = this.userMapper.selectByTenantId(tenant.getId());
            if (CollUtil.isNotEmpty(users)) {
                clearTenantData(tenant, users);
            }
            initializeTenantData(tenant, role);
            if (CollUtil.isNotEmpty(list)) {
                this.roleResMapper.insertBatchSomeColumn(list);
            }
            return null;
        });
    }


    private void initializeTenantData(Tenant tenant, Role role) {
        log.warn("开始初始化租户 - {} 的系统数据,危险动作", tenant.getName());
        Org org = new Org();
        org.setLabel(tenant.getName());
        org.setTenantId(tenant.getId());
        org.setStatus(true);
        org.setDescription("不可删除不可修改");
        org.setParentId(0L);
        org.setSequence(0);
        this.orgMapper.insert(org);

        User user = new User();
        String contactPhone = tenant.getContactPhone();
        user.setUsername(contactPhone);
        user.setEmail(tenant.getEmail());
        user.setAvatar(tenant.getLogo());
        user.setPassword(PasswordEncoderHelper.encode("123456"));
        user.setTenantId(tenant.getId());
        user.setNickName(tenant.getContactPerson());
        user.setMobile(contactPhone);
        user.setStatus(true);
        this.userMapper.insert(user);
        this.userRoleMapper.insert(UserRole.builder().userId(user.getId()).roleId(role.getId()).build());
    }

    void validTenant(Long tenantId) {
        final Tenant tenant = Optional.ofNullable(this.baseMapper.selectById(tenantId)).orElseThrow(() -> CheckedException.notFound("租户不存在"));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户未启用");
        }
        if (StringUtils.equals(tenant.getCode(), properties.getMultiTenant().getSuperTenantCode())) {
            throw CheckedException.badRequest("超级租户,禁止操作");
        }
    }
}
