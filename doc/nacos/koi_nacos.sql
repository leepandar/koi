/*
 Navicat Premium Data Transfer

 Source Server         : koi_nacos
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : 47.99.109.115:3306
 Source Schema         : koi_nacos

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 15/09/2025 10:59:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'group_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'configuration description',
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'configuration usage',
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '配置生效的描述',
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '配置的类型',
  `c_schema` text COLLATE utf8_bin COMMENT '配置的模式',
  `encrypted_data_key` varchar(1024) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (2, 'koi-suite.yaml', 'DEFAULT_GROUP', '# 日志采集策略\nextend:\n  boot:\n    log:\n      strategy: feign\n  feign:\n    # 自动获取TOKEN插件\n    plugin:\n      enabled: true\n      token:\n        enabled: true\n        load-balance: true\n        login:\n          client-id: pc-web\n          client-secret: pc-web\n          password: 123456\n          tenant-code: 0\n          username: admin\n        uri: http://koi-iam/token/login\n  mybatis-plus:\n    intercept:\n      data-permission:\n        remote: true\n    multi-tenant:\n      # 租户隔离策略（表字段隔离\n      include-tables: t_file_storage,t_file_storage_setting\n      strategy: feign\n      type: column\n      #租户隔离策略（动态数据源隔离）\n    #   type: datasource\n    #   ds-interceptor: true\n    #   db-notify: redis\nlogging:\n  level:\n    com:\n      wemirr: debug\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\nspringdoc:\n  packages-to-scan: com.koi\n', '602f550e10be43e94dc97d22f89410c3', '2025-09-15 10:45:32', '2025-09-15 10:48:59', 'nacos', '10.11.65.22', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (3, 'cloud-default.yaml', 'DEFAULT_GROUP', 'feign:\n  httpclient:\n    enabled: true # HttpClient的开关\n    connection-timeout: 5000       # 连接超时（毫秒）\n    time-to-live: 3                # 连接存活时长\n    time-to-live-unit: minutes     # 连接存活时长单位\n    max-connections: 200  # 最大连接数\n    max-connections-per-route: 50   # 单个请求路径的最大连接数', '1254e0b93f981cf493538ff7529b04c6', '2025-09-15 10:45:32', '2025-09-15 10:47:22', 'nacos', '10.11.65.22', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (4, 'security.yaml', 'DEFAULT_GROUP', '############## Sa-Token 配置 (文档: https://sa-token.cc) ##############\nsa-token:\n  # token 名称（同时也是 cookie 名称）\n  #  token-name: wp-token\n  token-name: Authorization\n  token-prefix: Bearer\n  # token 有效期（单位：秒） 默认30天，-1 代表永久有效\n  timeout: 86400\n  # token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结\n  active-timeout: -1\n  # 是否允许同一账号多地同时登录 （为 true 时允许一起登录, 为 false 时新登录挤掉旧登录）\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个 token （为 true 时所有登录共用一个 token, 为 false 时每次登录新建一个 token）\n  is-share: true\n  # token 风格（默认可取值：uuid、simple-uuid、random-32、random-64、random-128、tik）\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: true\n', '087a51d50fdde236f5434cae90218ab8', '2025-09-15 10:45:33', '2025-09-15 10:47:12', 'nacos', '10.11.65.22', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (5, 'koi-gateway.yaml', 'DEFAULT_GROUP', '# 端点放行\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# rabbon 超时时间设置\nribbon:\n  ConnectTimeout: 5000000\n  MaxAutoRetries: 0\n  MaxAutoRetriesNextServer: 1\n  ReadTimeout: 5000000\n\n# 跨域配置\nspring:\n  cloud:\n    gateway:\n      # 动态路由\n      dynamic-route:\n        type: redis\n        enabled: true\n      enabled: true\n      discovery:\n        locator:\n          enabled: true\n          lowerCaseServiceId: true\n      routes:\n        - id: koi-iam\n          uri: lb://koi-iam\n          predicates:\n            - Path=/iam/**\n          filters:\n            # 密码加密过滤器，如果配置了那么在前端传递的 password 请进行 AES 加密\n            - name: PasswordDecoderFilter\n              args:\n                password: \'password\'\n                loginUrl: \'/oauth2/token\'\n                # 要 16 位\n                encodeKey: \'koi-platform-key\'\n            #            - name: BlackWhiteList\n            #              args:\n            #                type: BLACK_LIST\n            #                ip-list: 127.0.0.1,0:0:0:0:0:0:0:1\n            #                ignore-intranet: true\n            - name: RequestRateLimiter\n              args:\n                redis-rate-limiter.replenishRate: 100   # 允许用户每秒处理多少个请求\n                redis-rate-limiter.burstCapacity: 100   # 令牌桶的容量，允许在一秒钟内完成的最大请求数\n                # 使用 IP 限流策略（使用 SpEL 按名称引用 bean）\n                key-resolver: \"#{@ipKeyResolver}\"\n            - StripPrefix=1\n            - PreserveHostHeader\n            - name: Retry\n              args:\n                retries: 1\n                statuses: BAD_GATEWAY\n                backoff:\n                  firstBackoff: 200ms\n                  maxBackoff: 500ms\n                  factor: 1\n                  basedOnPreviousValue: false\n                  exceptions: TimeoutException\n        - id: koi-iam-ws\n          uri: lb:ws://koi-iam\n          predicates:\n            - Path=/iam/**\n          filters:\n            - StripPrefix=1\n        - id: koi-suite\n          uri: lb://koi-suite\n          predicates:\n            - Path=/suite/**\n          filters:\n            - StripPrefix=1\n            - PreserveHostHeader\n        - id: koi-monitor\n          uri: lb://koi-monitor\n          predicates:\n            - Path=/monitor/**\n          filters:\n            - StripPrefix=1\n            - PreserveHostHeader\n        - id: koi-demo\n          uri: lb://koi-demo\n          predicates:\n            - Path=/demo/**\n          filters:\n            - StripPrefix=1\n            - PreserveHostHeader\n      loadbalancer:\n        use404: true\n      httpclient:\n        compression: true\n        response-timeout: 300s\n        pool:\n          type: elastic\n          max-idle-time: 30s\n          max-connections: 1000\n          acquire-timeout: 180000\n      globalcors:\n        cors-configurations:\n          \'[/**]\':  # 匹配所有路径\n            allow-credentials: true  # 允许携带凭证\n            allowedHeaders:\n              - \"*\"  # 允许所有请求头\n            allowed-origin-patterns:\n              - \"*\"  # 允许所有源（注意：生产环境不建议使用*，应指定具体域名）\n            allowedMethods:\n              - \"*\"  # 允许所有HTTP方法（GET、POST、PUT等）', 'cd34fe802e551941b3d8663d2c962c0a', '2025-09-15 10:45:34', '2025-09-15 10:50:44', 'nacos', '10.11.65.22', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (6, 'mybatis-plus.yaml', 'DEFAULT_GROUP', 'mybatis-plus:\n  mapper-locations:\n    - classpath:com/koi/**/mapper/**/*.xml\n  #实体扫描，多个package用逗号或者分号分隔\n  type-aliases-package: com.koi.**.entity,com.koi.**.handler.type\n  type-handlers-package: com.koi.**.handler.type\n  global-config:\n    db-config:\n      id-type: auto\n      insert-strategy: NOT_NULL\n      update-strategy: NOT_NULL\n      where-strategy: not_empty\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: false', '98638950d4e258498d5c8d837b27e326', '2025-09-15 10:45:35', '2025-09-15 10:46:49', 'nacos', '10.11.65.22', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (7, 'koi-iam.yaml', 'DEFAULT_GROUP', 'extend:\n  mybatis-plus:\n    multi-tenant:\n      # 可以组合数据源+字段独立隔离\n      include-tables: t_user,sys_role,sys_org,sys_position,c_login_log,c_opt_log,t_tenant_dict,t_tenant_dict_item\n      strategy: local\n      type: column\n      # 租户隔离策略（动态数据源隔离）\n    #   db-notify: redis\n    #   type: datasource\n\n\n  # 启用 Redis 扩展\n  redis:\n    enabled: true\n    lock:\n      enabled: true\n\n# 日志配置\nlogging:\n  level:\n    com:\n      wemirr: debug\n    springfox:\n      documentation: warn\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      # 如果不需要开启动态数据源,请关闭 AOP 切换\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\n  main:\n    allow-bean-definition-overriding: true\n\n  # 模板文件\n  thymeleaf:\n    prefix: classpath:/templates/\n', '60f4a16d80b6000515351ce524ad463e', '2025-09-15 10:45:35', '2025-09-15 10:49:26', 'nacos', '10.11.65.22', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (8, 'redis.yaml', 'DEFAULT_GROUP', '# Redis 连接\nspring:\n  cache:\n    redis:\n      cache-null-values: false\n      time-to-live: 2H\n    type: redis\n  data:\n    redis:\n      database: 2\n      host: 127.0.0.1\n      port: 6379\n      # password: 123456', '8e43ea117736d634e558c9799f0ba9da', '2025-09-15 10:45:36', '2025-09-15 10:46:02', 'nacos', '10.11.65.22', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (11, 'koi-bpm.yaml', 'DEFAULT_GROUP', '# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      # 如果不需要开启动态数据源,请关闭 AOP 切换\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi_bpm?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\n  main:\n    allow-bean-definition-overriding: true\n\nextend:\n  boot:\n    remote:\n      aop-enabled: true\n    log:\n      strategy: feign\n  feign:\n    plugin:\n      enabled: true\n  mybatis-plus:\n    multi-tenant: \n      strategy: feign\n      # 可以组合数据源+字段独立隔离\n      include-tables: wp_process_model,wp_process_model_form,wp_process_task_comment,wp_process_task_ext,wp_process_task_his,wp_process_category\n      type: column\n      # 租户隔离策略（动态数据源隔离）\n    #   db-notify: redis\n    #   type: datasource\n  # 启用 Redis 扩展\n  redis:\n    enabled: true\n    lock:\n      enabled: true\n\n#camunda 配置\ncamunda:\n  bpm:\n    admin-user:\n      id: admin\n      password: admin\n    # 是否启用授权\n    authorization:\n      enabled: false\n    auto-deployment-enabled: false\n    database:\n      schema-update: true\n      type: mysql\n    generic-properties:\n      properties:\n        historyTimeToLive: P1D\n        skipIsolationLevelCheck: true\n\n# 日志配置\nlogging:\n  level:\n    com:\n      koi: debug\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'', '5003f9a6dc26f132b98cf14900d7dd9e', '2025-09-15 16:07:57', '2025-09-15 17:13:11', 'nacos', '10.11.65.22', '', '', '工作流', '', '', 'yaml', '', '');
COMMIT;

-- ----------------------------
-- Table structure for config_info_gray
-- ----------------------------
DROP TABLE IF EXISTS `config_info_gray`;
CREATE TABLE `config_info_gray` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `src_user` text COMMENT 'src_user',
  `src_ip` varchar(100) DEFAULT NULL COMMENT 'src_ip',
  `gmt_create` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT 'gmt_create',
  `gmt_modified` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT 'gmt_modified',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `gray_name` varchar(128) NOT NULL COMMENT 'gray_name',
  `gray_rule` text NOT NULL COMMENT 'gray_rule',
  `encrypted_data_key` varchar(256) NOT NULL DEFAULT '' COMMENT 'encrypted_data_key',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfogray_datagrouptenantgray` (`data_id`,`group_id`,`tenant_id`,`gray_name`),
  KEY `idx_dataid_gmt_modified` (`data_id`,`gmt_modified`),
  KEY `idx_gmt_modified` (`gmt_modified`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='config_info_gray';

-- ----------------------------
-- Records of config_info_gray
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增长标识',
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Records of group_capacity
-- ----------------------------
BEGIN;
INSERT INTO `group_capacity` VALUES (1, '', 0, 7, 0, 0, 0, 0, '2025-09-15 02:45:30', '2025-09-15 02:54:39');
INSERT INTO `group_capacity` VALUES (2, '__MACOSX', 0, 0, 0, 0, 0, 0, '2025-09-15 02:45:31', '2025-09-15 02:54:39');
INSERT INTO `group_capacity` VALUES (3, 'DEFAULT_GROUP', 0, 7, 0, 0, 0, 0, '2025-09-15 02:45:31', '2025-09-15 02:54:39');
COMMIT;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'id',
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'nid, 自增标识',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'operation type',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` varchar(1024) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密钥',
  `publish_type` varchar(50) COLLATE utf8_bin DEFAULT 'formal' COMMENT 'publish type gray or formal',
  `gray_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'gray name',
  `ext_info` longtext COLLATE utf8_bin COMMENT 'ext info',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` VALUES (0, 1, '._DEFAULT_GROUP', '__MACOSX', '', '\0\0\0\0Mac OS X        \0\0\0\0	\0\0\02\0\0\0q\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ATTR\0\0\0\0\0\0\0�\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0com.apple.provenance\0\0\0��枪d��', '018ab73f3e966801a92e962b50592ed2', '2025-09-15 10:45:30', '2025-09-15 02:45:31', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 2, 'koi-suite.yaml', 'DEFAULT_GROUP', '', '# 日志采集策略\nextend:\n  boot:\n    log:\n      strategy: feign\n  feign:\n    # 自动获取TOKEN插件\n    plugin:\n      enabled: true\n      token:\n        enabled: true\n        load-balance: true\n        login:\n          client-id: pc-web\n          client-secret: pc-web\n          password: 123456\n          tenant-code: 0\n          username: admin\n        uri: http://koi-iam/token/login\n  mybatis-plus:\n    intercept:\n      data-permission:\n        remote: true\n    multi-tenant:\n      # 租户隔离策略（表字段隔离\n      include-tables: t_file_storage,t_file_storage_setting\n      strategy: feign\n      type: column\n      #租户隔离策略（动态数据源隔离）\n    #   type: datasource\n    #   ds-interceptor: true\n    #   db-notify: redis\nlogging:\n  level:\n    com:\n      wemirr: debug\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\nspringdoc:\n  packages-to-scan: com.koi\n', '26946be376925091b4200989e14a19f6', '2025-09-15 10:45:31', '2025-09-15 02:45:32', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 3, 'cloud-default.yaml', 'DEFAULT_GROUP', '', 'feign:\n  httpclient:\n    enabled: true # HttpClient的开关\n    connection-timeout: 5000       # 连接超时（毫秒）\n    time-to-live: 3                # 连接存活时长\n    time-to-live-unit: minutes     # 连接存活时长单位\n    max-connections: 200  # 最大连接数\n    max-connections-per-route: 50   # 单个请求路径的最大连接数', '1254e0b93f981cf493538ff7529b04c6', '2025-09-15 10:45:32', '2025-09-15 02:45:32', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 4, 'security.yaml', 'DEFAULT_GROUP', '', '############## Sa-Token 配置 (文档: https://sa-token.cc) ##############\nsa-token:\n  # token 名称（同时也是 cookie 名称）\n  #  token-name: wp-token\n  token-name: Authorization\n  token-prefix: Bearer\n  # token 有效期（单位：秒） 默认30天，-1 代表永久有效\n  timeout: 86400\n  # token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结\n  active-timeout: -1\n  # 是否允许同一账号多地同时登录 （为 true 时允许一起登录, 为 false 时新登录挤掉旧登录）\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个 token （为 true 时所有登录共用一个 token, 为 false 时每次登录新建一个 token）\n  is-share: true\n  # token 风格（默认可取值：uuid、simple-uuid、random-32、random-64、random-128、tik）\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: true\n', '087a51d50fdde236f5434cae90218ab8', '2025-09-15 10:45:33', '2025-09-15 02:45:33', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 5, 'koi-gateway.yaml', 'DEFAULT_GROUP', '', '# 端点放行\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# rabbon 超时时间设置\nribbon:\n  ConnectTimeout: 5000000\n  MaxAutoRetries: 0\n  MaxAutoRetriesNextServer: 1\n  ReadTimeout: 5000000\n\n# 跨域配置\nspring:\n  cloud:\n    gateway:\n      globalcors:\n        cors-configurations:\n          \'[/**]\':  # 匹配所有路径\n            allow-credentials: true  # 允许携带凭证\n            allowedHeaders:\n              - \"*\"  # 允许所有请求头\n            allowed-origin-patterns:\n              - \"*\"  # 允许所有源（注意：生产环境不建议使用*，应指定具体域名）\n            allowedMethods:\n              - \"*\"  # 允许所有HTTP方法（GET、POST、PUT等）', 'fe04d07d4cede25b0d8d8f92a7997cf1', '2025-09-15 10:45:34', '2025-09-15 02:45:34', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 6, 'mybatis-plus.yaml', 'DEFAULT_GROUP', '', 'mybatis-plus:\n  mapper-locations:\n    - classpath:com/koi/**/mapper/**/*.xml\n  #实体扫描，多个package用逗号或者分号分隔\n  type-aliases-package: com.koi.**.entity,com.koi.**.handler.type\n  type-handlers-package: com.koi.**.handler.type\n  global-config:\n    db-config:\n      id-type: auto\n      insert-strategy: NOT_NULL\n      update-strategy: NOT_NULL\n      where-strategy: not_empty\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: false', '98638950d4e258498d5c8d837b27e326', '2025-09-15 10:45:34', '2025-09-15 02:45:35', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 7, 'koi-iam.yaml', 'DEFAULT_GROUP', '', 'extend:\n  mybatis-plus:\n    multi-tenant:\n      # 可以组合数据源+字段独立隔离\n      include-tables: t_user,sys_role,sys_org,sys_position,c_login_log,c_opt_log,t_tenant_dict,t_tenant_dict_item\n      strategy: local\n      type: column\n      # 租户隔离策略（动态数据源隔离）\n    #   db-notify: redis\n    #   type: datasource\n\n\n  # 启用 Redis 扩展\n  redis:\n    enabled: true\n    lock:\n      enabled: true\n\n# 日志配置\nlogging:\n  level:\n    com:\n      wemirr: debug\n    springfox:\n      documentation: warn\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      # 如果不需要开启动态数据源,请关闭 AOP 切换\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\n  main:\n    allow-bean-definition-overriding: true\n\n  # 模板文件\n  thymeleaf:\n    prefix: classpath:/templates/\n', 'e14d1fd0f05aa58dff00f400b4c2ae3c', '2025-09-15 10:45:35', '2025-09-15 02:45:35', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 8, 'redis.yaml', 'DEFAULT_GROUP', '', '# Redis 连接\nspring:\n  cache:\n    redis:\n      cache-null-values: false\n      time-to-live: 2H\n    type: redis\n  data:\n    redis:\n      database: 2\n      host: 127.0.0.1\n      port: 6379\n      # password: 123456', '8e43ea117736d634e558c9799f0ba9da', '2025-09-15 10:45:35', '2025-09-15 02:45:36', 'nacos', '10.11.65.22', 'I', '', '', 'formal', '', NULL);
INSERT INTO `his_config_info` VALUES (1, 9, '._DEFAULT_GROUP', '__MACOSX', '', '\0\0\0\0Mac OS X        \0\0\0\0	\0\0\02\0\0\0q\0\0\0\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0ATTR\0\0\0\0\0\0\0�\0\0\0�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\0\0\0\0\0com.apple.provenance\0\0\0��枪d��', '018ab73f3e966801a92e962b50592ed2', '2025-09-15 10:45:50', '2025-09-15 02:45:50', 'nacos', '10.11.65.22', 'D', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (8, 10, 'redis.yaml', 'DEFAULT_GROUP', '', '# Redis 连接\nspring:\n  cache:\n    redis:\n      cache-null-values: false\n      time-to-live: 2H\n    type: redis\n  data:\n    redis:\n      database: 2\n      host: 127.0.0.1\n      port: 6379\n      # password: 123456', '8e43ea117736d634e558c9799f0ba9da', '2025-09-15 10:46:02', '2025-09-15 02:46:02', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (7, 11, 'koi-iam.yaml', 'DEFAULT_GROUP', '', 'extend:\n  mybatis-plus:\n    multi-tenant:\n      # 可以组合数据源+字段独立隔离\n      include-tables: t_user,sys_role,sys_org,sys_position,c_login_log,c_opt_log,t_tenant_dict,t_tenant_dict_item\n      strategy: local\n      type: column\n      # 租户隔离策略（动态数据源隔离）\n    #   db-notify: redis\n    #   type: datasource\n\n\n  # 启用 Redis 扩展\n  redis:\n    enabled: true\n    lock:\n      enabled: true\n\n# 日志配置\nlogging:\n  level:\n    com:\n      wemirr: debug\n    springfox:\n      documentation: warn\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      # 如果不需要开启动态数据源,请关闭 AOP 切换\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\n  main:\n    allow-bean-definition-overriding: true\n\n  # 模板文件\n  thymeleaf:\n    prefix: classpath:/templates/\n', 'e14d1fd0f05aa58dff00f400b4c2ae3c', '2025-09-15 10:46:18', '2025-09-15 02:46:19', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (6, 12, 'mybatis-plus.yaml', 'DEFAULT_GROUP', '', 'mybatis-plus:\n  mapper-locations:\n    - classpath:com/koi/**/mapper/**/*.xml\n  #实体扫描，多个package用逗号或者分号分隔\n  type-aliases-package: com.koi.**.entity,com.koi.**.handler.type\n  type-handlers-package: com.koi.**.handler.type\n  global-config:\n    db-config:\n      id-type: auto\n      insert-strategy: NOT_NULL\n      update-strategy: NOT_NULL\n      where-strategy: not_empty\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: false', '98638950d4e258498d5c8d837b27e326', '2025-09-15 10:46:49', '2025-09-15 02:46:49', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (5, 13, 'koi-gateway.yaml', 'DEFAULT_GROUP', '', '# 端点放行\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# rabbon 超时时间设置\nribbon:\n  ConnectTimeout: 5000000\n  MaxAutoRetries: 0\n  MaxAutoRetriesNextServer: 1\n  ReadTimeout: 5000000\n\n# 跨域配置\nspring:\n  cloud:\n    gateway:\n      globalcors:\n        cors-configurations:\n          \'[/**]\':  # 匹配所有路径\n            allow-credentials: true  # 允许携带凭证\n            allowedHeaders:\n              - \"*\"  # 允许所有请求头\n            allowed-origin-patterns:\n              - \"*\"  # 允许所有源（注意：生产环境不建议使用*，应指定具体域名）\n            allowedMethods:\n              - \"*\"  # 允许所有HTTP方法（GET、POST、PUT等）', 'fe04d07d4cede25b0d8d8f92a7997cf1', '2025-09-15 10:46:59', '2025-09-15 02:47:00', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (4, 14, 'security.yaml', 'DEFAULT_GROUP', '', '############## Sa-Token 配置 (文档: https://sa-token.cc) ##############\nsa-token:\n  # token 名称（同时也是 cookie 名称）\n  #  token-name: wp-token\n  token-name: Authorization\n  token-prefix: Bearer\n  # token 有效期（单位：秒） 默认30天，-1 代表永久有效\n  timeout: 86400\n  # token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结\n  active-timeout: -1\n  # 是否允许同一账号多地同时登录 （为 true 时允许一起登录, 为 false 时新登录挤掉旧登录）\n  is-concurrent: true\n  # 在多人登录同一账号时，是否共用一个 token （为 true 时所有登录共用一个 token, 为 false 时每次登录新建一个 token）\n  is-share: true\n  # token 风格（默认可取值：uuid、simple-uuid、random-32、random-64、random-128、tik）\n  token-style: uuid\n  # 是否输出操作日志\n  is-log: true\n', '087a51d50fdde236f5434cae90218ab8', '2025-09-15 10:47:12', '2025-09-15 02:47:12', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (3, 15, 'cloud-default.yaml', 'DEFAULT_GROUP', '', 'feign:\n  httpclient:\n    enabled: true # HttpClient的开关\n    connection-timeout: 5000       # 连接超时（毫秒）\n    time-to-live: 3                # 连接存活时长\n    time-to-live-unit: minutes     # 连接存活时长单位\n    max-connections: 200  # 最大连接数\n    max-connections-per-route: 50   # 单个请求路径的最大连接数', '1254e0b93f981cf493538ff7529b04c6', '2025-09-15 10:47:21', '2025-09-15 02:47:22', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (2, 16, 'koi-suite.yaml', 'DEFAULT_GROUP', '', '# 日志采集策略\nextend:\n  boot:\n    log:\n      strategy: feign\n  feign:\n    # 自动获取TOKEN插件\n    plugin:\n      enabled: true\n      token:\n        enabled: true\n        load-balance: true\n        login:\n          client-id: pc-web\n          client-secret: pc-web\n          password: 123456\n          tenant-code: 0\n          username: admin\n        uri: http://koi-iam/token/login\n  mybatis-plus:\n    intercept:\n      data-permission:\n        remote: true\n    multi-tenant:\n      # 租户隔离策略（表字段隔离\n      include-tables: t_file_storage,t_file_storage_setting\n      strategy: feign\n      type: column\n      #租户隔离策略（动态数据源隔离）\n    #   type: datasource\n    #   ds-interceptor: true\n    #   db-notify: redis\nlogging:\n  level:\n    com:\n      wemirr: debug\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\nspringdoc:\n  packages-to-scan: com.koi\n', '26946be376925091b4200989e14a19f6', '2025-09-15 10:47:39', '2025-09-15 02:47:40', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (2, 17, 'koi-suite.yaml', 'DEFAULT_GROUP', '', '# 日志采集策略\nextend:\n  boot:\n    log:\n      strategy: feign\n  feign:\n    # 自动获取TOKEN插件\n    plugin:\n      enabled: true\n      token:\n        enabled: true\n        load-balance: true\n        login:\n          client-id: pc-web\n          client-secret: pc-web\n          password: 123456\n          tenant-code: 0\n          username: admin\n        uri: http://koi-iam/token/login\n  mybatis-plus:\n    intercept:\n      data-permission:\n        remote: true\n    multi-tenant:\n      # 租户隔离策略（表字段隔离\n      include-tables: t_file_storage,t_file_storage_setting\n      strategy: feign\n      type: column\n      #租户隔离策略（动态数据源隔离）\n    #   type: datasource\n    #   ds-interceptor: true\n    #   db-notify: redis\nlogging:\n  level:\n    com:\n      wemirr: debug\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\nspringdoc:\n  packages-to-scan: com.koi\n', '26946be376925091b4200989e14a19f6', '2025-09-15 10:48:14', '2025-09-15 02:48:15', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"type\":\"yaml\",\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (2, 18, 'koi-suite.yaml', 'DEFAULT_GROUP', '', '# 日志采集策略\nextend:\n  boot:\n    log:\n      strategy: feign\n  feign:\n    # 自动获取TOKEN插件\n    plugin:\n      enabled: true\n      token:\n        enabled: true\n        load-balance: true\n        login:\n          client-id: pc-web\n          client-secret: pc-web\n          password: 123456\n          tenant-code: 0\n          username: admin\n        uri: http://koi-iam/token/login\n  mybatis-plus:\n    intercept:\n      data-permission:\n        remote: true\n    multi-tenant:\n      # 租户隔离策略（表字段隔离\n      include-tables: t_file_storage,t_file_storage_setting\n      strategy: feign\n      type: column\n      #租户隔离策略（动态数据源隔离）\n    #   type: datasource\n    #   ds-interceptor: true\n    #   db-notify: redis\nlogging:\n  level:\n    com:\n      wemirr: debug\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: koi\n          password: dBkJj28ZRN7EnHhY\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\nspringdoc:\n  packages-to-scan: com.koi\n', 'ff4c21e46ab71abdb1d3aa15738431fe', '2025-09-15 10:48:59', '2025-09-15 02:48:59', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"type\":\"yaml\",\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (7, 19, 'koi-iam.yaml', 'DEFAULT_GROUP', '', 'extend:\n  mybatis-plus:\n    multi-tenant:\n      # 可以组合数据源+字段独立隔离\n      include-tables: t_user,sys_role,sys_org,sys_position,c_login_log,c_opt_log,t_tenant_dict,t_tenant_dict_item\n      strategy: local\n      type: column\n      # 租户隔离策略（动态数据源隔离）\n    #   db-notify: redis\n    #   type: datasource\n\n\n  # 启用 Redis 扩展\n  redis:\n    enabled: true\n    lock:\n      enabled: true\n\n# 日志配置\nlogging:\n  level:\n    com:\n      wemirr: debug\n    springfox:\n      documentation: warn\n\n# 端点配置\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# 默认数据源\nspring:\n  datasource:\n    dynamic:\n      # 如果不需要开启动态数据源,请关闭 AOP 切换\n      aop:\n        enabled: false\n      primary: master\n      datasource:\n        master:\n          driver-class-name: com.mysql.cj.jdbc.Driver\n          pool-name: HikariDataSourcePool\n          type: com.zaxxer.hikari.HikariDataSource\n          url: jdbc:mysql://127.0.0.1:3306/koi?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true\n          username: root\n          password: root\n      hikari:\n        connection-timeout: 30000\n        is-auto-commit: true\n        max-lifetime: 1800000\n        max-pool-size: 15\n        min-idle: 5\n      strict: true\n  main:\n    allow-bean-definition-overriding: true\n\n  # 模板文件\n  thymeleaf:\n    prefix: classpath:/templates/\n', 'e14d1fd0f05aa58dff00f400b4c2ae3c', '2025-09-15 10:49:25', '2025-09-15 02:49:26', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"type\":\"yaml\",\"src_user\":\"nacos\"}');
INSERT INTO `his_config_info` VALUES (5, 20, 'koi-gateway.yaml', 'DEFAULT_GROUP', '', '# 端点放行\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# rabbon 超时时间设置\nribbon:\n  ConnectTimeout: 5000000\n  MaxAutoRetries: 0\n  MaxAutoRetriesNextServer: 1\n  ReadTimeout: 5000000\n\n# 跨域配置\nspring:\n  cloud:\n    gateway:\n      globalcors:\n        cors-configurations:\n          \'[/**]\':  # 匹配所有路径\n            allow-credentials: true  # 允许携带凭证\n            allowedHeaders:\n              - \"*\"  # 允许所有请求头\n            allowed-origin-patterns:\n              - \"*\"  # 允许所有源（注意：生产环境不建议使用*，应指定具体域名）\n            allowedMethods:\n              - \"*\"  # 允许所有HTTP方法（GET、POST、PUT等）', 'fe04d07d4cede25b0d8d8f92a7997cf1', '2025-09-15 10:50:44', '2025-09-15 02:50:44', 'nacos', '10.11.65.22', 'U', '', '', 'formal', '', '{\"type\":\"yaml\",\"src_user\":\"nacos\"}');
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL COMMENT 'role',
  `resource` varchar(128) NOT NULL COMMENT 'resource',
  `action` varchar(8) NOT NULL COMMENT 'action',
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL COMMENT 'username',
  `role` varchar(50) NOT NULL COMMENT 'role',
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL COMMENT 'username',
  `password` varchar(500) NOT NULL COMMENT 'password',
  `enabled` tinyint(1) NOT NULL COMMENT 'enabled',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$oBSKE4kFL4a4QTAtKF2mI.X1B265XUVLCS3cjLz6YgDsGRzmoBEBW', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
