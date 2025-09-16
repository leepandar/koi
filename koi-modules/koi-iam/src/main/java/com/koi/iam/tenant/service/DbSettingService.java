package com.koi.iam.tenant.service;

import com.koi.common.db.dynamic.core.EventAction;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.tenant.domain.dto.resp.DbSettingPageResp;
import com.koi.iam.tenant.domain.entity.DbSetting;
import com.koi.iam.tenant.domain.dto.req.DbSettingSaveReq;
import com.koi.iam.tenant.domain.dto.req.DbSettingSaveReq;
import com.koi.iam.tenant.domain.dto.resp.DbSettingPageResp;
import com.koi.iam.tenant.domain.entity.DbSetting;

import java.util.List;

/**
 * @author Levvin
 */
public interface DbSettingService extends SuperService<DbSetting> {

    /**
     * 查询所有可用的动态数据源
     *
     * @return 查询结果
     */
    List<DbSettingPageResp> selectTenantDynamicDatasource();

    /**
     * ping 数据源
     *
     * @param id id
     */
    void ping(Long id);

    /**
     * 添加或者保存动态数据源信息
     *
     * @param req req
     */
    void created(DbSettingSaveReq req);

    /**
     * 添加或者保存动态数据源信息
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, DbSettingSaveReq req);

    /**
     * 删除数据源
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * 发布数据源事件
     *
     * @param action   动作
     * @param tenantId 租户ID
     */
    void publishEvent(EventAction action, Long tenantId);
}
