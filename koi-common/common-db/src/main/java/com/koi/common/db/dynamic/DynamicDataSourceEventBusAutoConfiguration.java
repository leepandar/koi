package com.koi.common.db.dynamic;

import com.baomidou.dynamic.datasource.processor.DsJakartaHeaderProcessor;
import com.baomidou.dynamic.datasource.processor.DsJakartaSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import com.koi.common.db.dynamic.core.DynamicDatasourceEventPublish;
import com.koi.common.db.dynamic.core.local.DynamicDatasourceEvent;
import com.koi.common.db.dynamic.core.local.DynamicDatasourceLocalListener;
import com.koi.common.db.dynamic.core.redis.RedisDynamicDatasourceListener;
import com.koi.common.db.dynamic.core.redis.RedisDynamicDatasourcePublish;
import com.koi.common.db.dynamic.feign.TenantFeignClient;
import com.koi.common.db.properties.DatabaseProperties;
import com.koi.common.redis.listener.MessageEventListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 如果要实现自定义 database 库名的话 也很容易
 *
 * @author lida
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "type", havingValue = "datasource")
@EnableConfigurationProperties(DatabaseProperties.class)
public class DynamicDataSourceEventBusAutoConfiguration {

    private static final String UNDEFINED = "undefined";

    @Bean
    public DynamicDataSourceHandler tenantDynamicDataSourceProcess() {
        return new DynamicDataSourceHandler();
    }

    @Bean(initMethod = "init")
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "strategy", havingValue = "feign")
    public DynamicDataSourceLoad tenantDynamicDataSourceLoad(DynamicDataSourceHandler handler, TenantFeignClient tenantFeignClient) {
        return new DynamicDataSourceLoad(handler, tenantFeignClient);
    }

    @Bean
    @Order(value = Integer.MIN_VALUE)
    public ApplicationListener<DynamicDatasourceEvent> applicationListener(DynamicDataSourceHandler handler) {
        return new DynamicDatasourceLocalListener(handler);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "db-notify", havingValue = "redis")
    public DynamicDatasourceEventPublish redisDynamicDatasourcePublish(StringRedisTemplate redisTemplate) {
        return new RedisDynamicDatasourcePublish(redisTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "db-notify", havingValue = "redis")
    @Order(value = Integer.MIN_VALUE)
    public MessageEventListener redisDynamicDatasourceListener(DynamicDataSourceHandler handler) {
        return new RedisDynamicDatasourceListener(handler);
    }

    @Bean
    @Primary
    public DsProcessor dsProcessor() {
        DsJakartaHeaderProcessor headerProcessor = new DsJakartaHeaderProcessor();
        DsJakartaSessionProcessor sessionProcessor = new DsJakartaSessionProcessor();
        DsSpelExpressionProcessor expressionProcessor = new DsSpelExpressionProcessor();
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(expressionProcessor);
        return headerProcessor;
    }

    private String getTenantDb(HttpServletRequest request, DatabaseProperties.MultiTenant multiTenant, String tenantCode) {
        if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, UNDEFINED)) {
            log.debug("TenantCode is null,Switch the default datasource => {}", multiTenant.getDefaultDsName());
            return multiTenant.getDefaultDsName();
        }
        if (StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
            log.debug("TenantCode is super tenant,Switch the default datasource => {}", multiTenant.getDefaultDsName());
            return multiTenant.getDefaultDsName();
        }
        String db = multiTenant.getDsPrefix() + tenantCode;
        log.debug("TenantCode is => {} ,Switch the => {}, Uri => {}", tenantCode, db, request.getRequestURI());
        return db;
    }

}
