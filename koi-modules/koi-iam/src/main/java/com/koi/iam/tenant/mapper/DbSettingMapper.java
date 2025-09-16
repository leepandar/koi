package com.koi.iam.tenant.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.tenant.domain.dto.resp.DbSettingPageResp;
import com.koi.iam.tenant.domain.entity.DbSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lida
 */
@Repository
public interface DbSettingMapper extends SuperMapper<DbSetting> {

    /**
     * 查询所有可用的动态数据源.
     *
     * @param id id
     * @return 查询结果
     */
    List<DbSettingPageResp> selectTenantDbById(@Param("id") Long id);

    /**
     * 获取租户动态数据源
     *
     * @param tenantId tenantId
     * @return 查询结果
     */
    DbSettingPageResp getTenantDynamicDatasourceByTenantId(Long tenantId);

}
