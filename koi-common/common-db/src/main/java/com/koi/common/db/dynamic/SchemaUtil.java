package com.koi.common.db.dynamic;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.List;

/**
 * 数据库工具类
 *
 * @author lida
 */
@Slf4j
public class SchemaUtil {

    @SneakyThrows
    public static void createSchemaIfNotExists(String protocol, String schema, DataSource dataSource) {
        switch (protocol) {
            case "mysql" -> createMySqlSchema(dataSource, schema);
            case "postgresql" -> createPostgreSqlSchema(dataSource);
            case "oracle" -> createOracleSchema(dataSource);
            case "sqlserver" -> createSqlServerSchema(dataSource);
            default -> log.debug("不支持的数据库类型: " + schema);
        }
    }

    /**
     * MySQL Schema 创建
     *
     * @param hikari
     * @param schemaName
     */
    @SneakyThrows
    private static void createMySqlSchema(DataSource hikari, String schemaName) {
        String checkSchemaSql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
        Db db = Db.use(hikari);
        List<String> schemas = db.query(checkSchemaSql, String.class, schemaName);
        if (schemas.isEmpty()) {
            String createSchemaSql = StrUtil.format("CREATE DATABASE IF NOT EXISTS {} CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci", schemaName);
            db.execute(createSchemaSql);
            log.debug("MySQL Schema 创建成功: {}", schemaName);
        } else {
            log.debug("MySQL Schema 已存在: {}", schemaName);
        }
    }

    /**
     * PostgreSQL Schema 创建
     *
     * @param ds
     */
    @SneakyThrows
    private static void createPostgreSqlSchema(DataSource ds) {
        String checkSchemaSql = "SELECT schema_name FROM information_schema.schemata WHERE schema_name = ?";
        Db db = Db.use(ds);
        String schemaName = ds.getConnection().getSchema();
        List<String> schemas = db.query(checkSchemaSql, String.class, schemaName);
        if (schemas.isEmpty()) {
            String createSchemaSql = StrUtil.format("CREATE SCHEMA {}", schemaName);
            db.execute(createSchemaSql);
            log.debug("PostgreSQL Schema 创建成功: " + schemaName);
        } else {
            log.debug("PostgreSQL Schema 已存在: " + schemaName);
        }
    }

    /**
     * Oracle Schema 创建
     *
     * @param ds
     */
    @SneakyThrows
    private static void createOracleSchema(DataSource ds) {
        String checkSchemaSql = "SELECT USERNAME FROM ALL_USERS WHERE USERNAME = ?";
        Db db = Db.use(ds);
        String schemaName = ds.getConnection().getSchema();
        List<String> schemas = db.query(checkSchemaSql, String.class, schemaName);
        if (schemas.isEmpty()) {
            String createSchemaSql = StrUtil.format(
                    "CREATE USER {} IDENTIFIED BY password DEFAULT TABLESPACE users TEMPORARY TABLESPACE temp",
                    schemaName.toUpperCase());
            db.execute(createSchemaSql);
            log.debug("Oracle Schema 创建成功: " + schemaName.toUpperCase());
        } else {
            log.debug("Oracle Schema 已存在: " + schemaName.toUpperCase());
        }
    }

    /**
     * SQL Server Schema 创建
     *
     * @param ds
     */
    @SneakyThrows
    private static void createSqlServerSchema(DataSource ds) {
        String checkSchemaSql = "SELECT name FROM sys.schemas WHERE name = ?";
        Db db = Db.use(ds);
        String schemaName = ds.getConnection().getSchema();
        List<String> schemas = db.query(checkSchemaSql, String.class, schemaName);
        if (schemas.isEmpty()) {
            String createSchemaSql = StrUtil.format("CREATE SCHEMA {}", schemaName);
            db.execute(createSchemaSql);
            log.debug("SQL Server Schema 创建成功: " + schemaName);
        } else {
            log.debug("SQL Server Schema 已存在: " + schemaName);
        }
    }
}
