package com.koi.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.dynamic.core.DynamicDatasourceEvent;
import com.koi.common.db.dynamic.core.DynamicDatasourceEventPublish;
import com.koi.common.db.dynamic.core.EventAction;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.db.properties.DatabaseProperties;
import com.koi.common.db.properties.MultiTenantType;
import com.koi.iam.tenant.domain.dto.req.DbSettingSaveReq;
import com.koi.iam.tenant.domain.dto.resp.DbSettingPageResp;
import com.koi.iam.tenant.domain.entity.DbSetting;
import com.koi.iam.tenant.mapper.DbSettingMapper;
import com.koi.iam.tenant.service.DbSettingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DbSettingServiceImpl extends SuperServiceImpl<DbSettingMapper, DbSetting> implements DbSettingService {

    private final DatabaseProperties databaseProperties;
    private final ApplicationContext applicationContext;

    /**
     * 查询所有可用的动态数据源
     *
     * @return
     */
    @Override
    public List<DbSettingPageResp> selectTenantDynamicDatasource() {
        return this.baseMapper.selectTenantDbById(null);
    }

    /**
     * ping 数据源
     *
     * @param id id
     */
    @Override
    public void ping(Long id) {
        log.debug("查询结果 - {}", JSON.toJSONString(""));
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        final List<DbSettingPageResp> dataSourceList = this.baseMapper.selectTenantDbById(null);
        if (CollectionUtil.isEmpty(dataSourceList)) {
            log.warn("未找到符合条件的数据源...");
            return;
        }
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            return;
        }
        for (DbSettingPageResp dynamicDatasource : dataSourceList) {
            publishEvent(true, EventAction.ADD, dynamicDatasource);
        }
    }

    /**
     * 添加或者保存动态数据源信息
     *
     * @param req req
     */
    @Override
    @DSTransactional
    public void created(DbSettingSaveReq req) {
        final long count = super.count(Wraps.<DbSetting>lbQ().eq(DbSetting::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DbSetting bean = BeanUtilPlus.toBean(req, DbSetting.class);
        this.baseMapper.insert(bean);
    }

    /**
     * 添加或者保存动态数据源信息
     *
     * @param id  id
     * @param req req
     */
    @Override
    @DSTransactional
    public void edit(Long id, DbSettingSaveReq req) {
        final long count = super.count(Wraps.<DbSetting>lbQ()
                .ne(DbSetting::getId, id)
                .eq(DbSetting::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("连接名称已存在");
        }
        DbSetting bean = BeanUtilPlus.toBean(id, req, DbSetting.class);
        this.baseMapper.updateById(bean);
    }

    /**
     * 删除数据源
     *
     * @param id id
     */
    @Override
    @DSTransactional
    public void delete(Long id) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("数据连接信息不存在"));
        this.baseMapper.deleteById(id);
        final List<DbSettingPageResp> dataSourceList = this.baseMapper.selectTenantDbById(id);
        for (DbSettingPageResp tenantDynamicDatasource : dataSourceList) {
            publishEvent(false, EventAction.DEL, tenantDynamicDatasource);
        }
    }

    /**
     * 发布数据源事件
     *
     * @param action   动作
     * @param tenantId 租户ID
     */
    @Override
    public void publishEvent(EventAction action, Long tenantId) {
        final DbSettingPageResp dbSetting = this.baseMapper.getTenantDynamicDatasourceByTenantId(tenantId);
        publishEvent(false, action, dbSetting);
    }

    private void publishEvent(boolean init, EventAction action, DbSettingPageResp dbSetting) {
        if (Objects.isNull(dbSetting)) {
            throw CheckedException.notFound("租户未关联数据源信息");
        }
        if (databaseProperties.getMultiTenant().getType() != MultiTenantType.DATASOURCE) {
            throw CheckedException.notFound("系统异常,请配置[动态数据源]模式");
        }
        final DynamicDatasourceEventPublish eventPublisher = SpringUtil.getBean(DynamicDatasourceEventPublish.class);
        final DynamicDatasourceEvent event = BeanUtil.toBean(dbSetting, DynamicDatasourceEvent.class);
        event.setAction(action.getType());
        if (init) {
            applicationContext.publishEvent(new com.koi.common.db.dynamic.core.local.DynamicDatasourceEvent(action, event));
        } else {
            eventPublisher.publish(event);
        }
        log.debug("event publish successful - {}", event);
    }
}
