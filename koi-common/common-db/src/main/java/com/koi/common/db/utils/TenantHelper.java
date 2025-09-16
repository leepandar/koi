package com.koi.common.db.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.properties.DatabaseProperties;
import com.koi.common.db.properties.MultiTenantType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

/**
 * 租户工具类
 *
 * @author lida
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TenantHelper {

    public static boolean isSuperTenant() {
        DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        AuthenticationContext context = SpringUtil.getBean(AuthenticationContext.class);
        String tenantCode = context.tenantCode();
        return StringUtils.isNotBlank(tenantCode) && StringUtils.equals(tenantCode, properties.getMultiTenant().getSuperTenantCode());
    }

    /**
     * 使用主数据源执行
     *
     * @param supplier 待执行的动作
     */
    public static <T> T executeWithMaster(Supplier<T> supplier) {
        try {
            DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
            DynamicDataSourceContextHolder.push(properties.getMultiTenant().getDefaultDsName());
            return supplier.get();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    /**
     * 使用指定 DB 执行
     *
     * @param supplier 待执行的动作
     */
    public static <T> T executeWithTenantDb(String tenantCode, Supplier<T> supplier) {
        DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (multiTenant.getType() == MultiTenantType.COLUMN || multiTenant.isSuperTenant(tenantCode)) {
            return executeWithMaster(supplier);
        }
        try {
            DynamicDataSourceContextHolder.push(multiTenant.buildTenantDataSourceName(tenantCode));
            return supplier.get();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    public static boolean isDynamicSource() {
        DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        return multiTenant.getType() == MultiTenantType.DATASOURCE;
    }

    /**
     * 使用隔离类型执行
     *
     * @param dbSupplier     数据源函数
     * @param columnSupplier 字段隔离函数
     * @param <T>            返回类型
     * @return 查询结果
     */
    public static <T> T executeWithIsolationType(Supplier<T> dbSupplier, Supplier<T> columnSupplier) {
        return isDynamicSource() ? dbSupplier.get() : columnSupplier.get();
    }

    public static <T> T withIgnoreStrategy(Supplier<T> block) {
        return withIgnoreStrategy(IgnoreStrategy.builder().tenantLine(true).build(), block);
    }

    public static <T> T withIgnoreStrategy(IgnoreStrategy strategy, Supplier<T> block) {
        try {
            InterceptorIgnoreHelper.handle(strategy);
            return block.get();
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }
}
