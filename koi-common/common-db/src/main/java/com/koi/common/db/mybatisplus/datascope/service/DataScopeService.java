package com.koi.common.db.mybatisplus.datascope.service;


import com.koi.common.core.security.DataPermission;

/**
 * 数据权限服务
 * @author lida
 */
public interface DataScopeService {

    /**
     * 根据用户编号获取数据权限
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    DataPermission getDataScopeById(Long userId);

    /**
     * 根据用户编号获取数据权限
     *
     * @param userId 用户ID
     * @param orgId  机构ID
     * @return 查询结果
     */
    default DataPermission getDataScopeById(Long userId, Long orgId) {
        throw new RuntimeException("暂未实现");
    }
}
