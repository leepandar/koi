package com.koi.common.db.mybatisplus.datascope.service;

import com.koi.common.core.FeignConstants;
import com.koi.common.core.security.DataPermission;
import com.koi.common.db.properties.DatabaseProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程数据权限服务
 *
 * @author lida
 */
@Primary
@ConditionalOnProperty(prefix = DatabaseProperties.INTERCEPT_DATA_PERMISSION_PREFIX, name = "remote")
@FeignClient(name = FeignConstants.AUTH_FEIGN_NAME, dismiss404 = true)
public interface FeignDataScopeServiceImpl extends DataScopeService {

    /**
     * 获取用户的数据权限
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @GetMapping("/{user_id}/data_permission")
    DataPermission getDataScopeById(@PathVariable("user_id") Long userId);

}
