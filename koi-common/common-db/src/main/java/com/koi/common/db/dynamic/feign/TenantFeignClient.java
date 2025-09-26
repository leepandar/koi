package com.koi.common.db.dynamic.feign;

import com.koi.common.db.dynamic.core.DynamicDatasourceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 租户动态数据源服务
 *
 * @author lida
 */
@FeignClient(name = "koi-iam", dismiss404 = true, fallback = TenantFeignClient.TenantFeignClientFallback.class)
public interface TenantFeignClient {

    /**
     * 查询所有租户数据源
     *
     * @return 查询结果
     */
    @GetMapping(value = "/db-setting/active", headers = {"X-Auto-Token=true"})
    List<DynamicDatasourceEvent> selectActiveDbSetting();

    @Component
    @RequiredArgsConstructor
    class TenantFeignClientFallback implements TenantFeignClient {

        @Override
        public List<DynamicDatasourceEvent> selectActiveDbSetting() {
            return null;
        }
    }
}
