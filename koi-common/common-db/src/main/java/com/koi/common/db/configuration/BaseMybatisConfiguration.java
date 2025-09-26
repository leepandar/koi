package com.koi.common.db.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.datascope.handler.DataScopePermissionHandler;
import com.koi.common.db.mybatisplus.audit.AuditInterceptor;
import com.koi.common.db.mybatisplus.handler.MyBatisMetaObjectHandler;
import com.koi.common.db.mybatisplus.injector.MySqlInjector;
import com.koi.common.db.properties.DatabaseProperties;
import com.koi.common.db.properties.MultiTenantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * Mybatis 常用重用拦截器
 * 拦截器执行一定是：
 * WriteInterceptor > DataScopeInterceptor > PaginationInterceptor
 *
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
public abstract class BaseMybatisConfiguration {

    private final DatabaseProperties properties;
    private final AuthenticationContext context;

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    @Order(5)
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        final DatabaseProperties.MultiTenant multiTenant = properties.getMultiTenant();
        if (MultiTenantType.NONE != multiTenant.getType()) {
            // 新增多租户拦截器
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {

                @Override
                public Expression getTenantId() {
                    // 租户ID
                    log.debug("当前租户ID - {}", context.tenantId());
                    return context.tenantId() == null ? null : new LongValue(context.tenantId());
                }

                @Override
                public boolean ignoreTable(String tableName) {
                    final List<String> tables = multiTenant.getIncludeTables();
                    // 判断哪些表不需要进行多租户判断,返回false表示都需要进行多租户判断
                    return context.anonymous() || !tables.contains(tableName);
                }

                @Override
                public String getTenantIdColumn() {
                    return multiTenant.getTenantIdColumn();
                }

            }));
        }
        // 加载其它插件
        loadInnerInterceptor(interceptor);
        return interceptor;
    }

    /**
     * mybatis-plus 分页插件
     *
     * @param pagination 参数配置
     * @return 插件
     */
    public PaginationInnerInterceptor paginationInnerInterceptor(final DatabaseProperties.Pagination pagination) {
        // 新增MYSQL分页拦截器,一定要先设置租户判断后才进行分页拦截设置
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(pagination.getDbType());
        paginationInnerInterceptor.setOverflow(pagination.isOverflow());
        paginationInnerInterceptor.setDialect(pagination.getDialect());
        return paginationInnerInterceptor;
    }

    protected void loadInnerInterceptor(MybatisPlusInterceptor interceptor) {
        final DatabaseProperties.Intercept intercept = properties.getIntercept();
        if (properties.getDataPermission().isEnabled()) {
            // 分页拦截器之前的插件 => 数据权限插件
            interceptor.addInnerInterceptor(new DataPermissionInterceptor(new DataScopePermissionHandler(context)));
        }
        if (intercept.isBlockAttack()) {
            // 防止全表更新与删除插件: BlockAttackInnerInterceptor
            interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        }
        if (intercept.isIllegalSql()) {
            // SQL性能规范插件，限制比较多，慎用哦
            interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        }
        if (properties.getAudit().isEnabled()) {
            interceptor.addInnerInterceptor(new AuditInterceptor(properties.getAudit()));
        }
        // 分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor(intercept.getPagination()));
    }

    @Bean
    @ConditionalOnMissingBean
    public MySqlInjector getMySqlInjector() {
        return new MySqlInjector();
    }

    @Bean
    @ConditionalOnMissingBean
    public MetaObjectHandler metaObjectHandler() {
        return new MyBatisMetaObjectHandler(context);
    }
}
