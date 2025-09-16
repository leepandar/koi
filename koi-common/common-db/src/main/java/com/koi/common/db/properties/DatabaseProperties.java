package com.koi.common.db.properties;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * 客户端认证配置
 *
 * @author lida
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = DatabaseProperties.PREFIX)
public class DatabaseProperties {

    public static final String PREFIX = "extend.mybatis-plus";
    public static final String ENCRYPTOR_PROPERTY_NAME = PREFIX + ".encryptor.password";
    public static final String INTERCEPT_DATA_PERMISSION_PREFIX = PREFIX + ".intercept.data-permission";

    /**
     * 多租户配置
     */
    private MultiTenant multiTenant = new MultiTenant();

    /**
     * 加密
     */
    private Encryptor encryptor;

    /**
     * 拦截器
     */
    private Intercept intercept = new Intercept();
    /**
     * 审计拦截器
     */
    private Audit audit = new Audit();
    /**
     * 是否启用数据权限
     */
    private DataPermission dataPermission = new DataPermission();

    @Data
    public static class Encryptor {

        private String password;

    }

    @Data
    public static class Intercept {

        private Pagination pagination = new Pagination();

        /**
         * 攻击 SQL 阻断解析器
         */
        private boolean blockAttack = true;

        /**
         * SQL性能规范插件，限制比较多，慎用哦
         */
        private boolean illegalSql = false;
    }

    @Data
    public static class Audit {

        /**
         * 是否启用
         */
        private boolean enabled = false;

        /**
         * 需要记录审计日志的表
         */
        private List<String> includeTables = Lists.newArrayList();

        /**
         * 全局忽略(默认情况下如果没给表单独配置则取全局忽略)
         */
        private List<String> ignoreGlobalColumns = Lists.newArrayList("deleted", "created_time", "created_by", "created_name", "last_modified_time", "last_modified_by", "last_modified_name");

        /**
         * 忽略表字段
         */
        private Map<String, List<String>> ignoreTableColumns = Maps.newHashMap();
    }


    @Data
    public static class Pagination {

        /**
         * 生成 countSql 优化掉 join
         * 现在只支持 left join
         *
         * @since 3.4.2
         */
        protected boolean optimizeJoin = true;
        /**
         * 溢出总页数后是否进行处理
         */
        private boolean overflow;
        /**
         * 数据库类型
         */
        private DbType dbType;
        /**
         * 方言
         */
        private IDialect dialect;
    }

    @Data
    public static class DataPermission {

        /**
         * 是否启用
         */
        private boolean enabled = true;

        /**
         * 对时效性要求高就需要改成 true 了,但是这样性能有些许影响
         */
        private boolean remote = true;
    }

    @Data
    public static class MultiTenant {

        /**
         * 多租户模式
         */
        private MultiTenantType type = MultiTenantType.COLUMN;

        /**
         * 包含租户字段的表
         */
        private List<String> includeTables = Lists.newArrayList();

        /**
         * 多租户数据权限策略
         */
        private MultiTenantStrategy strategy = MultiTenantStrategy.LOCAL;

        /**
         * 默认数据源名称
         */
        private String defaultDsName = "master";
        /**
         * 租户 ID 列名 （数据库查询使用）
         */
        private String tenantIdColumn = "tenant_id";
        /**
         * 租户字段参数名称（前端传递给后端）
         */
        private String tenantCodeColumn = "Tenant-Code";

        /**
         * 超级租户编码
         */
        private String superTenantCode = "0000";
        /**
         * 租户数据库前缀
         */
        private String dsPrefix = "wemirr_tenant_";

        /**
         * 租户SQL脚本
         * 请放在 项目 resource/db 目录下
         */
        private List<String> tenantSqlScripts = Lists.newArrayList("classpath:db/tenant_schema.sql");

        public boolean isSuperTenant(String tenantCode) {
            return StringUtils.isNotBlank(tenantCode) && StringUtils.equals(tenantCode, superTenantCode);
        }

        public String buildTenantDataSourceName(String tenantCode) {
            return dsPrefix + tenantCode;
        }
    }

}
