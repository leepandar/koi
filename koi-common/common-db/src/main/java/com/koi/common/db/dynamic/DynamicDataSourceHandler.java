package com.koi.common.db.dynamic;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.hikaricp.HikariDataSourceCreator;
import com.baomidou.dynamic.datasource.support.ScriptRunner;
import com.google.common.collect.Lists;
import com.koi.common.core.MvelHelper;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.dynamic.core.DynamicDatasourceEvent;
import com.koi.common.db.dynamic.core.EventAction;
import com.koi.common.db.properties.DatabaseProperties;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 动态数据源处理
 *
 * @author Levin
 */
@Slf4j
public class DynamicDataSourceHandler {

    public static final String TENANT_DATASOURCE_POOL = "TenantDataSourcePool_%s";

    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    @Resource
    private HikariDataSourceCreator hikariDataSourceCreator;

    @Resource
    private DatabaseProperties databaseProperties;

    @Resource
    private ResourceLoader resourceLoader;

    @NotNull
    private static DataSourceProperty getDataSourceProperty(DynamicDatasourceEvent db, String database, boolean lazy) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(String.format(TENANT_DATASOURCE_POOL, db.getTenantCode()));
        dataSourceProperty.setDriverClassName(db.getDriverClassName());
        if (lazy) {
            String url =
                    "jdbc:mysql://" + db.getHost() + "/" + database + "?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
            dataSourceProperty.setUrl(url);
        } else {
            String url = "jdbc:mysql://" + db.getHost();
            dataSourceProperty.setUrl(url);
        }
        dataSourceProperty.setUsername(db.getUsername());
        dataSourceProperty.setPassword(db.getPassword());
        dataSourceProperty.setLazy(lazy);
        return dataSourceProperty;
    }

    public void handler(EventAction action, DynamicDatasourceEvent db) {
        if (Objects.isNull(db)) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        if (Objects.isNull(action)) {
            log.warn("event action is null....");
            return;
        }
        log.info("接收租户事件消息: - {} - {}", action, db);
        final DatabaseProperties.MultiTenant multiTenant = databaseProperties.getMultiTenant();
        final String database = multiTenant.getDsPrefix() + db.getTenantCode();
        if (action == EventAction.DEL) {
            dynamicRoutingDataSource.removeDataSource(database);
            log.info("数据源移除成功 - {}", database);
            return;
        }
        if (action == EventAction.INIT) {
            // 创建数据库
            DataSourceProperty property = getDataSourceProperty(db, database, false);
            DataSource dataSource = hikariDataSourceCreator.createDataSource(property);
            log.debug("数据源信息 - {} - {} - {}", property.getUsername(), property.getPassword(), database);
            SchemaUtil.createSchemaIfNotExists(db.getDbType(), database, dataSource);
        }
        DataSourceProperty dataSourceProperty = getDataSourceProperty(db, database, true);
        DataSource dataSource = hikariDataSourceCreator.createDataSource(dataSourceProperty);
        log.debug("数据源信息 - {} - {} - {}", dataSourceProperty.getUsername(), dataSourceProperty.getPassword(), database);
        dynamicRoutingDataSource.addDataSource(database, dataSource);
        log.info("数据源添加成功 - {}", database);
        final Set<String> dsSets = dynamicRoutingDataSource.getDataSources().keySet();
        log.debug("连接池信息 - {}", dsSets);
    }

    public String buildDb(String tenantCode) {
        final DatabaseProperties.MultiTenant multiTenant = databaseProperties.getMultiTenant();
        if (StringUtils.isBlank(tenantCode) || StringUtils.equals(tenantCode, multiTenant.getSuperTenantCode())) {
            return multiTenant.getDefaultDsName();
        }
        return multiTenant.getDsPrefix() + tenantCode;
    }

    public void initSqlScript(String tenantCode, Map<String, Object> variables) {
        runScript(tenantCode, variables);
    }

    @SneakyThrows
    private void runScript(String tenantCode, Map<String, Object> variables) {
        log.info("runScript tenantCode - {}", tenantCode);
        if (tenantCode == null) {
            throw CheckedException.badRequest("租户编码不能为空");
        }
        String dsKey = buildDb(tenantCode);
        // 从ThreadLocal中获取当前数据源
        final DataSource dataSource = dynamicRoutingDataSource.getDataSource(dsKey);
        ScriptRunner scriptRunner = new ScriptRunner(false, ";");
        final DatabaseProperties.MultiTenant multiTenant = databaseProperties.getMultiTenant();
        final List<String> tenantSqlScripts = multiTenant.getTenantSqlScripts();
        log.info("tenantSqlScripts - {}", tenantSqlScripts);
        if (CollectionUtil.isEmpty(tenantSqlScripts)) {
            return;
        }
        for (String scriptPath : tenantSqlScripts) {
            log.info("path - {}", scriptPath);
            final InputStream stream = resourceLoader.getResource(scriptPath).getInputStream();
            List<String> scriptLine = IoUtil.readUtf8Lines(stream, Lists.newArrayList());
            final File tmpFile = FileUtil.createTempFile(new File(Objects.requireNonNull(this.getClass().getResource("/")).getPath()));
            List<String> newSqlScript = Lists.newArrayList();
            for (String text : scriptLine) {
                newSqlScript.add(MvelHelper.format(text, variables));
            }
            FileUtil.writeLines(newSqlScript, tmpFile, StandardCharsets.UTF_8);
            scriptRunner.runScript(dataSource, tmpFile.getName());
            FileUtil.del(tmpFile);
        }
    }
}
