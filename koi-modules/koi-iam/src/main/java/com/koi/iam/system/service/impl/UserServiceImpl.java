package com.koi.iam.system.service.impl;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.annotation.remote.RemoteResult;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.datascope.handler.DataPermissionRule;
import com.koi.common.db.mybatisplus.datascope.service.DataScopeService;
import com.koi.common.db.mybatisplus.datascope.util.DataPermissionUtils;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.db.mybatisplus.wrap.query.LbqWrapper;
import com.koi.common.db.properties.DatabaseProperties;
import com.koi.common.db.utils.TenantHelper;
import com.koi.common.db.properties.MultiTenantType;
import com.koi.common.log.core.annotation.DiffLog;
import com.koi.common.log.core.context.DiffLogContext;
import com.koi.common.security.configuration.SecurityExtProperties;
import com.koi.common.security.domain.UserInfoDetails;
import com.koi.common.security.utils.PasswordEncoderHelper;
import com.koi.iam.system.mapper.ResourceMapper;
import com.koi.iam.system.mapper.RoleMapper;
import com.koi.iam.system.mapper.UserMapper;
import com.koi.iam.system.mapper.UserRoleMapper;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.tenant.mapper.TenantMapper;
import com.koi.iam.auth.domain.entity.LoginLog;
import com.koi.iam.auth.support.domain.UserTenantAuthentication;
import com.koi.iam.base.domain.dto.req.ChangeUserInfoReq;
import com.koi.iam.system.domain.dto.req.UserOnlinePageReq;
import com.koi.iam.system.domain.dto.req.UserPageReq;
import com.koi.iam.system.domain.dto.req.UserSaveReq;
import com.koi.iam.system.domain.dto.req.UserUpdateReq;
import com.koi.iam.system.domain.dto.resp.UserResp;
import com.koi.iam.system.domain.entity.Resource;
import com.koi.iam.system.domain.entity.Role;
import com.koi.iam.system.domain.entity.User;
import com.koi.iam.system.domain.entity.UserRole;
import com.koi.iam.system.service.OrgService;
import com.koi.iam.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lida
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {

    private final UserRoleMapper userRoleMapper;
    private final AuthenticationContext context;
    private final OrgService orgService;
    private final ResourceMapper resourceMapper;
    private final RoleMapper roleMapper;
    private final TenantMapper tenantMapper;
    private final DataScopeService dataScopeService;
    private final SaTokenDao saTokenDao;
    private final SecurityExtProperties extProperties;
    private final DatabaseProperties databaseProperties;
    private static final List<String> ADMIN_ROLE = List.of("PLATFORM-ADMIN", "TENANT-ADMIN");

    @Override
    public void create(UserSaveReq req) {
        final long count = super.count(Wraps.<User>lbQ().eq(User::getUsername, req.getUsername()));
        if (count > 0) {
            throw CheckedException.badRequest("账号已存在");
        }
        var bean = BeanUtil.toBean(req, User.class);
        bean.setPassword(PasswordEncoderHelper.encode(req.getPassword()));
        bean.setTenantId(context.tenantId());
        this.baseMapper.insert(bean);
    }

    @Override
    @DiffLog(group = "用户管理", tag = "编辑用户", businessKey = "{{#id}}",
            success = "更新用户信息 {_DIFF{#_newObj}}",
            fail = "更新用户信息异常 {{#id}} 需要更新的数据 {{#req}}")
    public void modify(Long id, UserUpdateReq req) {
        User oldVal = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        User newVal = BeanUtilPlus.toBean(id, req, User.class);
        DiffLogContext.putDiffItem(oldVal, newVal);
        this.baseMapper.updateById(newVal);
    }

    @Override
    public List<User> list() {
        return baseMapper.list();
    }

    @Override
    @RemoteResult
    public IPage<UserResp> pageList(UserPageReq req) {
        return DataPermissionUtils.executeWithDataPermissionRule(DataPermissionRule.builder()
                        .columns(List.of(new DataPermissionRule.Column()))
                        .build(),
                () -> baseMapper.selectPage(req.buildPage(), Wraps.<User>lbQ()
                        .eq(User::getTenantId, context.tenantId())
                        .like(User::getUsername, req.getUsername())
                        .like(User::getNickName, req.getNickName())
                        .like(User::getEmail, req.getEmail())
                        .eq(User::getStatus, req.getStatus())
                        .eq(User::getEducation, req.getEducation())
                        .eq(User::getDeleted, false)
                        .eq(User::getSex, req.getSex())
                        .in(User::getOrgId, orgService.getFullTreeIdPath(req.getOrgId()))
                        .eq(User::getMobile, req.getMobile())).convert(x -> BeanUtil.toBean(x, UserResp.class)));
    }

    @Override
    public void changePassword(Long userId, String orgPassword, String newPassword) {
        final User user = Optional.ofNullable(this.baseMapper.selectById(userId)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        // if (!passwordEncoder.matches(orgPassword, user.getPassword())) {
        // throw CheckedException.badRequest("原始密码错误");
        // }
        // this.baseMapper.updateById(User.builder().id(userId).password(passwordEncoder.encode(newPassword)).build());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        final User user = Optional.ofNullable(getById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (user.getReadonly()) {
            throw CheckedException.badRequest("内置用户不允许删除");
        }
        baseMapper.deleteById(id);
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getUserId, id));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void changeInfo(ChangeUserInfoReq req) {
        final Long userId = context.userId();
        User bean = User.builder().id(userId).email(req.getEmail()).mobile(req.getMobile())
                .nickName(req.getNickName()).birthday(req.getBirthday())
                .description(req.getDescription()).build();
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void resetPassword(Long id) {
        final User user = Optional.ofNullable(getById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (user.getReadonly()) {
            throw CheckedException.badRequest("禁止重置内置用户密码");
        }
        String prefix = RandomUtil.randomString(4);
        String suffix = RandomUtil.randomNumbers(6);
        String password = prefix + suffix;
        String encodePassword = PasswordEncoderHelper.encode(password);
        user.setPassword(encodePassword);
        log.info("随机生成的新密码 - {} - {}", password, encodePassword);
        this.baseMapper.updateById(User.builder().id(id).password(encodePassword).build());
    }

    @Override
    public UserInfoDetails userinfo(Long userId) {
        // TODO 后续通过注解和 API 方式动态控制，MP3.5.10 支持 api.execute 方式
        return TenantHelper.withIgnoreStrategy(() -> {
            final User user = Optional.ofNullable(this.baseMapper.selectById(userId))
                    .orElseThrow(() -> CheckedException.notFound("用户信息不存在"));
            Tenant tenant = this.tenantMapper.selectById(user.getTenantId());
            return userinfo(UserTenantAuthentication.builder().user(user).tenant(tenant).build());
        });
    }

    @Override
    public UserInfoDetails userinfo(UserTenantAuthentication authentication) {
        User user = authentication.getUser();
        Tenant tenant = authentication.getTenant();
        return TenantHelper.executeWithTenantDb(tenant.getCode(), () -> {
            final UserInfoDetails info = new UserInfoDetails();
            info.setTenantCode(tenant.getCode());
            info.setTenantName(tenant.getName());
            info.setTenantId(user.getTenantId());
            info.setUserId(user.getId());
            info.setUsername(user.getUsername());
            info.setNickName(user.getNickName());
            info.setMobile(user.getMobile());
            info.setEmail(user.getEmail());
            info.setDescription(user.getDescription());
            info.setBirthday(user.getBirthday());
            info.setEnabled(user.getStatus());
            info.setAvatar(user.getAvatar());
            info.setPassword(user.getPassword());
            final List<Role> roles = this.roleMapper.findRoleByUserId(user.getId());
            info.setRoles(roles.stream().map(Role::getCode).toList());
            setFuncPermissions(info);
            // 为了减少一次数据库查询,所以用了这个不规范写法
            info.setDataPermission(dataScopeService.getDataScopeById(user.getId(), user.getOrgId()));
            return info;
        });
    }

    public void setFuncPermissions(UserInfoDetails info) {
        Collection<String> roles = info.getRoles();
        Long userId = info.getUserId();
        // 需要考虑下租户订阅产品后,租户管理员应该自动读取相关权限
        // 同时需要考虑到期的产品如何回收权限
        DatabaseProperties.MultiTenant multiTenant = databaseProperties.getMultiTenant();
        boolean isAdmin = CollUtil.containsAny(roles, ADMIN_ROLE);
        if (isAdmin || multiTenant.getType() != MultiTenantType.COLUMN) {
            // 查询租户数据源
            LbqWrapper<Resource> wrapper = Wraps.<Resource>lbQ().select(Resource::getPermission);
            if (!isAdmin) {
                List<Long> resIdList = this.baseMapper.selectResByUserId(userId);
                wrapper.in(Resource::getId, resIdList);
            }
            List<String> permission = TenantHelper.executeWithMaster(() -> this.resourceMapper.selectList(wrapper)
                    .stream().filter(Objects::nonNull).map(Resource::getPermission).distinct().toList());
            info.setFuncPermissions(permission);
        } else {
            List<String> permission = this.resourceMapper.selectPermissionByUserId(userId);
            info.setFuncPermissions(permission);
        }
    }

    @Override
    public IPage<Object> userOnlineList(UserOnlinePageReq req) {
        // TODO 需要重写
        List<Object> list = Lists.newArrayList();
        // 查询所有在线 Token
        List<String> tokenKeyList = StpUtil.searchTokenValue(StrUtil.EMPTY, 0, -1, false);
        for (String tokenKey : tokenKeyList) {
            String token = StrUtil.subAfter(tokenKey, StrUtil.COLON, true);
            // 如果已经过期则跳过
            if (StpUtil.stpLogic.getTokenActiveTimeoutByToken(token) < -1) {
                continue;
            }
            String key = String.format(extProperties.getServer().getTokenInfoKey(), token);
            UserInfoDetails info = ((JSONObject) StpUtil.getTokenSessionByToken(token).get(key)).to(UserInfoDetails.class);
            if (info == null || info.getLoginLog() == null) {
                continue;
            }
            LoginLog loginLog = JSONObject.from(info.getLoginLog()).toJavaObject(LoginLog.class);
            if (StrUtil.isNotBlank(req.getClientId()) && !StrUtil.equals(req.getClientId(), loginLog.getClientId())) {
                continue;
            }
            if (StrUtil.isNotBlank(req.getPrincipal()) && !StrUtil.equals(req.getPrincipal(), loginLog.getPrincipal())) {
                continue;
            }
            if (StrUtil.isNotBlank(req.getPlatform()) && !StrUtil.equals(req.getPlatform(), loginLog.getPlatform())) {
                continue;
            }
            if (ObjUtil.isNotNull(req.getTenantId()) && !NumberUtil.equals(req.getTenantId(), loginLog.getTenantId())) {
                continue;
            }
            JSONObject item = JSONObject.from(info.getLoginLog());
            item.put("token", token);
            list.add(item);
        }
        IPage<Object> page = new Page<>(req.getCurrent(), req.getSize(), list.size());
        List<Object> records = CollUtil.page((int) (ObjUtil.defaultIfNull(req.getCurrent(), 1L) - 1), (int) req.getSize(), list);
        page.setRecords(records);
        return page;
    }
}
