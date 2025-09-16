package com.koi.iam.tenant.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.tenant.domain.dto.req.TenantConfigReq;
import com.koi.iam.tenant.domain.dto.req.TenantSaveReq;
import com.koi.iam.tenant.domain.dto.req.TenantSettingReq;
import com.koi.iam.tenant.domain.dto.req.TenantConfigReq;
import com.koi.iam.tenant.domain.dto.req.TenantSaveReq;
import com.koi.iam.tenant.domain.dto.req.TenantSettingReq;
import com.koi.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.koi.iam.tenant.domain.entity.Tenant;

/**
 * @author lida
 */
public interface TenantService extends SuperService<Tenant> {

    /**
     * 保存租户
     *
     * @param req 租户信息
     */
    void create(TenantSaveReq req);

    /**
     * 保存租户
     *
     * @param id  id
     * @param req 租户信息
     */
    void modify(Long id, TenantSaveReq req);

    /**
     * 租户配置
     *
     * @param tenantId tenantId
     * @param req      租户配置
     */
    void tenantConfig(Long tenantId, TenantConfigReq req);

    /**
     * 初始化SQL脚本
     *
     * @param id id
     */
    void initSqlScript(Long id);

    /**
     * 字典刷新
     *
     * @param tenantId tenantId
     */
    void refreshTenantDict(Long tenantId);

    /**
     * 租户设置信息
     *
     * @param tenantId 租户ID
     * @return 查询结果
     */
    TenantSettingResp settingInfo(Long tenantId);

    /**
     * 保存租户设置
     *
     * @param tenantId 租户ID
     * @param req      设置信息
     */
    void saveSetting(Long tenantId, TenantSettingReq req);

}
