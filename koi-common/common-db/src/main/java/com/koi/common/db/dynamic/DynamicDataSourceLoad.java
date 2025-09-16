package com.koi.common.db.dynamic;

import cn.hutool.core.collection.CollUtil;
import com.koi.common.db.dynamic.core.DynamicDatasourceEvent;
import com.koi.common.db.dynamic.core.EventAction;
import com.koi.common.db.dynamic.feign.TenantFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class DynamicDataSourceLoad {

    private final DynamicDataSourceHandler dynamicDataSourceHandler;
    private final TenantFeignClient tenantFeignClient;

    public void init() {
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic begin...");
        final List<DynamicDatasourceEvent> result = tenantFeignClient.selectActiveDbSetting();
        if (CollUtil.isEmpty(result)) {
            log.warn("feign pull tenantDynamicDataSources is null......");
            return;
        }
        result.forEach(tenantDynamicDataSource -> dynamicDataSourceHandler.handler(EventAction.ADD, tenantDynamicDataSource));
        log.debug("extend.mybatis-plus.multi-tenant.strategy eq feign , pull dynamic end...");
    }

}
