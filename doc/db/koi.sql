/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3306
 Source Schema         : koi

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 09/01/2025 17:29:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_message_channel
-- ----------------------------
DROP TABLE IF EXISTS `b_message_channel`;
CREATE TABLE `b_message_channel` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道标题',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息类型',
  `status` bit(1) DEFAULT b'1' COMMENT '状态（0=禁用;1=启用）',
  `setting` json DEFAULT NULL COMMENT '设置（JSON）',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1864548132428439555 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_channel
-- ----------------------------
BEGIN;
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864537666578714625, '系统内置', 'system', b'1', NULL, 1, '系统内置 webstock 消息', b'0', NULL, NULL, '2024-12-05 13:09:56', NULL, NULL, '2024-12-05 14:17:23');
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864543691579056130, '213123', NULL, b'1', NULL, 1, '12312', b'0', NULL, NULL, '2024-12-05 13:33:52', NULL, NULL, '2024-12-05 05:58:16');
INSERT INTO `b_message_channel` (`id`, `title`, `type`, `status`, `setting`, `tenant_id`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864548132428439554, '腾讯邮箱', 'email', b'1', '{\"id\": \"1864548132428439554\", \"host\": \"smtp.qq.com\", \"port\": \"465\", \"smtp\": {\"ssl\": true, \"auth\": true}, \"type\": \"email\", \"title\": \"腾讯邮箱\", \"status\": true, \"password\": \"123\", \"protocol\": \"smtp\", \"tenantId\": \"1\", \"username\": \"123@qq.com\", \"description\": \"描述\"}', 1, '描述', b'0', NULL, NULL, '2024-12-05 13:51:31', NULL, NULL, '2024-12-05 06:31:14');
COMMIT;

-- ----------------------------
-- Table structure for b_message_notify
-- ----------------------------
DROP TABLE IF EXISTS `b_message_notify`;
CREATE TABLE `b_message_notify` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息类型',
  `template_id` bigint DEFAULT NULL COMMENT '消息模板ID',
  `variables` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息变量',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` bigint DEFAULT NULL COMMENT '接收用户ID',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `subscribe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订阅人 比如 邮箱,手机号,钉钉ID等',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1871423081856249858 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知';

-- ----------------------------
-- Records of b_message_notify
-- ----------------------------
BEGIN;
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868884501955334145, '系统消息', 'email', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 1, '平台管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-17 13:02:42', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868884501963722753, '系统消息', 'ding-talk', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 1, '平台管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-17 13:02:42', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868884501967917058, '系统消息', 'sms', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 1, '平台管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-17 13:02:42', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871391176511336450, '系统消息', 'email', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 24, '测试管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 11:03:20', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871391176616194050, '系统消息', 'ding-talk', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 24, '测试管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 11:03:20', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871391176721051650, '系统消息', 'sms', 1863835249327370241, '{\"username\":\"1\"}', '欢迎 1 来到 WP 系统', 24, '测试管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 11:03:20', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871391823688249346, '系统消息', 'email', 1863835249327370241, '{\"username\":\"\"}', '欢迎  来到 WP 系统', 1863475850498969601, '2323', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 11:05:54', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871391823797301250, '系统消息', 'ding-talk', 1863835249327370241, '{\"username\":\"\"}', '欢迎  来到 WP 系统', 1863475850498969601, '2323', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 11:05:54', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871391823910547458, '系统消息', 'sms', 1863835249327370241, '{\"username\":\"\"}', '欢迎  来到 WP 系统', 1863475850498969601, '2323', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 11:05:54', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871422439070773250, '系统消息', 'email', 1863835249327370241, '{\"username\":\"2133\",\"key_1\":\"123123\"}', '欢迎 2133 来到 WP 系统', 1863475850498969601, '2323', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 13:07:33', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871422439179825153, '系统消息', 'ding-talk', 1863835249327370241, '{\"username\":\"2133\",\"key_1\":\"123123\"}', '欢迎 2133 来到 WP 系统', 1863475850498969601, '2323', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 13:07:33', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871422439293071361, '系统消息', 'sms', 1863835249327370241, '{\"username\":\"2133\",\"key_1\":\"123123\"}', '欢迎 2133 来到 WP 系统', 1863475850498969601, '2323', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 13:07:33', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871423081650728961, '系统消息', 'email', 1863835249327370241, '{\"username\":\"测试一下\"}', '欢迎 测试一下 来到 WP 系统', 24, '测试管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 13:10:07', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871423081759780865, '系统消息', 'ding-talk', 1863835249327370241, '{\"username\":\"测试一下\"}', '欢迎 测试一下 来到 WP 系统', 24, '测试管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 13:10:07', NULL, NULL, NULL);
INSERT INTO `b_message_notify` (`id`, `title`, `type`, `template_id`, `variables`, `content`, `user_id`, `nickname`, `subscribe`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871423081856249857, '系统消息', 'sms', 1863835249327370241, '{\"username\":\"测试一下\"}', '欢迎 测试一下 来到 WP 系统', 24, '测试管理员', '1837307557@qq.com', 1, b'0', 1, '平台管理员', '2024-12-24 13:10:07', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for b_message_template
-- ----------------------------
DROP TABLE IF EXISTS `b_message_template`;
CREATE TABLE `b_message_template` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板类型',
  `subject` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息标题（如邮件标题）',
  `status` bit(1) DEFAULT b'1' COMMENT '状态（0=禁用;1=启用）',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1863835249327370242 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of b_message_template
-- ----------------------------
BEGIN;
INSERT INTO `b_message_template` (`id`, `code`, `name`, `type`, `subject`, `content`, `status`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1863835249327370241, '0001', '系统消息', 'email,ding-talk,sms', '系统消息', '欢迎 ${username} 来到 WP 系统', b'1', NULL, b'0', NULL, NULL, '2024-12-03 14:38:46', NULL, NULL, '2024-12-06 11:09:43');
COMMIT;

-- ----------------------------
-- Table structure for c_diff_log
-- ----------------------------
DROP TABLE IF EXISTS `c_diff_log`;
CREATE TABLE `c_diff_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `variables` json DEFAULT NULL COMMENT '变量信息，JSON格式',
  `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '服务名称',
  `business_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务组',
  `business_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务标签',
  `business_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务关键字',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '描述信息',
  `status` int DEFAULT NULL COMMENT '状态',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `created_by` bigint DEFAULT NULL COMMENT '创建者ID',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者名称',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `extra` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '额外信息，JSON格式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='通用差异日志表';

-- ----------------------------
-- Records of c_diff_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_generate
-- ----------------------------
DROP TABLE IF EXISTS `c_generate`;
CREATE TABLE `c_generate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `root_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '根目录',
  `springdoc` bit(2) DEFAULT b'0' COMMENT '是否添加swagger2',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表名',
  `parent_package` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父包',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模块名',
  `table_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表前缀',
  `api_url_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'api地址前缀',
  `logic_delete_field` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '逻辑删除字段',
  `platform_id` bigint DEFAULT '0' COMMENT '平台ID',
  `deleted` bit(2) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代码生成器';

-- ----------------------------
-- Records of c_generate
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_data
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_data`;
CREATE TABLE `c_i18n_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一标识 = 业务:关键词',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0' COMMENT '删除状态0：未删除，1：已删除',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1724355494514126850 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_data
-- ----------------------------
BEGIN;
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 'SEX.1', '性别->男', 1, b'0', 0, NULL, '2024-07-19 09:06:38', 0, NULL, '2024-07-19 09:18:35');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 'SEX.2', '性别->女', 1, b'0', 0, NULL, '2024-07-19 09:06:38', 0, NULL, '2024-07-19 09:18:35');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1721821542981042178, 'i18n.db', '测试中文内容 {i18n.demo}', 1, b'0', 1, '长风一梦0000', '2023-11-07 17:27:00', NULL, NULL, '2024-12-06 17:09:22');
INSERT INTO `c_i18n_data` (`id`, `code`, `remark`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1724355494514126849, '123', '213', 1, b'1', 1, '长风一梦0000', '2023-11-14 17:16:02', 0, NULL, '2023-11-14 09:16:04');
COMMIT;

-- ----------------------------
-- Table structure for c_i18n_locale_message
-- ----------------------------
DROP TABLE IF EXISTS `c_i18n_locale_message`;
CREATE TABLE `c_i18n_locale_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint DEFAULT NULL COMMENT 'i18n_data.id',
  `locale` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '语言',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文本值，可以使用 {} 加角标，作为占位符',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1864960309274497027 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='国际化信息';

-- ----------------------------
-- Records of c_i18n_locale_message
-- ----------------------------
BEGIN;
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (10, 1, 'zh_CN', '男', 1, NULL, '2024-07-19 09:07:18');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (11, 1, 'en_US', 'Male', 1, NULL, '2024-07-19 09:07:31');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (20, 2, 'zh_CN', '女', 0, NULL, '2024-07-19 09:19:03');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (21, 2, 'en_US', 'Female', 0, NULL, '2024-07-19 09:19:05');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (1864960309261914113, 1721821542981042178, 'en_US', 'English Context', NULL, NULL, '2024-12-06 17:09:22');
INSERT INTO `c_i18n_locale_message` (`id`, `parent_id`, `locale`, `message`, `created_by`, `created_name`, `created_time`) VALUES (1864960309274497026, 1721821542981042178, 'zh_CN', '测试中文内容 {i18n.demo}', NULL, NULL, '2024-12-06 17:09:22');
COMMIT;

-- ----------------------------
-- Table structure for c_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_login_log`;
CREATE TABLE `c_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录IP',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录地点',
  `client_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人客户端ID',
  `principal` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录人账号',
  `platform` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台',
  `engine` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '引擎类型',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作系统',
  `login_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录类型',
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='登录日志';

-- ----------------------------
-- Records of c_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for c_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_opt_log`;
CREATE TABLE `c_opt_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
  `module` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志模块',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作描述',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作IP（支持IPv6）',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `trace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '日志链路追踪id日志标志',
  `action` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类路径',
  `uri` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求地址',
  `http_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT 'GET' COMMENT '请求类型ENUM(''GET'', ''POST'', ''PUT'', ''DELETE'', ''PATCH'', ''TRACE'', ''HEAD'', ''OPTIONS'')',
  `request` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '请求参数',
  `response` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '返回值',
  `message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '执行消息',
  `status` bit(1) DEFAULT NULL COMMENT '日志状态（true=正常;false=异常）',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `duration` bigint DEFAULT '0' COMMENT '消耗时间',
  `browser` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器名称',
  `os` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作系统',
  `engine` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器引擎',
  `platform` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '平台信息',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求令牌',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统日志';

-- ----------------------------
-- Records of c_opt_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for plat_product_def_res
-- ----------------------------
DROP TABLE IF EXISTS `plat_product_def_res`;
CREATE TABLE `plat_product_def_res` (
  `product_id` bigint NOT NULL COMMENT '角色ID',
  `res_id` bigint NOT NULL COMMENT '菜单ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `idx_role_res` (`product_id`,`res_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色权限表';

-- ----------------------------
-- Records of plat_product_def_res
-- ----------------------------
BEGIN;
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 11, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 1101, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 1102, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 110101, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 110102, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 110107, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 110201, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 110202, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 110203, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871786068400021505, 110204, '2024-12-25 05:12:39');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 151, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 152, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 153, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 301, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 302, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 303, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 306, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 310, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 320, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15101, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15102, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15103, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15201, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15202, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15203, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15204, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15205, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15206, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15207, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 15301, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30101, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30102, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30103, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30201, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30202, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30301, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30303, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30604, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30605, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30606, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30607, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 30608, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 31003, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 31005, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 31006, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 32002, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 32003, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010101, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010102, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010103, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010104, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010105, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010201, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010202, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010203, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010301, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010302, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3010303, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020101, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020102, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020103, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020201, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020202, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020203, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020207, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3020208, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3060501, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3060502, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3060503, '2024-12-25 05:53:27');
INSERT INTO `plat_product_def_res` (`product_id`, `res_id`, `created_time`) VALUES (1871796003473084418, 3060504, '2024-12-25 05:53:27');
COMMIT;

-- ----------------------------
-- Table structure for plat_product_definition
-- ----------------------------
DROP TABLE IF EXISTS `plat_product_definition`;
CREATE TABLE `plat_product_definition` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '产品定义ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '产品名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品Logo链接',
  `status` tinyint(1) DEFAULT '1' COMMENT '启用状态（租户字典 0=禁用;1=启用）',
  `deleted` bit(1) DEFAULT b'0',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '产品详情',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1871796003473084419 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品定义表';

-- ----------------------------
-- Records of plat_product_definition
-- ----------------------------
BEGIN;
INSERT INTO `plat_product_definition` (`id`, `name`, `code`, `logo`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871796003473084418, '租户测试项目', 'PD202412250002', 'http://127.0.0.1:19000/wp-local/dev/676b9d7b91326cc9f14145b2.png', 1, b'0', '租户测试项目', 1, '平台管理员', '2024-12-25 13:51:58', 0, NULL, '2024-12-25 05:52:06');
COMMIT;

-- ----------------------------
-- Table structure for plat_product_pricing
-- ----------------------------
DROP TABLE IF EXISTS `plat_product_pricing`;
CREATE TABLE `plat_product_pricing` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '定价名称',
  `users` int DEFAULT NULL COMMENT '用户数量',
  `months` int DEFAULT NULL COMMENT '月数',
  `price` decimal(10,0) DEFAULT NULL COMMENT '总价',
  `price_per_user` decimal(10,0) DEFAULT NULL COMMENT '每用户单价',
  `over_price` decimal(10,2) DEFAULT NULL COMMENT '超额单价',
  `status` bit(1) DEFAULT b'0' COMMENT '状态(1=启用;0=禁用)',
  `description` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
  `deleted` bit(1) DEFAULT b'0' COMMENT '逻辑删除（0=未删除;1=已删除）',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品定价表';

-- ----------------------------
-- Records of plat_product_pricing
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for plat_product_subscription
-- ----------------------------
DROP TABLE IF EXISTS `plat_product_subscription`;
CREATE TABLE `plat_product_subscription` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_id` bigint DEFAULT NULL COMMENT '产品ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `users` int DEFAULT NULL COMMENT '用户数量',
  `months` int DEFAULT NULL COMMENT '月数',
  `license_price` decimal(19,2) DEFAULT NULL COMMENT '用户单价',
  `total_amount` decimal(19,2) DEFAULT NULL COMMENT '总金额',
  `discount_amount` decimal(19,2) DEFAULT NULL COMMENT '优惠金额',
  `statement_amount` decimal(19,2) DEFAULT NULL COMMENT '结算金额',
  `statement_price` decimal(19,2) DEFAULT NULL COMMENT '结算单价',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `payment_status` tinyint DEFAULT NULL COMMENT '支付状态(0=待支付;10=部分支付;20=已支付)',
  `description` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1871796594064633859 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='产品订阅信息';

-- ----------------------------
-- Records of plat_product_subscription
-- ----------------------------
BEGIN;
INSERT INTO `plat_product_subscription` (`id`, `product_id`, `tenant_id`, `users`, `months`, `license_price`, `total_amount`, `discount_amount`, `statement_amount`, `statement_price`, `start_time`, `end_time`, `payment_status`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871796594064633858, 1871796003473084418, 3, 10000, 12, 1.00, 120000.00, 0.00, 120000.00, 1.00, '2024-12-25 13:54:06', '2025-12-25 13:54:06', 20, '测试项目', b'0', 1, '平台管理员', '2024-12-25 13:54:19', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_data_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_permission_resource`;
CREATE TABLE `sys_data_permission_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner_id` bigint NOT NULL COMMENT '拥有者',
  `owner_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '拥有类型（角色，用户）',
  `data_id` bigint NOT NULL COMMENT '数据ID',
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '数据类型（机构、角色、租户等等）',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1813866396912828422 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='数据权限资源表';

-- ----------------------------
-- Records of sys_data_permission_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '编码\r\n一颗树仅仅有一个统一的编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `type` tinyint DEFAULT '0' COMMENT '字典类型(0=平台字典;1=租户字典)',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `sequence` tinyint DEFAULT '0' COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1873605395382255618 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 'NATION', '民族', 0, '123', b'1', 0, b'0', 0, '平台管理员', '2019-06-01 09:42:50', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 'POSITION_STATUS', '在职状态', 0, '11', b'1', 1, b'0', 0, '平台管理员', '2019-06-04 11:37:15', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 'EDUCATION', '学历', 0, '', b'1', 2, b'0', 0, '平台管理员', '2019-06-04 11:33:52', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (4, 'AREA_LEVEL', '行政区级', 0, '', b'1', 3, b'0', 0, '平台管理员', '2020-01-20 15:12:05', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5, 'ORG_TYPE', '机构类型', 0, '', b'1', 4, b'0', 0, '平台管理员', '2020-08-19 15:02:57', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (6, 'SEX', '性别', 0, '性别', b'1', 5, b'0', 0, '平台管理员', '2020-11-18 07:24:43', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (7, 'NOTICE', '消息类型', 0, '消息类型', b'1', 6, b'0', 0, '平台管理员', '2020-11-19 02:57:22', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (8, 'POSITION_TYPE', '岗位类型', 0, NULL, b'1', 7, b'0', 0, '平台管理员', '2021-07-16 03:34:04', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (9, 'COLOR', '颜色', 0, NULL, b'1', 8, b'0', 0, '平台管理员', '2021-07-16 04:12:21', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10, 'INDUSTRY', '行业类型', 0, '行业类型', b'1', 9, b'0', 0, '平台管理员', '2021-07-31 08:10:07', 10, '系统管理员', '2024-12-18 02:27:08');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (11, 'STATUS', '启停状态', 1, NULL, b'1', 1, b'0', 0, '平台管理员', '2024-12-18 02:26:54', 10, '系统管理员', '2024-12-18 02:27:11');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1686979951801745410, 'CONTRACT_SEAL_TYPE', '用印类型', 1, NULL, b'1', 0, b'0', 1, '平台管理员', '2023-08-03 05:58:57', 10, '系统管理员', '2024-12-18 02:27:11');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1698634189967577089, 'PAYMENT_METHOD', '付款方法', 1, NULL, b'1', 0, b'0', 1, '平台管理员', '2023-09-04 09:48:44', 10, '系统管理员', '2024-12-18 02:27:11');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1698634230920761345, 'PAYMENT_TYPE', '付款类型', 1, NULL, b'1', 0, b'0', 1, '平台管理员', '2023-09-04 09:48:54', 10, '系统管理员', '2024-12-18 02:27:11');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865614807147950081, 'TENANT-DICT', '租户字典', 1, '测试租户字典推送和同步', b'1', 1, b'0', 1, '平台管理员', '2024-12-08 12:30:06', 10, '系统管理员', '2024-12-18 02:27:11');
INSERT INTO `sys_dict` (`id`, `code`, `name`, `type`, `description`, `status`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1873605395382255617, '123', '123', 1, '123', b'1', 0, b'1', 1, '平台管理员', '2024-12-30 13:41:51', 1, '平台管理员', '2024-12-30 13:41:56');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dict_id` bigint DEFAULT NULL COMMENT '类型ID',
  `dict_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '编码',
  `label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '颜色',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dict_code_item_code_uniq` (`dict_code`,`value`) USING BTREE COMMENT '字典编码与字典项目编码联合唯一'
) ENGINE=InnoDB AUTO_INCREMENT=1865928793793933314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 4, 'AREA_LEVEL', 'COUNTRY', '国家', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-01-20 15:12:57', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 4, 'AREA_LEVEL', 'PROVINCE', '省份', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2020-01-20 15:13:45', 1, '长风一梦', '2021-07-31 08:18:51');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 4, 'AREA_LEVEL', 'CITY', '地市', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-01-20 15:14:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (4, 4, 'AREA_LEVEL', 'COUNTY', '区县', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2020-01-20 15:14:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (5, 5, 'ORG_TYPE', '01', '单位', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-08-19 15:03:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (6, 5, 'ORG_TYPE', '02', '部门', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-08-19 15:03:59', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (38, 3, 'EDUCATION', 'ZHUANKE', '专科', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (39, 3, 'EDUCATION', 'COLLEGE', '本科', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2019-06-04 11:36:19', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (40, 3, 'EDUCATION', 'SUOSHI', '硕士', b'1', 'warning', '', 6, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (41, 3, 'EDUCATION', 'BOSHI', '博士', b'1', 'warning', '', 7, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (42, 3, 'EDUCATION', 'BOSHIHOU', '博士后', b'1', 'warning', '', 8, b'0', 0, '系统管理员', '2019-06-04 11:36:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (43, 1, 'NATION', 'mz_hanz', '汉族', b'1', 'warning', '', 0, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (44, 1, 'NATION', 'mz_zz', '壮族', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (45, 1, 'NATION', 'mz_mz', '满族', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (46, 1, 'NATION', 'mz_hz', '回族', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (47, 1, 'NATION', 'mz_miaoz', '苗族', b'1', 'warning', '', 4, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (48, 1, 'NATION', 'mz_wwez', '维吾尔族', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (49, 1, 'NATION', 'mz_tjz', '土家族', b'1', 'warning', '', 6, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (50, 1, 'NATION', 'mz_yz', '彝族', b'1', 'warning', '', 7, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (51, 1, 'NATION', 'mz_mgz', '蒙古族', b'1', 'warning', '', 8, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (52, 1, 'NATION', 'mz_zhangz', '藏族', b'1', 'warning', '', 9, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (53, 1, 'NATION', 'mz_byz', '布依族', b'1', 'warning', '', 10, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (54, 1, 'NATION', 'mz_dz', '侗族', b'1', 'warning', '', 11, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (55, 1, 'NATION', 'mz_yaoz', '瑶族', b'1', 'warning', '', 12, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (56, 1, 'NATION', 'mz_cxz', '朝鲜族', b'1', 'warning', '', 13, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (57, 1, 'NATION', 'mz_bz', '白族', b'1', 'warning', '', 14, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (58, 1, 'NATION', 'mz_hnz', '哈尼族', b'1', 'warning', '', 15, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (59, 1, 'NATION', 'mz_hskz', '哈萨克族', b'1', 'warning', '', 16, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (60, 1, 'NATION', 'mz_lz', '黎族', b'1', 'warning', '', 17, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (61, 1, 'NATION', 'mz_daiz', '傣族', b'1', 'warning', '', 18, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (62, 1, 'NATION', 'mz_sz', '畲族', b'1', 'warning', '', 19, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (63, 1, 'NATION', 'mz_llz', '傈僳族', b'1', 'warning', '', 20, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (64, 1, 'NATION', 'mz_glz', '仡佬族', b'1', 'warning', '', 21, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (65, 1, 'NATION', 'mz_dxz', '东乡族', b'1', 'warning', '', 22, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (66, 1, 'NATION', 'mz_gsz', '高山族', b'1', 'warning', '', 23, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (67, 1, 'NATION', 'mz_lhz', '拉祜族', b'1', 'warning', '', 24, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (68, 1, 'NATION', 'mz_shuiz', '水族', b'1', 'warning', '', 25, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (69, 1, 'NATION', 'mz_wz', '佤族', b'1', 'warning', '', 26, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (70, 1, 'NATION', 'mz_nxz', '纳西族', b'1', 'warning', '', 27, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (71, 1, 'NATION', 'mz_qz', '羌族', b'1', 'warning', '', 28, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (72, 1, 'NATION', 'mz_tz', '土族', b'1', 'warning', '', 29, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (73, 1, 'NATION', 'mz_zlz', '仫佬族', b'1', 'warning', '', 30, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (74, 1, 'NATION', 'mz_xbz', '锡伯族', b'1', 'warning', '', 31, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (75, 1, 'NATION', 'mz_kehzz', '柯尔克孜族', b'1', 'warning', '', 32, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (76, 1, 'NATION', 'mz_dwz', '达斡尔族', b'1', 'warning', '', 33, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (77, 1, 'NATION', 'mz_jpz', '景颇族', b'1', 'warning', '', 34, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (78, 1, 'NATION', 'mz_mlz', '毛南族', b'1', 'warning', '', 35, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (79, 1, 'NATION', 'mz_slz', '撒拉族', b'1', 'warning', '', 36, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (80, 1, 'NATION', 'mz_tjkz', '塔吉克族', b'1', 'warning', '', 37, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (81, 1, 'NATION', 'mz_acz', '阿昌族', b'1', 'warning', '', 38, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (82, 1, 'NATION', 'mz_pmz', '普米族', b'1', 'warning', '', 39, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (83, 1, 'NATION', 'mz_ewkz', '鄂温克族', b'1', 'warning', '', 40, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (84, 1, 'NATION', 'mz_nz', '怒族', b'1', 'warning', '', 41, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (85, 1, 'NATION', 'mz_jz', '京族', b'1', 'warning', '', 42, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (86, 1, 'NATION', 'mz_jnz', '基诺族', b'1', 'warning', '', 43, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (87, 1, 'NATION', 'mz_daz', '德昂族', b'1', 'warning', '', 44, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (88, 1, 'NATION', 'mz_baz', '保安族', b'1', 'warning', '', 45, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (89, 1, 'NATION', 'mz_elsz', '俄罗斯族', b'1', 'warning', '', 46, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (90, 1, 'NATION', 'mz_ygz', '裕固族', b'1', 'warning', '', 47, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (91, 1, 'NATION', 'mz_wzbkz', '乌兹别克族', b'1', 'warning', '', 48, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (92, 1, 'NATION', 'mz_mbz', '门巴族', b'1', 'warning', '', 49, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (93, 1, 'NATION', 'mz_elcz', '鄂伦春族', b'1', 'warning', '', 50, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (94, 1, 'NATION', 'mz_dlz', '独龙族', b'1', 'warning', '', 51, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (95, 1, 'NATION', 'mz_tkez', '塔塔尔族', b'1', 'warning', '', 52, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (96, 1, 'NATION', 'mz_hzz', '赫哲族', b'1', 'warning', '', 53, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (97, 1, 'NATION', 'mz_lbz', '珞巴族', b'1', 'warning', '', 54, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (98, 1, 'NATION', 'mz_blz', '布朗族', b'1', 'warning', '', 55, b'0', 0, '系统管理员', '2018-03-15 20:11:01', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (99, 2, 'POSITION_STATUS', 'WORKING', '在职', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2019-06-04 11:38:16', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (100, 2, 'POSITION_STATUS', 'QUIT', '离职', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2019-06-04 11:38:50', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (101, 4, 'AREA_LEVEL', 'TOWNS', '乡镇', b'1', 'warning', '', 5, b'0', 0, '系统管理员', '2020-03-09 23:33:46', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (102, 3, 'EDUCATION', 'XIAOXUE', '小学', b'1', 'warning', '', 1, b'0', 0, '系统管理员', '2020-03-09 23:34:13', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (103, 3, 'EDUCATION', 'ZHONGXUE', '中学', b'1', 'warning', '', 2, b'0', 0, '系统管理员', '2020-03-09 23:34:32', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (104, 3, 'EDUCATION', 'GAOZHONG', '高中', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-03-09 23:34:40', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (105, 3, 'EDUCATION', 'QITA', '其他', b'1', 'warning', '', 20, b'0', 0, '系统管理员', '2020-03-09 23:34:54', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (106, 1, 'NATION', 'mz_qt', '其他', b'1', 'warning', '', 100, b'0', 0, '系统管理员', '2020-03-09 23:38:29', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (107, 2, 'POSITION_STATUS', 'LEAVE', '请假', b'1', 'warning', '', 3, b'0', 0, '系统管理员', '2020-03-09 23:39:30', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (108, 6, 'SEX', '1', '男', b'1', 'success', '男', 1, b'0', 0, '系统管理员', '2020-11-18 07:24:58', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (109, 6, 'SEX', '2', '女', b'1', 'error', '女', 1, b'0', 0, '系统管理员', '2020-11-18 07:25:07', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (110, 7, 'NOTICE', '0', '通知', b'1', 'success', '通知', 1, b'0', 0, '系统管理员', '2020-11-19 02:58:37', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (111, 7, 'NOTICE', '1', '消息', b'1', 'success', '消息', 1, b'0', 0, '系统管理员', '2020-11-19 02:58:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (112, 7, 'NOTICE', '2', '待办', b'1', 'error', '待办', 1, b'0', 0, '系统管理员', '2020-11-19 02:59:05', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (113, 8, 'POSITION_TYPE', '0', '基层', b'1', 'success', '1111', 1, b'0', 0, '系统管理员', '2021-07-16 04:06:54', 0, '系统管理员', '2024-12-08 10:23:52');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (114, 9, 'COLOR', 'success', '成功', b'1', 'success', '成功', 1, b'0', 0, '系统管理员', '2021-07-16 04:12:47', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (115, 9, 'COLOR', 'warning', '警告', b'1', 'warning', '警告', 1, b'0', 0, '系统管理员', '2021-07-16 04:15:06', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (116, 9, 'COLOR', 'error', '错误', b'1', 'error', '红色', 1, b'0', 0, '系统管理员', '2021-07-16 04:16:55', 0, '系统管理员', '2021-07-31 08:11:24');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (118, 8, 'POSITION_TYPE', '1', '中层', b'1', 'success', '中层', 1, b'0', 0, '系统管理员', '2021-07-16 04:59:51', 0, '系统管理员', '2024-12-08 10:23:49');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (119, 8, 'POSITION_TYPE', '2', '高层', b'1', 'warning', '高层', 1, b'0', 0, '系统管理员', '2021-07-16 05:00:07', 0, '系统管理员', '2024-12-08 10:23:50');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (120, 10, 'INDUSTRY', '1', '医疗', b'1', 'success', '医疗行业', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:08', NULL, NULL, '2024-12-08 11:37:08');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (121, 10, 'INDUSTRY', '2', '教育', b'1', 'success', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:17', NULL, NULL, '2021-07-31 08:20:14');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (122, 10, 'INDUSTRY', '3', '金融', b'1', 'success', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:28', NULL, NULL, '2021-07-31 08:20:17');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (123, 10, 'INDUSTRY', '4', '互联网', b'1', 'warning', '', 1, b'0', 1, '长风一梦', '2021-07-31 08:17:40', 1, '长风一梦', '2021-07-31 08:20:17');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (124, 10, 'INDUSTRY', '5', '电商', b'1', 'warning', '', 1, b'1', 1, '长风一梦', '2021-07-31 08:17:58', 1, '长风一梦000011', '2024-07-17 17:41:50');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (125, 11, 'STATUS', '1', '启用', b'1', NULL, '', 1, b'0', 0, NULL, '2024-12-18 02:27:33', 0, NULL, '2024-12-18 02:27:36');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (126, 11, 'STATUS', '0', '停用', b'1', NULL, '', 1, b'0', 0, NULL, '2024-12-18 02:27:33', 0, NULL, '2024-12-18 02:27:36');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613636006027265, 1865614807147950081, 'TENANT-DICT', 'A', '租户A', b'1', NULL, '租户A', 1, b'0', NULL, NULL, '2024-12-08 12:25:27', 0, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085314, 1865614807147950081, 'TENANT-DICT', 'B', '租户B', b'1', NULL, '租户B', 1, b'0', NULL, NULL, '2024-12-08 12:25:35', 0, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085661, 1686979951801745410, 'CONTRACT_SEAL_TYPE', 'contractSeal', '合同章', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-08-03 06:01:41', 1, '长风一梦8888', '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085662, 1686979951801745410, 'CONTRACT_SEAL_TYPE', 'officialSeal', '公章', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-08-03 06:03:02', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085663, 1698634230920761345, 'PAYMENT_TYPE', 'L0104', '退质保金-供应商', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:57:52', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085664, 1698634230920761345, 'PAYMENT_TYPE', 'L010', '预付款', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:58:19', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085665, 1698634230920761345, 'PAYMENT_TYPE', 'L0106', '退供应商履约保证金', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:58:47', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085666, 1698634189967577089, 'PAYMENT_METHOD', '1', '电汇', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:59:08', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865613671603085667, 1698634189967577089, 'PAYMENT_METHOD', '2', '支票', b'1', NULL, '', 1, b'0', 1, '长风一梦8888', '2023-09-04 09:59:15', NULL, NULL, '2024-12-08 10:16:35');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `status`, `color`, `description`, `sequence`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865928793793933313, 1865614807147950081, 'TENANT-DICT', '123', '123', b'1', NULL, '123', 1, b'1', 1, NULL, '2024-12-09 09:17:46', NULL, NULL, '2024-12-18 02:27:57');
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '简称',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系方式',
  `parent_id` bigint DEFAULT '0' COMMENT '父ID',
  `tree_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '树节点路径',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1865599612409217026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (100, '鏖战八方', 1, '鏖战', '0746-8485560转1', 0, '0', 1, b'1', b'0', '初始化数据', 1, '1', '2019-07-10 17:02:18', 1, '1', '2023-11-22 08:21:42');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10001, '鏖战八方上海分部', 1, '鏖战上海', '0746-8485560转2', 100, '0,100', 0, b'1', b'0', '初始化数据', 1, '1', '2019-08-06 09:10:53', NULL, NULL, '2024-12-07 13:39:58');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10002, '鏖战八方北京分部', 1, '鏖战北京', '0746-8485560转3', 100, '0,100', 1, b'1', b'0', '初始化数据', 1, '1', '2019-11-07 16:13:09', 1, '1', '2023-11-22 08:23:13');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10003, '管理层', 1, '管理层', '0746-8485560转4', 100, '0,100', 3, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:34:39', 3, '3', '2023-11-22 08:23:14');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10004, '总经办', 1, '', '0746-8485560', 100, '0,100', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:35:37', 1, '长风一梦', '2023-11-22 08:23:15');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10005, '财务部', 1, '', NULL, 100, '0,100', 4, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:17', 3, '3', '2023-11-22 08:23:17');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10006, '市场部', 1, '', NULL, 100, '0,100', 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:37:37', 3, '3', '2023-11-22 08:23:21');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10101, '综合部', 1, '综合部', '0746-8485560转7', 10001, '0,100,10001', 0, b'1', b'0', '前台&HR11', 3, '3', '2019-11-12 11:34:27', 1, '长风一梦8888', '2023-11-22 08:23:23');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10102, '研发部', 1, '研发部', '0746-8485560转5', 10001, '0,100,10001', 5, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:21', 2, '不告诉你', '2023-11-22 08:23:25');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10103, '产品部', 1, '产品部', '0746-8485560转6', 10001, '0,100,10001', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:31', NULL, NULL, '2024-12-07 13:40:22');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10201, '综合部', 1, '综合部', NULL, 10002, '0,100,10002', 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:39', 3, '3', '2023-11-22 08:23:30');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10202, '测试部', 1, '测试部', NULL, 10002, '0,100,10002', 1, b'1', b'0', '初始化数据', 2, '不告诉你', '2020-10-29 06:39:09', 2, '不告诉你', '2023-11-22 08:23:32');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10203, '研发部', 1, '', NULL, 10002, '0,100,10002', 0, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:38:50', 3, '3', '2023-11-22 08:23:34');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10204, '销售部', 1, '', NULL, 10002, '0,100,10002', 2, b'1', b'0', '初始化数据', 3, '3', '2019-11-12 11:39:00', 3, '3', '2023-11-22 08:23:35');
INSERT INTO `sys_org` (`id`, `label`, `tenant_id`, `alias`, `tel`, `parent_id`, `tree_path`, `sequence`, `status`, `deleted`, `description`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865599612409217025, '深圳腾讯科技股份有限公司', 3, '', NULL, 0, NULL, 0, b'1', b'0', '不可删除不可修改', NULL, NULL, '2024-12-08 11:29:43', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `sequence` tinyint DEFAULT NULL COMMENT '排序',
  `org_id` bigint DEFAULT '0' COMMENT '组织ID',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `deleted` bit(1) DEFAULT b'0',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1865020143000756227 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='岗位';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
BEGIN;
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (100, 1, '总经理', 'CEO', 0, 100, b'1', '总部-1把手2', b'0', '2019-07-10 17:03:03', NULL, 1, NULL, NULL, '2024-12-06 17:03:57');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (101, 1, '副总经理', NULL, 1, 10001, b'1', '总部-2把手', b'0', '2019-07-22 17:07:55', NULL, 1, 1, '长风一梦', '2021-07-28 02:58:53');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (103, 1, '研发经理', NULL, 1, 10002, b'1', '子公司-研发部老大', b'0', '2019-11-07 16:08:49', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:06');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (104, 1, '副总经理', NULL, 1, 10001, b'1', '子公司-老大', b'0', '2019-11-16 09:51:45', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:29');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (106, 1, '产品经理', NULL, 1, 10002, b'1', '子公司-产品部老大', b'0', '2019-11-16 09:53:27', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (107, 1, '人事经理', NULL, 1, 10002, b'1', '子公司-综合老大', b'0', '2019-11-16 09:54:43', NULL, 3, 1, '长风一梦', '2021-07-20 18:25:07');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (108, 1, 'Java研发', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:04', NULL, 3, 1, '长风一梦', '2021-07-28 02:59:38');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1011, 1, 'UI工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 09:55:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:52');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1012, 1, '运维工程师', 'CTI', 1, 10203, b'1', '普通员工', b'0', '2019-11-16 09:55:53', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:45');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1013, 1, '行政经理', NULL, 1, 10003, b'1', '普通员工', b'0', '2019-11-16 09:56:04', NULL, 3, 1, '长风一梦8888', '2021-11-27 06:36:22');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1014, 1, '人事经理', NULL, 1, 10004, b'1', '北京分公司-综合部老大', b'0', '2019-11-16 09:56:38', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:36');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1015, 1, '研发经理', NULL, 1, 10102, b'1', '北京分公司-研发部老大', b'0', '2019-11-16 09:57:07', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:28');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1016, 1, '销售经理', NULL, 1, 10004, b'1', '北京销售部老大', b'0', '2019-11-16 09:57:40', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:19');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10121, 1, '销售总监', NULL, 1, 10005, b'1', '总部2把手', b'0', '2019-11-16 09:59:10', NULL, 3, 1, '长风一梦', '2021-07-29 02:36:31');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10122, 1, '销售员工', NULL, 1, 10006, b'1', '普通员工', b'0', '2019-11-16 09:58:41', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:11');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10123, 1, '财务总监', NULL, 1, 10003, b'1', '总部2把手', b'0', '2019-11-16 09:59:39', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:06');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10124, 1, '市场经理', NULL, 1, 10003, b'1', '总部市场部老大', b'0', '2019-11-16 10:00:03', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:21:01');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (10128, 1, '前端工程师', 'CTI', 1, 10102, b'1', '普通员工', b'0', '2019-11-16 10:00:43', NULL, 3, 1, '长风一梦0000', '2023-11-22 17:20:53');
INSERT INTO `sys_position` (`id`, `tenant_id`, `title`, `code`, `sequence`, `org_id`, `status`, `description`, `deleted`, `created_time`, `created_name`, `created_by`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865020143000756226, NULL, '1', '1', 0, 10101, b'1', '213', b'1', '2024-12-06 21:07:07', NULL, NULL, NULL, NULL, '2024-12-06 21:07:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_registered_client`;
CREATE TABLE `sys_registered_client` (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `client_authentication_methods` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `grant_types` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `client_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `token_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `status` bit(1) DEFAULT b'1' COMMENT '0=禁用;1=启用',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_registered_client
-- ----------------------------
BEGIN;
INSERT INTO `sys_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`, `status`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES ('1', 'pc-web', '2024-09-11 06:39:32', 'pc-web', NULL, 'PC-WEB 管理系统', 'client_secret_basic', 'password,sms', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', '', '[[[wp]]]', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"accessTokenTimeToLive\":120}', b'1', b'0', NULL, NULL, '2023-09-15 16:43:14', NULL, NULL, '2024-12-07 12:24:30');
INSERT INTO `sys_registered_client` (`id`, `client_id`, `client_id_issued_at`, `client_secret`, `client_secret_expires_at`, `client_name`, `client_authentication_methods`, `grant_types`, `redirect_uris`, `post_logout_redirect_uris`, `scopes`, `client_settings`, `token_settings`, `status`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES ('1865050872845828098', 'uniapp', '2023-08-18 09:55:44', 'pc-web', NULL, 'APP 客户端', NULL, 'password,sms', 'http://127.0.0.1:5001/oauth2/code,https://www.baidu.com', NULL, '[[[[wp]]]]', NULL, '{\"accessTokenTimeToLive\":120}', b'1', b'0', NULL, NULL, '2024-12-06 23:09:14', NULL, NULL, '2024-12-07 12:24:26');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限',
  `parent_id` bigint DEFAULT '0' COMMENT '父级菜单ID',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '菜单图标',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型 directory=目录;menu=菜单;iframe=内嵌;link=外链;button=按钮',
  `status` bit(1) DEFAULT b'1' COMMENT '1=启用;0=禁用',
  `keep_alive` bit(1) DEFAULT NULL COMMENT '开启后页面会缓存，不会重新加载，仅在标签页启用时有效',
  `readonly` bit(1) DEFAULT b'0' COMMENT '内置菜单（0=否;1=是）',
  `global` bit(1) DEFAULT b'0' COMMENT '公共资源\nTrue是无需分配所有人就可以访问的',
  `visible` bit(1) DEFAULT b'1' COMMENT '0=隐藏;1=显示',
  `meta` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单 meta 可以覆盖',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `INX_STATUS` (`global`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1875080402612436994 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1, '平台管理', NULL, 0, '/plat', 'BasicLayout', 1, 'clarity-thin-client-line', 'directory', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2025-09-15 16:59:18');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3, '系统管理', NULL, 0, '/sys', 'BasicLayout', 2, 'ant-design:dashboard-filled', 'directory', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2025-09-15 16:58:59');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (50, '开发平台', NULL, 0, '/dev', 'BasicLayout', 50, 'ant-design:appstore-add-outlined', 'directory', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2025-09-15 16:40:46');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (104, '租户管理', NULL, 1, '/plat/tenant', NULL, 104, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (105, '产品管理', NULL, 1, '/plat/product', NULL, 105, 'icon-park-outline:ad-product', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:54:15', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (107, '数据配置', NULL, 1, '/plat/setting', NULL, 107, 'file-icons:config-coffeescript', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (301, '组织架构', NULL, 3, '/sys/structure', NULL, 301, 'ant-design:user-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (302, '权限管理', NULL, 3, '/sys/auth', NULL, 302, 'ant-design:security-scan-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (303, '设置中心', NULL, 3, '/sys/setting', NULL, 303, 'file-icons:config-coffeescript', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (306, '消息管理', NULL, 3, '/sys/message', NULL, 306, 'mi:message', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (310, '运维监控', NULL, 3, '/sys/monitor', NULL, 310, 'lucide:monitor-cog', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (320, '存储管理', NULL, 3, '/sys/oss', NULL, 320, 'carbon:ibm-cloud-vpc-file-storage', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (350, '安全中心', NULL, 1, '/plat/security', NULL, 350, 'carbon-application', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (5003, '在线开发', NULL, 50, '/dev/online', '', 5003, 'material-symbols:home-work-outline-rounded', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-12-12 13:11:50', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (5005, '网关管理', NULL, 50, '/dev/gateway', NULL, 5005, 'ant-design:gateway-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (5050, '定时任务', NULL, 50, '/dev/job', 'http://localhost:7002/snail-job', 0, 'arcticons:jobstreet', 'iframe', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2025-09-14 16:38:34');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (5069, '代码生成', NULL, 50, '/develop/gen', '', 0, 'ant-design:appstore-filled', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-12 13:11:50', 1, '平台管理员', '2025-02-22 22:26:20');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (10401, '租户中心', 'tenant:list', 104, '/plat/tenant/list', '/wemirr/platform/tenant/index', 10401, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (10402, '多数据源', NULL, 104, '/plat/db/list', '/wemirr/platform/db/index', 10402, 'ant-design:database-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', NULL, NULL, '2024-12-06 17:07:02');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (10501, '产品定义', 'plat:product:definition', 105, '/platform/product/definition', '/wemirr/platform/product/definition/index', 1, 'fluent-mdl2:product-list', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:59:39', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (10502, '产品定价', 'plat:product:pricing', 105, '/platform/product/pricing', '/wemirr/platform/product/pricing/index', 2, 'arcticons:pricespy', 'menu', b'0', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:59:39', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (10503, '产品订阅', 'plat:product:subscribe', 105, '/platform/product/subscribe', '/wemirr/platform/product/subscribe/index', 3, 'material-symbols:package-2-sharp', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2023-09-23 06:59:39', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (10706, '数据字典', 'dict:list', 107, '/plat/setting/dict', '/wemirr/platform/basic/dict/index', 10706, 'bx-bxs-data', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (10707, '地区信息', NULL, 107, '/plat/setting/area', '/wemirr/platform/basic/area/index', 10707, 'ant-design:area-chart-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30101, '用户管理', 'sys:user:page', 301, '/sys/user', '/wemirr/system/user/index', 30101, 'heroicons-outline:user-group', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30102, '机构管理', 'sys:org:page', 301, '/sys/org', '/wemirr/system/org/index', 30102, 'codicon-organization', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30103, '岗位管理', 'sys:position:page', 301, '/sys/position', '/wemirr/system/position/index', 30103, 'ant-design:usergroup-add-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30201, '菜单管理', 'sys:menu:page', 302, '/system/menu', '/wemirr/system/auth/menu/index', 30201, 'ant-design:menu-unfold-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30202, '角色管理', 'sys:role:page', 302, '/system/role', '/wemirr/system/auth/role/index', 30202, 'eos-icons:role-binding-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30301, '租户字典', 'tenant:dict:list', 303, '/sys/basic-dict', '/wemirr/system/basic/dict/index', 30301, 'bx-bxs-data', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30303, '语言管理', 'i18n:page', 303, '/sys/basic-i18n', '/wemirr/system/basic/i18n/index', 30303, 'cil:language', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30604, '通道设置', 'message:channel:setting', 306, '/sys/message/channel-setting', '/wemirr/system/message/setting/index', 30604, 'fluent:channel-48-regular', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30605, '消息模板', 'message:template:page', 306, '/sys/message/template', '/wemirr/system/message/template', 30605, 'fluent:mail-template-24-regular', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30606, '消息列表', 'message:list', 306, '/sys/message/list', '/wemirr/system/message/message-list', 30606, 'mdi:envelope-outline', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30607, '消息推送', 'message:publish', 306, '/sys/message/publish', '/wemirr/system/message/publish', 30607, 'arcticons:efa-publish', 'menu', b'1', b'1', b'0', b'0', b'0', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (30608, '我的消息', 'message:subscribe-list', 306, '/sys/message/subscribe-list', '/wemirr/system/message/subscribe-list', 30608, 'ant-design:comment-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (31003, '在线用户', 'monitor:online:token-list', 310, '/sys/monitor/online', '/wemirr/system/monitor/online/index', 31003, 'mdi:account-online-outline', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"badge\":\"Alpha\",\"badgeVariants\":\"destructive\"}', NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (31005, '登录日志', 'monitor:log:login', 310, '/sys/monitor/login-log', '/wemirr/system/log/login-log', 31005, 'mdi:account-security-outline', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (31006, '操作日志', 'monitor:log:opt', 310, '/sys/monitor/opt-log', '/wemirr/system/log/opt-log', 31006, 'carbon-operation', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (32002, '文件管理', '', 320, '/sys/storage/file', '/wemirr/system/storage/file/index', 32002, 'hugeicons:folder-attachment', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-15 22:40:52');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (32003, '配置管理', '', 320, '/sys/storage/setting', '/wemirr/system/storage/setting/index', 32003, 'ep:setting', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-15 22:40:43');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (35005, '应用管理', 'plat:app:page', 350, '/plat/security/app', '/wemirr/platform/security/app/index', 35005, 'streamline:application-add-solid', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (500301, '表单模型', NULL, 5003, '/dev/online/list', '/wemirr/develop/online/index', 500301, 'file-icons:3d-model', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"badge\":\"Alpha\",\"badgeVariants\":\"destructive\"}', '', b'0', NULL, NULL, '2024-12-13 08:40:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (500302, '表单设计', 'dev:online:form-design', 5003, '/dev/online/form-design', '/wemirr/develop/online/form-design', 500302, 'arcticons:efa-publish', 'menu', b'1', b'1', b'0', b'0', b'0', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (500304, '在线打印', NULL, 5003, '/dev/online/print', '/wemirr/develop/print/index', 500304, 'mi:print', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"badge\":\"Alpha\",\"badgeVariants\":\"destructive\"}', '', b'0', NULL, NULL, '2024-12-13 08:40:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (500308, '测试用例', NULL, 5003, '/dev/online/demo', NULL, 500308, 'arcticons:democracy', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-12-13 08:40:43', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (500501, '限流规则', 'dev:gateway:limit', 5005, '/dev/gateway/limit', '/wemirr/develop/gateway/limit/index', 500501, 'carbon-rule', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (500502, '限访名单', 'dev:gateway:blacklist', 5005, '/dev/gateway/blacklist', '/wemirr/develop/gateway/blacklist/index', 500502, 'carbon-ai-status-rejected', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (500503, '网关路由', 'dev:gateway:route', 5005, '/dev/gateway/route', '/wemirr/develop/gateway/route/index', 500503, 'mdi-router-wireless-settings', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, NULL, b'0', 1, '平台管理员', '2024-12-06 14:52:11', 1, '平台管理员', '2024-12-06 14:52:11');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (505069, '生成配置', NULL, 5069, '/develop/gen/table', '/wemirr/develop/gen/generate-table', 0, 'ant-design:code-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-09-14 22:01:58');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (505079, '模板配置', NULL, 5069, '/develop/gen/template', '/wemirr/develop/gen/generate-template', 0, 'ant-design:appstore-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-09-14 21:59:42');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (505089, '列配置', NULL, 5069, '/develop/gen/column', '/wemirr/develop/gen/generate-table-column', 0, 'ant-design:menu-fold-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-09-14 21:58:50');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (505099, '模板组', NULL, 5069, '/develop/gen/template-group', '/wemirr/develop/gen/generate-template-group', 0, 'ant-design:container-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', '', '', b'0', NULL, '', '2024-12-13 08:40:43', 1, '平台管理员', '2025-09-14 21:57:55');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1040101, '添加', 'tenant:add', 10401, '', NULL, 1040101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1040102, '编辑', 'tenant:edit', 10401, '', NULL, 1040102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1040103, '删除', 'tenant:remove', 10401, '', NULL, 1040103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1040105, '数据源配置', 'tenant:db-config', 10401, '', NULL, 1040105, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '数据源配置', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1040106, '数据初始化', 'tenant:init-script', 10401, '', NULL, 1040106, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '基础数据初始化', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1040107, '字典数据刷新', 'tenant:refresh-dict', 10401, '', NULL, 1040107, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '字典数据刷新', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1070601, '添加', 'dict:add', 10706, '', NULL, 1070601, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1070602, '编辑', 'dict:edit', 10706, '', NULL, 1070602, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1070603, '删除', 'dict:remove', 10706, '', NULL, 1070603, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1070605, '刷新字典', 'dict:refresh', 10706, '', NULL, 1070605, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '字典数据刷新', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010101, '添加', 'sys:user:add', 30101, '', NULL, 3010101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010102, '编辑', 'sys:user:edit', 30101, '', NULL, 3010102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010103, '删除', 'sys:user:remove', 30101, '', NULL, 3010103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010104, '重置密码', 'sys:user:reset', 30101, '', NULL, 3010104, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010105, '导出', 'sys:user:export', 30101, '', NULL, 0, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '用户导出', b'0', 1, '平台管理员', '2024-12-25 10:58:09', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010201, '添加', 'sys:user:add', 30102, '', NULL, 3010201, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010202, '编辑', 'sys:user:edit', 30102, '', NULL, 3010202, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010203, '删除', 'sys:user:remove', 30102, '', NULL, 3010203, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010301, '添加', 'sys:position:add', 30103, '', NULL, 3010301, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010302, '编辑', 'sys:position:edit', 30103, '', NULL, 3010302, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3010303, '删除', 'sys:position:remove', 30103, '', NULL, 3010303, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020101, '添加', 'sys:menu:add', 30201, '', NULL, 3020101, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020102, '编辑', 'sys:menu:edit', 30201, '', NULL, 3020102, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020103, '删除', 'sys:menu:remove', 30201, '', NULL, 3020103, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020201, '添加', 'sys:role:add', 30202, '', NULL, 3020201, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020202, '编辑', 'sys:role:edit', 30202, '', NULL, 3020202, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020203, '删除', 'sys:role:remove', 30202, '', NULL, 3020203, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020207, '分配用户', 'sys:role:assign-users', 30202, '', NULL, 3020207, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '分配用户', b'0', 1, '平台管理员', '2024-12-09 15:54:08', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3020208, '分配资源', 'sys:role:assign-resource', 30202, '', NULL, 3020208, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '分配资源', b'0', 1, '平台管理员', '2024-12-09 15:55:02', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3060501, '添加', 'message:template:add', 30605, '', NULL, 3060501, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '新增按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:00', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3060502, '编辑', 'message:template:edit', 30605, '', NULL, 3060502, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '编辑按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:17', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3060503, '删除', 'message:template:remove', 30605, '', NULL, 3060503, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '删除按钮权限', b'0', 1, '平台管理员', '2024-12-07 17:52:36', 1, '平台管理员', '2024-12-07 17:52:00');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (3060504, '推送', 'message:template:publish', 30605, '', NULL, 3060504, '', 'button', b'1', NULL, b'0', b'0', b'1', NULL, '', b'0', NULL, NULL, '2024-12-07 10:30:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (50030801, '学生管理', NULL, 500308, '/dev/online/demo/student', '/wemirr/develop/online/reader', 1, 'ph:student-fill', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"query\":{\"definitionKey\":\"student\"}}', '', b'0', NULL, NULL, '2025-01-08 09:38:42', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (50030802, '老师管理', NULL, 500308, '/dev/online/demo/teacher', '/wemirr/develop/online/reader', 2, 'mdi:teacher', 'menu', b'1', b'1', b'0', b'0', b'1', '{\"query\":{\"definitionKey\":\"teacher\"}}', '', b'0', NULL, NULL, '2025-01-08 09:38:42', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967505611004141569, '审批管理', NULL, 0, '/bpm', 'BasicLayout', 3, 'ant-design:edit-filled', 'directory', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:27:47', 1, '平台管理员', '2025-09-15 16:59:39');
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967505974054707202, '审批办公', NULL, 1967505611004141569, '/bpm/approval', 'Layout', 0, 'ant-design:audit-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:29:13', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967506673433927682, '流程设置', NULL, 1967505611004141569, '/bpm/setting', 'Layout', 0, 'ant-design:node-index-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:32:00', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967507036505464834, '历史任务', NULL, 1967505974054707202, '/bpm/task/history', '/wemirr/bpm/task/history/index', 0, 'ant-design:book-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:33:27', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967507301698723842, '任务列表', NULL, 1967505974054707202, '/bpm/task/list', '/wemirr/bpm/task/list/index', 0, 'ant-design:menu-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:34:30', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967507542556631041, '流程列表', NULL, 1967505974054707202, '/bpm/process/list', '/wemirr/bpm/process/list/index', 0, 'ant-design:profile-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:35:27', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967507707673796610, '创建流程', NULL, 1967505974054707202, '/bpm/process/create', '/wemirr/bpm/process/create/index', 0, 'ant-design:plus-square-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:36:07', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967507843825098754, '创建模型', NULL, 1967506673433927682, '/bpm/process/design', '/wemirr/bpm/process/model/workflow', 0, 'ant-design:plus-circle-twotone', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:36:39', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967508076986458113, '表单设计', NULL, 1967506673433927682, '/bpm/process/form-design', '/wemirr/bpm/process/model/form-design', 0, 'ant-design:compass-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:37:35', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967508200080891905, '模型管理', NULL, 1967506673433927682, '/bpm/process/model', '/wemirr/bpm/process/model/index', 0, 'ant-design:code-sandbox-outlined', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:38:04', NULL, NULL, NULL);
INSERT INTO `sys_resource` (`id`, `title`, `permission`, `parent_id`, `path`, `component`, `sequence`, `icon`, `type`, `status`, `keep_alive`, `readonly`, `global`, `visible`, `meta`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`)  VALUES (1967508470483476481, '流程类别', NULL, 1967506673433927682, '/bpm/process/category', '/wemirr/bpm/process/category/index', 0, 'ant-design:mac-command-filled', 'menu', b'1', b'1', b'0', b'0', b'1', NULL, '', b'0', 1, '平台管理员', '2025-09-15 16:39:09', NULL, NULL, NULL);

COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编码',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色编码',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
  `scope_type` tinyint DEFAULT NULL COMMENT '数据权限范围，值越大，权限越大',
  `status` tinyint(1) DEFAULT '0' COMMENT '0=正常1=禁用',
  `super` tinyint(1) DEFAULT '0' COMMENT '0=非 1=管理员',
  `readonly` tinyint(1) DEFAULT '0' COMMENT '是否内置角色',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1829323672360452099 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, 'PLATFORM-ADMIN', '平台管理员', '平台管理员，拥有所有数据可视权', 50, 1, 1, 1, 0, '超级管理员', '2019-10-25 13:46:00', 57, NULL, '2024-12-25 06:03:52');
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 1, 'TENANT-ADMIN', '租户管理员', '租户管理员', 50, 1, 0, 1, 1, '长风一梦8888', '2022-06-07 07:52:29', 0, NULL, '2024-07-19 00:58:14');
INSERT INTO `sys_role` (`id`, `tenant_id`, `code`, `name`, `description`, `scope_type`, `status`, `super`, `readonly`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1725059482402205698, 1, 'TEST', '测试权限范围', '123', 10, 1, 0, 0, 1, '长风一梦0000', '2023-11-16 15:53:25', 0, NULL, '2024-12-06 09:09:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `res_id` bigint NOT NULL COMMENT '菜单ID',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `idx_role_res` (`role_id`,`res_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 11, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 50, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 104, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 105, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 107, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 151, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 152, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 153, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 161, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 162, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 163, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 164, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 165, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 166, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 168, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 301, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 302, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 303, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 306, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 310, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 320, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 350, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5003, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5005, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5008, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5050, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10401, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10402, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10501, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10502, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10503, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10706, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 10707, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15103, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15201, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15202, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15203, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15204, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15205, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15206, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15207, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 15301, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16103, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16108, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16201, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16401, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16408, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16501, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16502, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16601, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 16608, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30103, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30201, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30202, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30301, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30303, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30604, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30605, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30606, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30607, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 30608, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 31003, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 31005, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 31006, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 32002, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 32003, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 35005, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110107, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110108, '2024-12-31 06:10:02');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110201, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110202, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110203, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 110204, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160001, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160002, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160005, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160006, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160010, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160011, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 160012, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 161001, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 161002, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 161003, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 162002, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 163001, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 163002, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500301, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500302, '2025-01-08 08:25:57');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500303, '2025-01-07 09:28:50');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500304, '2025-01-07 03:26:53');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500308, '2025-01-08 09:44:19');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500501, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500502, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 500503, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040103, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040105, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040106, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1040107, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070601, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070602, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070603, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 1070605, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010103, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010104, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010105, '2024-12-25 02:58:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010201, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010202, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010203, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010301, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010302, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3010303, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020101, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020102, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020103, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020201, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020202, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020203, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020207, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3020208, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060501, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060502, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060503, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 3060504, '2024-12-19 15:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 50030801, '2025-01-08 09:44:23');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 50030802, '2025-01-08 09:44:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 105, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 107, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 350, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 10501, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 10503, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 10706, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 10707, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1725059482402205698, 35005, '2025-01-03 07:24:40');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 151, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 152, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 153, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 301, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 302, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 303, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 306, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 310, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 320, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15101, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15102, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15103, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15201, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15202, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15203, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15204, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15205, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15206, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15207, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 15301, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30101, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30102, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30103, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30201, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30202, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30301, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30303, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30604, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30605, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30606, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30607, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 30608, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 31003, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 31005, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 31006, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 32002, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 32003, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010101, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010102, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010103, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010104, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010105, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010201, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010202, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010203, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010301, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010302, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1866800164601061378, 3010303, '2024-12-25 04:43:26');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 5069, '2025-02-10 10:57:18');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 505069, '2025-02-10 10:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 505079, '2025-02-10 10:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 505089, '2025-02-10 10:57:09');
INSERT INTO `sys_role_res` (`role_id`, `res_id`, `created_time`) VALUES (1, 505099, '2025-02-10 10:57:09');

COMMIT;

-- ----------------------------
-- Table structure for sys_site_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_site_notify`;
CREATE TABLE `sys_site_notify` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息级别',
  `status` tinyint(1) DEFAULT NULL COMMENT '0=为发布;1=已发布',
  `type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '接受者ID',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1863776323592458243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='站内消息';

-- ----------------------------
-- Records of sys_site_notify
-- ----------------------------
BEGIN;
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 1, '1', 1, '1', '测试消息', '2,1', '<p>测试消息</p>', '测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息测试消息', b'0', 1, '管理员', '2021-07-12 11:41:31', 1, '长风一梦0000', '2023-12-15 14:16:51');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 1, '0', 1, '1', '测试通知', '1', '<p>测试通知</p>', '测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知', b'0', 1, '管理员', '2021-07-12 11:42:15', 1, '长风一梦0000', '2023-12-15 14:16:51');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (4, 2, '2', 1, '1', '测试待办', '1', '<p>测试待办</p><img src=\"http://www.docmirror.cn:7070/api/upload/form/download?key=file/2021/7/15/30369909559790.png\" contenteditable=\"false\"/>', '测试待办', b'0', 1, '管理员', '2021-07-12 11:42:26', NULL, NULL, '2024-12-03 10:44:22');
INSERT INTO `sys_site_notify` (`id`, `tenant_id`, `level`, `status`, `type`, `title`, `receiver`, `content`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1863776323592458242, NULL, '0', NULL, '1', '12312', '1', '<p>12312321321</p>', '3213123123123', b'0', NULL, NULL, '2024-12-03 10:44:37', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  UNIQUE KEY `role_id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1725059482402205698);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (8, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (10, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (12, 4);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (14, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (19, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (20, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (21, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (22, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (24, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (24, 1725059482402205698);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1863475850498969601, 1725059482402205698);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1865599612446965761, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_db_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_db_setting`;
CREATE TABLE `t_db_setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '连接名',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `db_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据库类型',
  `driver_class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `host` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'localhost' COMMENT '数据库连接',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述信息',
  `locked` bit(1) DEFAULT b'0' COMMENT '0=正常1=禁用',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最后修改人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1735576153244049410 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态数据源';

-- ----------------------------
-- Records of t_db_setting
-- ----------------------------
BEGIN;
INSERT INTO `t_db_setting` (`id`, `name`, `username`, `password`, `db_type`, `driver_class_name`, `host`, `description`, `locked`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, '测试专用1', 'root', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', 'localhost:3306', 'tenant-8888', b'0', b'0', NULL, NULL, '2021-08-06 13:57:40', NULL, NULL, '2024-12-06 17:01:53');
INSERT INTO `t_db_setting` (`id`, `name`, `username`, `password`, `db_type`, `driver_class_name`, `host`, `description`, `locked`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, '测试专用2', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', '123123213123', NULL, b'0', b'1', 1, '长风一梦8888', '2023-02-21 01:02:51', 1, '长风一梦8888', '2023-12-15 08:24:47');
INSERT INTO `t_db_setting` (`id`, `name`, `username`, `password`, `db_type`, `driver_class_name`, `host`, `description`, `locked`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, '测试专用3', 'admin', '123456', 'mysql', 'com.mysql.cj.jdbc.Driver', '31231231231231', NULL, b'0', b'1', 1, '长风一梦8888', '2023-02-21 01:05:06', NULL, NULL, '2023-12-15 08:24:47');
COMMIT;

-- ----------------------------
-- Table structure for t_file_storage
-- ----------------------------
DROP TABLE IF EXISTS `t_file_storage`;
CREATE TABLE `t_file_storage` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `platform` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '存储平台',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '预览地址',
  `size` bigint DEFAULT NULL COMMENT '文件大小，单位字节',
  `format_size` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `filename` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文件名称',
  `original_filename` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '原始文件名',
  `base_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '基础存储路径',
  `path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '存储路径',
  `ext` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文件扩展名',
  `content_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'MIME类型',
  `th_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '缩略图访问路径',
  `th_filename` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '缩略图名称',
  `th_size` bigint DEFAULT NULL COMMENT '缩略图大小，单位字节',
  `th_content_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '缩略图MIME类型',
  `object_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文件所属对象id',
  `object_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文件所属对象类型，例如用户头像，评价图片',
  `metadata` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '文件元数据',
  `user_metadata` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '文件用户元数据',
  `th_metadata` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '缩略图元数据',
  `th_user_metadata` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '缩略图用户元数据',
  `attr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '附加属性',
  `file_acl` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文件ACL',
  `th_file_acl` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '缩略图文件ACL',
  `hash_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '哈希信息',
  `category` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件记录表';

-- ----------------------------
-- Table structure for t_file_storage_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_file_storage_setting`;
CREATE TABLE `t_file_storage_setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint DEFAULT NULL,
  `platform` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'type+bucket_name 组合',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型（七牛云、阿里云、腾讯云、Minio、其他S3）',
  `status` tinyint DEFAULT '0' COMMENT '状态',
  `access_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'AccessKey',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'SecretKey',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '存储区域',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '域名',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '存储桶',
  `base_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '基础路径',
  `end_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '服务端点',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1868599415401865218 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_file_storage_setting
-- ----------------------------
BEGIN;
INSERT INTO `t_file_storage_setting` (`id`, `tenant_id`, `platform`, `type`, `status`, `access_key`, `secret_key`, `region`, `domain`, `bucket_name`, `base_path`, `end_point`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, 'minio-wp-local', 'minio', 0, 'dQAztlq1gMdAaTfR5i7K', 'S6wpCw8Jo2ujqqs2bgYcBGbp4sOLtvzVy2Yvm6zF', NULL, 'http://127.0.0.1:19000/wp-local/', 'wp-local', 'minio/', 'http://127.0.0.1:19000', b'0', '1', '平台管理员', '2024-12-16 10:41:31', '1', '平台管理员', '2024-12-17 11:19:59');
INSERT INTO `t_file_storage_setting` (`id`, `tenant_id`, `platform`, `type`, `status`, `access_key`, `secret_key`, `region`, `domain`, `bucket_name`, `base_path`, `end_point`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1868599415401865217, 1, 's3-wp-local', 's3', 1, 'dQAztlq1gMdAaTfR5i7K	', 'S6wpCw8Jo2ujqqs2bgYcBGbp4sOLtvzVy2Yvm6zF', NULL, 'http://127.0.0.1:19000/wp-local/', 'wp-local', 'dev/', 'http://127.0.0.1:19000', b'0', '1', '平台管理员', '2024-12-16 18:09:52', '1', '平台管理员', '2024-12-17 11:19:59');
COMMIT;

-- ----------------------------
-- Table structure for t_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `t_gateway_route`;
CREATE TABLE `t_gateway_route` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `route_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '路由ID',
  `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业邮箱',
  `order` tinyint DEFAULT '0' COMMENT '排序',
  `predicates` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '谓语条件',
  `filters` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '过滤器',
  `locked` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_route_id` (`route_id`) USING BTREE COMMENT '路由ID唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='网关路由表';

-- ----------------------------
-- Records of t_gateway_route
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_online_form_data
-- ----------------------------
DROP TABLE IF EXISTS `t_online_form_data`;
CREATE TABLE `t_online_form_data` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `definition_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '业务组标识',
  `form_data` longtext NOT NULL COMMENT '动态表单数据',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_business_group_key` (`definition_key`)
) ENGINE=InnoDB AUTO_INCREMENT=1876938407108820994 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='动态表单数据表';

-- ----------------------------
-- Records of t_online_form_data
-- ----------------------------
BEGIN;
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1876938285876658177, 'student', '{\"definitionKey\":\"student\",\"id\":\"1876938285876658177\",\"createdName\":\"平台管理员\",\"createdTime\":\"2025-01-08T10:25:34Z\",\"name\":\"1\",\"email\":\"1222\"}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:25:34', 1, '平台管理员', '2025-01-09 09:23:23');
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1876938337336573954, 'teacher', '{\"definitionKey\":\"teacher\",\"id\":\"1876938337336573954\",\"createdName\":\"平台管理员\",\"createdTime\":\"2025-01-08T10:25:46Z\",\"name\":\"花花老师\",\"age\":18,\"remark\":\"你别问\",\"type\":[\"1\",\"4\"]}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:25:46', 1, '平台管理员', '2025-01-09 16:39:34');
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1876938382756691969, 'teacher', '{\"definitionKey\":\"teacher\",\"id\":\"1876938382756691969\",\"createdName\":\"平台管理员\",\"createdTime\":\"2025-01-08T10:25:57Z\",\"name\":\"小安老师\",\"age\":19,\"remark\":\"反差\",\"type\":[\"4\",\"3\"]}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:25:57', 1, '平台管理员', '2025-01-09 16:39:45');
INSERT INTO `t_online_form_data` (`id`, `definition_key`, `form_data`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1876938407108820993, 'student', '{\"definitionKey\":\"student\",\"name\":\"3\",\"email\":\"4\"}', NULL, b'0', 1, '平台管理员', '2025-01-08 18:26:03', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_online_model
-- ----------------------------
DROP TABLE IF EXISTS `t_online_model`;
CREATE TABLE `t_online_model` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `definition_key` varchar(255) NOT NULL COMMENT '定义KEY',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号',
  `description` text COMMENT '备注',
  `form_script` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '脚本',
  `form_schemas` longtext COMMENT '表单配置',
  `form_crud_config` longtext COMMENT '表单CRUD配置',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1876935098889273347 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='在线模型表';

-- ----------------------------
-- Records of t_online_model
-- ----------------------------
BEGIN;
INSERT INTO `t_online_model` (`id`, `title`, `definition_key`, `status`, `version`, `description`, `form_script`, `form_schemas`, `form_crud_config`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, '学生管理', 'student', 1, 1, '测试用的', 'const { defineExpose, find } = epic;\n\nfunction test (){\n    console.log(\'test\')\n}\n\n// 通过defineExpose暴露的函数或者属性\ndefineExpose({\n test\n})', '[{\"type\":\"page\",\"id\":\"root\",\"label\":\"页面\",\"children\":[{\"label\":\"表单\",\"type\":\"form\",\"componentProps\":{\"name\":\"default\",\"labelWidth\":\"100px\",\"labelLayout\":\"fixed\",\"labelCol\":{\"span\":5},\"wrapperCol\":{\"span\":19}},\"children\":[{\"label\":\"名称\",\"type\":\"input\",\"field\":\"name\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入名称\"},\"id\":\"input_npnj7ex7\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]},{\"label\":\"邮箱\",\"type\":\"input\",\"field\":\"email\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入邮箱\"},\"id\":\"input_nrd81qrj\",\"rules\":[{\"required\":true,\"message\":\"必填项\",\"type\":\"string\",\"trigger\":[\"change\"]}]}],\"id\":\"form_rnxhlmui\"}],\"componentProps\":{\"style\":{\"padding\":\"16px\"}}}]', '{null:{\"search\":{\"show\":true},\"column\":{\"show\":true,\"width\":200},\"type\":\"page\",\"title\":\"页面\"}}', b'0', 1, '平台管理员', '2025-01-08 15:30:46', 1, '平台管理员', '2025-01-08 18:12:33');
INSERT INTO `t_online_model` (`id`, `title`, `definition_key`, `status`, `version`, `description`, `form_script`, `form_schemas`, `form_crud_config`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, '老师管理', 'teacher', 1, 1, '测试专用', 'const { defineExpose, find } = epic;\n\nfunction test (){\n    console.log(\'test\')\n}\n\n// 通过defineExpose暴露的函数或者属性\ndefineExpose({\n test\n})', '[{\"type\":\"page\",\"id\":\"root\",\"label\":\"页面\",\"children\":[{\"label\":\"表单\",\"type\":\"form\",\"componentProps\":{\"name\":\"default\",\"labelWidth\":\"100px\",\"labelLayout\":\"fixed\",\"labelCol\":{\"span\":5},\"wrapperCol\":{\"span\":19}},\"children\":[{\"label\":\"名称\",\"type\":\"input\",\"field\":\"name\",\"input\":true,\"componentProps\":{\"placeholder\":\"老师名称\"},\"id\":\"input_4cqc5eq4\"},{\"label\":\"年龄\",\"type\":\"number\",\"field\":\"age\",\"input\":true,\"componentProps\":{\"style\":{\"width\":\"100%\"},\"placeholder\":\"请输入\",\"defaultValue\":18},\"id\":\"number_ttaqiyd7\"},{\"label\":\"擅长\",\"type\":\"textarea\",\"field\":\"remark\",\"input\":true,\"componentProps\":{\"placeholder\":\"请输入\",\"defaultValue\":\"老师擅长姿势\"},\"id\":\"textarea_t4nbl3pp\"},{\"label\":\"类型\",\"type\":\"checkbox\",\"field\":\"type\",\"input\":true,\"componentProps\":{\"options\":[{\"label\":\"蛟龙出海\",\"value\":\"1\"},{\"label\":\"亢龙有悔\",\"value\":\"2\"},{\"label\":\"不动如山\",\"value\":\"3\"},{\"label\":\"探囊取物\",\"value\":\"4\"}],\"defaultValue\":[]},\"id\":\"checkbox_irvcgtg5\"}],\"id\":\"form_k4isjdth\"}],\"componentProps\":{\"style\":{\"padding\":\"16px\"}}}]', '{null:{\"search\":{\"show\":true},\"column\":{\"show\":true,\"width\":200},\"type\":\"page\",\"title\":\"页面\"}}', b'0', 1, '平台管理员', '2025-01-08 18:12:54', 1, '平台管理员', '2025-01-09 15:40:21');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
    `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '租户名称',
    `type` tinyint DEFAULT '0' COMMENT '0=其它,1=企业',
    `alias` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简称',
    `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'LOGO',
    `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户邮箱',
    `status` bit(1) DEFAULT b'0' COMMENT '是否启用 0=未锁定 1=锁定(逻辑删除用)',
    `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
    `contact_phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人方式',
    `industry` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '行业',
    `province_id` int DEFAULT NULL COMMENT '省份',
    `province_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
    `city_id` int DEFAULT NULL COMMENT '市',
    `city_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '市',
    `address` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '详细地址',
    `district_id` int DEFAULT NULL COMMENT '区县',
    `district_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '区县',
    `credit_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '统一信用代码',
    `legal_person_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '法人',
    `web_site` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业网址',
    `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
    `deleted` bit(1) DEFAULT b'0',
    `created_by` bigint DEFAULT '0' COMMENT '创建人id',
    `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
    `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
    `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1864956411491020802 DEFAULT CHARSET=utf8mb4 COMMENT='租户信息';

-- ----------------------------
-- Records of t_tenant
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, '0000', '平台超级租户', 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '000000@qq.com', b'1', '唐亚峰', '13002170000', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 07:27:08', 1, '长风一梦', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, '2222', '小米科技有限责任公司', 1, '小米', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '666666@qq.com', b'1', '雷军', '13002176666', '4', 110000, '北京市', 110100, '市辖区', '北京市海淀区西二旗中路33号院6号楼6层006号 ', 110108, '海淀区', '91110108551385082Q', '雷军', 'https://cloud.battcn.com', NULL, b'0', 0, NULL, '2021-07-31 08:14:02', 1, '长风一梦', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, '8888', '深圳腾讯科技股份有限公司', 1, '腾讯', 'https://img.zcool.cn/community/010cb65e205811a80120a895cf85b3.jpg@1280w_1l_2o_100sh.jpg', '888888@qq.com', b'1', '马化腾', '13002178888', '4', 440000, '广东省', 440300, '深圳市', '深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层', 440305, '南山区', '91440300708461136T', '马化腾', 'http://localhost:5666', NULL, b'0', 0, NULL, '2021-07-31 07:27:08', 1, '平台管理员', '2025-01-02 09:09:55');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1722515686367961090, '3333', '测试3333', 0, '测试', NULL, '1231@163.com', b'1', '测试3333', '13000000000', NULL, 130000, NULL, 130500, NULL, '123', 130505, NULL, NULL, NULL, '123123', NULL, b'1', 1, '长风一梦0000', '2023-11-09 15:25:17', NULL, NULL, '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1821121516151009281, 'ces4', 'ces4', 0, 'ces4', NULL, NULL, b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ces4.com', NULL, b'1', 1, '平台管理员', '2024-08-07 17:49:39', NULL, NULL, '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1821132304500666370, 'ces5', 'ces5', 0, 'ces5', NULL, NULL, b'1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ces5.com', NULL, NULL, NULL, NULL, 'ces5.com', NULL, b'1', 1, '平台管理员', '2024-08-07 18:32:31', 1, '平台管理员', '2024-12-10 08:23:47');
INSERT INTO `t_tenant` (`id`, `code`, `name`, `type`, `alias`, `logo`, `email`, `status`, `contact_person`, `contact_phone`, `industry`, `province_id`, `province_name`, `city_id`, `city_name`, `address`, `district_id`, `district_name`, `credit_code`, `legal_person_name`, `web_site`, `description`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1864956411491020801, '12345', '123', 0, '123', NULL, NULL, b'1', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '123', NULL, b'1', NULL, NULL, '2024-12-06 16:53:52', NULL, NULL, '2024-12-10 08:23:47');
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dict`;
CREATE TABLE `t_tenant_dict` (
  `id` bigint NOT NULL COMMENT '雪花算法ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly` bit(1) DEFAULT b'0' COMMENT '0=否；1=是；只读数据不允许删除',
  `sequence` tinyint DEFAULT NULL COMMENT '排序',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID t_tenant.id',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_code` (`code`) USING BTREE COMMENT '租户字典索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_tenant_dict
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185326841858, 'TENANT-DICT', '租户字典', '测试租户字典推送和同步', b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185339424769, 'PAYMENT_TYPE', '付款类型', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185343619074, 'PAYMENT_METHOD', '付款方法', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
INSERT INTO `t_tenant_dict` (`id`, `code`, `name`, `description`, `status`, `readonly`, `sequence`, `tenant_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185352007681, 'CONTRACT_SEAL_TYPE', '用印类型', NULL, b'1', b'1', NULL, 1, b'0', 1, '平台管理员', '2024-12-08 18:29:14', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_dict_item`;
CREATE TABLE `t_tenant_dict_item` (
  `id` bigint NOT NULL COMMENT '雪花算法id',
  `dict_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典ID',
  `dict_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典编码',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典值',
  `label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '子项名',
  `readonly` bit(1) DEFAULT b'0' COMMENT '是否只读 true = 只读;false = 可编辑修改',
  `status` bit(1) DEFAULT b'1' COMMENT '状态',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '颜色',
  `deleted` bit(1) DEFAULT b'0',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '描述',
  `sequence` int DEFAULT '1' COMMENT '排序',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id，t_tenant.id',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_tenant_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185528168449, '1698634189967577089', 'PAYMENT_METHOD', '2', '支票', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185532362753, '1698634189967577089', 'PAYMENT_METHOD', '1', '电汇', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185536557058, '1698634230920761345', 'PAYMENT_TYPE', 'L0106', '退供应商履约保证金', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185536557059, '1698634230920761345', 'PAYMENT_TYPE', 'L010', '预付款', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185544945666, '1698634230920761345', 'PAYMENT_TYPE', 'L0104', '退质保金-供应商', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185544945667, '1686979951801745410', 'CONTRACT_SEAL_TYPE', 'officialSeal', '公章', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185553334273, '1686979951801745410', 'CONTRACT_SEAL_TYPE', 'contractSeal', '合同章', b'1', b'1', NULL, b'0', '', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185557528577, '1865614807147950081', 'TENANT-DICT', 'B', '租户B', b'1', b'1', NULL, b'0', '租户B', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
INSERT INTO `t_tenant_dict_item` (`id`, `dict_id`, `dict_code`, `value`, `label`, `readonly`, `status`, `color`, `deleted`, `description`, `sequence`, `tenant_id`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865705185557528578, '1865614807147950081', 'TENANT-DICT', 'A', '租户A', b'1', b'1', NULL, b'0', '租户A', 1, 1, 1, '平台管理员', '2024-12-08 18:29:14', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_tenant_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant_setting`;
CREATE TABLE `t_tenant_setting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `site_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '站点',
  `site_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '站点标题',
  `site_sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '子标题',
  `site_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'LOGO',
  `db_id` bigint DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT NULL,
  `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_modified_by` bigint DEFAULT NULL,
  `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='租户配置信息';

-- ----------------------------
-- Records of t_tenant_setting
-- ----------------------------
BEGIN;
INSERT INTO `t_tenant_setting` (`id`, `tenant_id`, `site_url`, `site_title`, `site_sub_title`, `site_logo`, `db_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, '0000.wp.com', 'WEMIRR SAAS 平台', '欢迎来到 wemirr-platform 云 saas 平台', NULL, 1, b'0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tenant_setting` (`id`, `tenant_id`, `site_url`, `site_title`, `site_sub_title`, `site_logo`, `db_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (2, 2, '2222.wp.com', '2222 - 平台', '欢迎来到 WP 2222 平台', NULL, 1, b'0', NULL, NULL, NULL, 1, '平台管理员', '2024-12-12 17:35:19');
INSERT INTO `t_tenant_setting` (`id`, `tenant_id`, `site_url`, `site_title`, `site_sub_title`, `site_logo`, `db_id`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (3, 3, '8888.wp.com', '8888', '8888', NULL, 1, b'0', NULL, NULL, NULL, 1, '平台管理员', '2024-12-11 20:13:40');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `org_id` bigint DEFAULT NULL COMMENT '组织ID',
  `position_id` bigint DEFAULT NULL COMMENT '岗位ID',
  `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否内置',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '手机',
  `id_card` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证',
  `sex` tinyint DEFAULT '1' COMMENT '性别',
  `status` bit(1) DEFAULT b'0' COMMENT '状态 \n1启用 0禁用',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '头像',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '民族',
  `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '学历',
  `position_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '职位状态',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `last_login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最后一次登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1871805475306237954 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1, 1, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '平台管理员', 100, 100, b'0', '1837307557@qq.com', '13002171111', '111111111', 1, b'1', 'http://127.0.0.1:19000/wp-local/dev/6766346cd59052dfcedc7531.png', '平台最帅的超级管理员', 'mz_daiz', 'SUOSHI', 'QUIT', '2023-10-14', '0:0:0:0:0:0:0:1', '2025-01-09 03:44:34', b'0', 0, NULL, '2020-10-16 03:25:36', NULL, NULL, '2025-01-09 03:44:34');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (23, 1, 'test', '{bcrypt}$2a$10$j1U0rIRl8ODzc2j5rkLx8OSvjjEZ.cJ/Xe8DzkqL2jgAyX2c3x.4C', '444', NULL, NULL, b'0', '1837307557@qq.com', '13002171921', NULL, 1, b'0', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', 1, '长风一梦8888', '2022-12-04 08:05:29', NULL, NULL, '2024-12-07 14:34:19');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (24, 1, 'admin2', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '测试管理员', 100, 100, b'0', '1837307557@qq.com', '13002171921', NULL, 1, b'1', 'http://127.0.0.1:19000/wp-local/dev/67663480d59052dfcedc7532.png', '修改描述信息吧222', 'mz_daiz', 'XIAOXUE', 'QUIT', NULL, '0:0:0:0:0:0:0:1', '2024-12-07 22:34:47', b'0', 1, '长风一梦8888', '2023-02-21 01:17:43', 1, '平台管理员', '2024-12-21 11:22:43');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1710534752819261442, 2, 'admin', '{bcrypt}$2a$10$VjkzPHKfMoN0ZNLJSl.PkOWx9LVA7BqLmcI6sgyvzpe5D1INtm8xe', '雷军', NULL, NULL, b'0', '1837307557@qq.com', '13002176666', NULL, 1, b'1', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', 1, '长风一梦8888', NULL, 0, NULL, '2024-12-05 15:36:32');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1725059377381027842, 1, 'admin3', '{bcrypt}$2a$10$oaKlJc26SNFT4obD4B0aOOil12YtKH7/yyayzhbGrLrWyAgc.X.Ji', 'admin3', 10001, 104, b'0', '1837307557@qq.com', '13002171934', NULL, 2, b'0', '', NULL, NULL, NULL, 'QUIT', NULL, NULL, NULL, b'1', 1, '长风一梦0000', '2023-11-16 15:53:00', 1, '长风一梦000011', '2024-12-05 15:36:32');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1863475850498969601, 1, 'guoxue', '{bcrypt}$2a$10$cLEEmhkxOWcRamyIy79dKuUleCXfNZmk5EwaZxNa/3/6BglOnEokm', '2323', 10001, 101, b'0', '1837307557@qq.com', '13002171933', NULL, 1, b'1', 'http://127.0.0.1:19000/wp-local/dev/67663398d59052dfcedc7530.png', '11', 'mz_lhz', 'COLLEGE', 'WORKING', NULL, '0:0:0:0:0:0:0:1', '2024-12-09 14:56:49', b'0', NULL, NULL, '2024-12-02 14:50:39', 1, '平台管理员', '2024-12-21 11:18:52');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1865599612446965761, 3, 'admin', '{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K', '马化腾', NULL, NULL, b'0', NULL, '13002178888', NULL, 1, b'1', '', NULL, NULL, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', '2025-01-09 03:43:54', b'0', NULL, NULL, '2024-12-08 11:29:43', NULL, NULL, '2025-01-09 03:43:54');
INSERT INTO `t_user` (`id`, `tenant_id`, `username`, `password`, `nick_name`, `org_id`, `position_id`, `readonly`, `email`, `mobile`, `id_card`, `sex`, `status`, `avatar`, `description`, `nation`, `education`, `position_status`, `birthday`, `last_login_ip`, `last_login_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1871805475306237953, 3, '123456', '{bcrypt}$2a$10$EOyTopgyQtHP3Nn2ODJZde1F.kc8v/BFQUkx5.zKY62wFzFR1A3US', '123', 1865599612409217025, NULL, b'0', '312', '13000001122', NULL, 1, b'1', '', '123', NULL, NULL, 'QUIT', NULL, NULL, NULL, b'0', 1865599612446965761, '马化腾', '2024-12-25 14:29:36', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user_third_account
-- ----------------------------
DROP TABLE IF EXISTS `t_user_third_account`;
CREATE TABLE `t_user_third_account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `type` varchar(50) NOT NULL COMMENT '平台类型',
  `account_id` varchar(255) NOT NULL COMMENT '第三方平台用户唯一标识',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `nickname` varchar(100) NOT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(500) DEFAULT NULL COMMENT '用户头像 URL',
  `access_token` text NOT NULL COMMENT '访问令牌',
  `refresh_token` text COMMENT '刷新令牌',
  `token_expire_time` datetime DEFAULT NULL COMMENT '令牌过期时间',
  `deleted` bit(1) DEFAULT b'0',
  `created_by` bigint DEFAULT '0' COMMENT '创建人id',
  `created_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_by` bigint DEFAULT '0' COMMENT '更新人id',
  `last_modified_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人名称',
  `last_modified_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_platform` (`type`,`account_id`),
  KEY `idx_created_time` (`created_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1875008671508062211 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='第三方授权账户表';

-- ----------------------------
-- Records of t_user_third_account
-- ----------------------------
BEGIN;
INSERT INTO `t_user_third_account` (`id`, `user_id`, `type`, `account_id`, `username`, `nickname`, `email`, `avatar`, `access_token`, `refresh_token`, `token_expire_time`, `deleted`, `created_by`, `created_name`, `created_time`, `last_modified_by`, `last_modified_name`, `last_modified_time`) VALUES (1875008671508062210, NULL, 'gitee', '483986', 'battcn-levin', 'Levin丶枫过无痕', NULL, 'https://foruda.gitee.com/avatar/1676915438129308948/483986_battcn-levin_1578925304.png', '734c4243f9b25732e922c22b6ff1126f', '4a7124450744a3855b7da82ecc8b5af5d54a9f84db7dac8ab76989971f65ca63', NULL, b'0', NULL, NULL, '2025-01-03 10:37:58', NULL, NULL, '2025-01-03 15:19:35');
COMMIT;

-- ----------------------------
-- Table structure for c_generate_table
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_table`;
CREATE TABLE `c_generate_table`  (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                     `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
                                     `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表描述',
                                     `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实体类名称(首字母大写)',
                                     `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生成包路径',
                                     `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生成模块名',
                                     `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
                                     `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                                     `business_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名称 [英文，用于api路径命名]',
                                     `remove_prefix` tinyint(1) NULL DEFAULT 0 COMMENT '是否去掉前缀',
                                     `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前缀',
                                     `template_group_id` bigint NULL DEFAULT NULL COMMENT '关联模板组',
                                     `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `created_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                                     `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                                     `last_modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                     `last_modified_by` bigint NULL DEFAULT NULL COMMENT '最后修改人ID',
                                     `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
                                     `deleted` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                     `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户ID',
                                     `swagger` tinyint(1) NULL DEFAULT 1 COMMENT '是否开启swagger配置',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1892201850246184962 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_generate_table
-- ----------------------------
INSERT INTO `c_generate_table` VALUES (1893148668509442050, 'b_message_channel', '消息通知', 'BMessageChannel', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'bMessageChannel', 0, '', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 11:59:50', 1, '平台管理员', b'1', 1, 1);
INSERT INTO `c_generate_table` VALUES (1893152402421485569, 'b_message_notify', '消息通知', 'BMessageNotify', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'bMessageNotify', 0, '', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 12:14:40', 1, '平台管理员', b'1', 1, 1);
INSERT INTO `c_generate_table` VALUES (1893152842248863745, 'b_message_template', '站内消息', 'BMessageTemplate', 'com.wemirr.platform', 'GenCode', '平台管理员', NULL, 'bMessageTemplate', 0, '', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 12:16:25', 1, '平台管理员', b'1', 1, 1);
INSERT INTO `c_generate_table` VALUES (1893154833356275714, 'b_message_channel', '消息通知', 'MessageChannel', 'com.wemirr.platform', 'suite', '平台管理员', NULL, 'messageChannel', 1, 'b_', 1872173937937879041, '2025-02-10 09:32:36', 1, '平台管理员', '2025-02-22 12:26:19', 1, '平台管理员', b'0', 1, 1);

-- ----------------------------
-- Table structure for c_generate_table_column
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_table_column`;
CREATE TABLE `c_generate_table_column`  (
                                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                            `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称 [ alipan 是用表名称进行关联的 ]',
                                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
                                            `sort` int NULL DEFAULT NULL COMMENT '排序',
                                            `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型',
                                            `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注释',
                                            `property_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性类型',
                                            `property_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性名',
                                            `property_package` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '属性所属的包',
                                            `pk` tinyint(1) NULL DEFAULT NULL COMMENT '主键 0：否 1：是',
                                            `increment` tinyint(1) NULL DEFAULT NULL COMMENT '是否自增',
                                            `required` tinyint(1) NULL DEFAULT NULL COMMENT '是否必填',
                                            `inserted` tinyint(1) NULL DEFAULT NULL COMMENT '是否为插入字段',
                                            `edit` tinyint(1) NULL DEFAULT NULL COMMENT '是否编辑字段',
                                            `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                            `created_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                                            `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                                            `last_modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                            `last_modified_by` bigint NULL DEFAULT NULL COMMENT '最后修改人ID',
                                            `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
                                            `deleted` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                            `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户ID',
                                            `generate` tinyint(1) NULL DEFAULT 1 COMMENT '当前字段是否需要生成',
                                            `list` tinyint(1) NULL DEFAULT 1 COMMENT '当前字段是否列表展示',
                                            `search` tinyint(1) NULL DEFAULT 0 COMMENT '当前字段是否作为查询条件',
                                            `search_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查询条件',
                                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1892201850338459653 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成表列' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_generate_table_column
-- ----------------------------
INSERT INTO `c_generate_table_column` VALUES (1893154833150754817, 'b_message_channel', 'id', 1, 'bigint', 'ID', 'Long', 'id', NULL, 1, 1, 0, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 0, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833184309249, 'b_message_channel', 'title', 2, 'varchar', '渠道标题', 'String', 'title', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833196892161, 'b_message_channel', 'type', 3, 'varchar', '消息类型', 'String', 'type', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833196892162, 'b_message_channel', 'status', 4, 'bit', '状态（0=禁用;1=启用）', 'Boolean', 'status', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833205280769, 'b_message_channel', 'setting', 5, 'json', '设置（JSON）', 'String', 'setting', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833209475073, 'b_message_channel', 'tenant_id', 6, 'bigint', '租户ID', 'Long', 'tenantId', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833209475074, 'b_message_channel', 'description', 7, 'varchar', '描述信息', 'String', 'description', NULL, 0, 0, 1, 1, 1, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 1, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833217863682, 'b_message_channel', 'deleted', 8, 'bit', '', 'Boolean', 'deleted', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833222057986, 'b_message_channel', 'created_by', 9, 'bigint', '创建人id', 'Long', 'createdBy', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833222057987, 'b_message_channel', 'created_name', 10, 'varchar', '创建人名称', 'String', 'createdName', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833230446593, 'b_message_channel', 'created_time', 11, 'datetime', '创建时间', 'Date', 'createdTime', 'java.util.Date', 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833259806722, 'b_message_channel', 'last_modified_by', 12, 'bigint', '更新人id', 'Long', 'lastModifiedBy', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833293361153, 'b_message_channel', 'last_modified_name', 13, 'varchar', '更新人名称', 'String', 'lastModifiedName', NULL, 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');
INSERT INTO `c_generate_table_column` VALUES (1893154833293361154, 'b_message_channel', 'last_modified_time', 14, 'datetime', '更新时间', 'Date', 'lastModifiedTime', 'java.util.Date', 0, 0, 1, 0, 0, '2025-02-22 12:24:20', 1, '平台管理员', '2025-02-22 12:24:20', NULL, NULL, b'0', 1, 0, 1, 0, 'LIKE');

-- ----------------------------
-- Table structure for c_generate_template
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_template`;
CREATE TABLE `c_generate_template`  (
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板名称',
                                        `generate_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板路径',
                                        `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板描述',
                                        `code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板代码',
                                        `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `created_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                                        `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                                        `last_modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                        `last_modified_by` bigint NULL DEFAULT NULL COMMENT '最后修改人ID',
                                        `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
                                        `deleted` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                        `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户ID',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1872465281231159298 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用于存储代码生成模板的信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_generate_template
-- ----------------------------
INSERT INTO `c_generate_template` VALUES (1872172480907972610, 'wp规范service', 'backend/src/main/java/${packagePath}/${moduleName}/service/${ClassName}Service.java', '', 'package ${package}.${moduleName}.service;\n\nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport com.wemirr.framework.db.mybatisplus.ext.SuperService;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\n\npublic interface ${ClassName}Service extends SuperService<${ClassName}>{\n\n    /**\n    * 分页查询\n    * @param req 分页请求参数\n    * @return 分页结果\n    */\n    IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req);\n\n     /**\n     * 通过id查询详情\n     * @param id 实体ID\n     * @return 详情响应\n     */\n     ${ClassName}DetailResp detail(Long id);\n\n     /**\n     * 新增实体\n     * @param req 新增请求参数\n     */\n     void create(${ClassName}SaveReq req);\n\n     /**\n     * 修改实体\n     * @param id 实体ID\n     * @param req 修改请求参数\n     */\n     void modify(Long id, ${ClassName}SaveReq req);\n\n     /**\n     * 通过id删除实体\n     * @param id 实体ID\n     */\n     void removeById(Long id);\n }', '2024-12-26 14:47:57', 1, '平台管理员', '2025-02-22 11:58:16', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872172659639848962, 'wp规范serviceImpl', 'backend/src/main/java/${packagePath}/${moduleName}/service/impl/${ClassName}ServiceImpl.java', '', 'package ${package}.${moduleName}.service.impl;\n\nimport cn.hutool.core.bean.BeanUtil;\nimport com.wemirr.framework.commons.BeanUtilPlus;\nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;\nimport com.wemirr.framework.commons.exception.CheckedException;\nimport com.wemirr.framework.db.mybatisplus.wrap.Wraps;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\nimport ${package}.${moduleName}.repository.${ClassName}Mapper;\nimport ${package}.${moduleName}.service.${ClassName}Service;\nimport lombok.RequiredArgsConstructor;\nimport org.springframework.stereotype.Service;\nimport org.springframework.transaction.annotation.Transactional;\n\nimport java.util.Optional;\n\n@Service\n@RequiredArgsConstructor\npublic class ${ClassName}ServiceImpl extends SuperServiceImpl<${ClassName}Mapper, ${ClassName}> implements ${ClassName}Service {\n\n    private final ${ClassName}Mapper ${businessName}Mapper;\n\n    @Override\n    public IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req) {\n       return this.baseMapper.selectPage(req.buildPage(), null)\n       .convert(x -> BeanUtil.toBean(x, ${ClassName}PageResp.class));\n     }\n\n     @Override\n     public ${ClassName}DetailResp detail(Long id) {\n     ${ClassName} ${businessName} = this.baseMapper.selectById(id);\n        Optional.ofNullable(${businessName})\n        .orElseThrow(() -> CheckedException.notFound(\"实体不存在\"));\n        return BeanUtil.toBean(${businessName}, ${ClassName}DetailResp.class);\n     }\n\n     @Override\n     public void create(${ClassName}SaveReq req) {\n         ${ClassName} ${businessName} = BeanUtil.toBean(req, ${ClassName}.class);\n         this.baseMapper.insert(${businessName});\n     }\n\n     @Override\n     public void modify(Long id, ${ClassName}SaveReq req) {\n          Optional.ofNullable(this.baseMapper.selectById(id))\n          .orElseThrow(() -> CheckedException.notFound(\"实体不存在\"));\n          ${ClassName} ${businessName} = BeanUtilPlus.toBean(id,req, ${ClassName}.class);\n          this.baseMapper.updateById(${businessName});\n     }\n\n     @Override\n     public void removeById(Long id) {\n       this.baseMapper.deleteById(id);\n     }\n }', '2024-12-26 14:48:40', 1, '平台管理员', '2025-02-22 11:58:17', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872172878448300033, 'wp规范entity', 'backend/src/main/java/${packagePath}/${moduleName}/domain/entity/${ClassName}.java', '', 'package ${package}.${moduleName}.domain.entity;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.baomidou.mybatisplus.annotation.*;\nimport com.wemirr.framework.commons.entity.SuperEntity;\nimport lombok.AllArgsConstructor;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\nimport lombok.NoArgsConstructor;\nimport lombok.experimental.SuperBuilder;\n\n\n/**\n* <p>\n* ${table.comment!}\n* </p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@EqualsAndHashCode(callSuper = true)\n@Data\n@SuperBuilder\n@NoArgsConstructor\n@AllArgsConstructor\n@TableName(\"${table.name}\")\n<#if swagger>\n@Schema(description = \"${table.comment!}\")\n</#if>\npublic class ${ClassName} extends SuperEntity<Long>{\n\n    <#-- ----------  BEGIN 字段循环遍历  ---------->\n    <#list columns as field>\n       <#if  field.generate>\n        <#if field.comment?has_content>\n        /**\n         * ${field.comment}\n         **/\n        </#if>\n        <#if swagger>\n        @Schema(description = \"${field.comment!}\")\n        </#if>\n        private ${field.propertyType} ${field.propertyName};\n        </#if>\n    </#list>\n    <#-- ----------  END 字段循环遍历  ---------->\n\n}', '2024-12-26 14:49:32', 1, '平台管理员', '2025-02-22 11:58:17', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872173056794300417, 'wp规范DetailResp', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/resp/${ClassName}DetailResp.java', '', 'package ${package}.${moduleName}.domain.dto.resp;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.baomidou.mybatisplus.annotation.*;\nimport com.wemirr.framework.commons.entity.SuperEntity;\nimport lombok.AllArgsConstructor;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\nimport lombok.NoArgsConstructor;\nimport lombok.experimental.SuperBuilder;\n\n\n/**\n* <p>\n    * ${table.comment!}\n    * </p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@Data\n<#if swagger>\n@Schema(description = \"${ClassName}DetailResp\")\n</#if>\npublic class ${ClassName}DetailResp {\n\n    <#-- ----------  BEGIN 字段循环遍历  ---------->\n    <#list columns as field>\n        <#if  field.generate>\n            <#if field.comment?has_content>\n            /**\n            * ${field.comment}\n            **/\n            </#if>\n            <#if swagger>\n            @Schema(description = \"${field.comment!}\")\n            </#if>\n            private ${field.propertyType} ${field.propertyName};\n        </#if>\n    </#list>\n    <#-- ----------  END 字段循环遍历  ---------->\n\n}', '2024-12-26 14:50:15', 1, '平台管理员', '2025-02-22 11:58:18', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872173195764174850, 'wp规范PageResp', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/resp/${ClassName}PageResp.java', '', 'package ${package}.${moduleName}.domain.dto.resp;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.wemirr.framework.db.mybatisplus.page.PageRequest;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\n\n/**\n* <p>\n    * ${table.comment!}\n    *</p>\n*\n* @author ${author}\n* @since ${date}\n*/\n<#if swagger>\n@Schema(description = \"${ClassName}PageReq\")\n</#if>\n@Data\npublic class ${ClassName}PageResp {\n\n<#-- ----------  BEGIN 字段循环遍历  ---------->\n<#list columns as field>\n    <#if field.list || field.pk>\n        <#if field.comment?has_content>\n            /**\n            * ${field.comment}\n            **/\n        </#if>\n        <#if swagger>\n         @Schema(description = \"${field.comment!}\")\n        </#if>\n        private ${field.propertyType} ${field.propertyName};\n    </#if>\n</#list>\n<#-- ----------  END 字段循环遍历  ---------->\n\n}\n', '2024-12-26 14:50:48', 1, '平台管理员', '2025-02-22 12:53:02', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872173350030675970, 'wp规范PageReq', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/req/${ClassName}PageReq.java', '', 'package ${package}.${moduleName}.domain.dto.req;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.wemirr.framework.db.mybatisplus.page.PageRequest;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\n\n/**\n* <p>\n* ${table.comment!}\n*</p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@Data\n@EqualsAndHashCode(callSuper = true)\n<#if swagger>\n@Schema(description = \"${ClassName}PageReq\")\n</#if>\npublic class ${ClassName}PageReq extends PageRequest {\n\n<#-- ----------  BEGIN 字段循环遍历  ---------->\n<#list columns as field>\n    <#if field.search>\n    <#if field.comment?has_content>\n    /**\n    * ${field.comment}\n    **/\n    </#if>\n    <#if swagger>\n    @Schema(description = \"${field.comment!}\")\n    </#if>\n    private ${field.propertyType} ${field.propertyName};\n    </#if>\n</#list>\n<#-- ----------  END 字段循环遍历  ---------->\n\n}\n', '2024-12-26 14:51:25', 1, '平台管理员', '2025-02-22 13:00:49', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872173466347114497, 'wp规范SaveReq', 'backend/src/main/java/${packagePath}/${moduleName}/domain/dto/req/${ClassName}SaveReq.java', '', 'package ${package}.${moduleName}.domain.dto.req;\n<#if importList?has_content>\n    <#list importList as pkg>\nimport ${pkg};\n    </#list>\n</#if>\n<#if swagger>\nimport io.swagger.v3.oas.annotations.media.Schema;\n</#if>\nimport com.wemirr.framework.db.mybatisplus.page.PageRequest;\nimport jakarta.validation.constraints.NotBlank;\nimport jakarta.validation.constraints.NotNull;\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\n\n/**\n* <p>\n* ${table.comment!}\n*</p>\n*\n* @author ${author}\n* @since ${date}\n*/\n@Data\n<#if swagger>\n@Schema(description = \"${ClassName}PageReq\")\n</#if>\npublic class ${ClassName}SaveReq  {\n\n<#-- ----------  BEGIN 字段循环遍历  ---------->\n<#list columns as field>\n    <#if field.pk >\n     /**\n      * ${field.comment}\n     **/\n    <#if swagger>\n    @Schema(description = \"${field.comment!}\")\n    </#if>\n    private ${field.propertyType} ${field.propertyName};\n    </#if>\n    <#if field.inserted  >\n    <#if field.comment?has_content>\n     /**\n      * ${field.comment}\n     **/\n    </#if>\n    <#if swagger>\n    @Schema(description = \"${field.comment!}\")\n    </#if>\n     <#if field.propertyType == \"String\">\n    @NotBlank(message = \"${field.comment}不能为空\")\n     </#if>\n    <#if field.propertyType != \"String\">\n    @NotNull(message = \"${field.comment}不能为空\")\n    </#if>\n    private ${field.propertyType} ${field.propertyName};\n    </#if>\n</#list>\n<#-- ----------  END 字段循环遍历  ---------->\n\n}\n', '2024-12-26 14:51:52', 1, '平台管理员', '2025-02-22 11:58:19', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872173626129125378, 'wp规范control', 'backend/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java', '', 'package ${package}.${moduleName}.controller;\n                     \nimport com.baomidou.mybatisplus.core.metadata.IPage;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}PageResp;\nimport ${package}.${moduleName}.domain.dto.resp.${ClassName}DetailResp;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}PageReq;\nimport ${package}.${moduleName}.domain.dto.req.${ClassName}SaveReq;\nimport ${package}.${moduleName}.service.${ClassName}Service;\nimport lombok.AllArgsConstructor;\nimport org.springframework.web.bind.annotation.*;\nimport org.springframework.web.bind.annotation.RestController;\n\n\n\n/**\n * ${table.comment!} 控制器\n * @author ${author}\n * @since ${date}\n */\n@RestController\n@AllArgsConstructor\n@RequestMapping(\"/<#if businessName??>${businessName}</#if>\")\npublic class ${ClassName}Controller {\n\n    private final ${ClassName}Service ${businessName}Service;\n\n    /**\n    * 分页查询\n    */\n    @GetMapping(\"/page\")\n    public IPage<${ClassName}PageResp> pageList(${ClassName}PageReq req) {\n        return ${businessName}Service.pageList(req);\n    }\n\n    /**\n    * 通过id查询 ${table.comment!}\n    *\n    * @param id id\n    * @return ${ClassName}DetailResp\n    */\n    @GetMapping(\"/{id}\")\n    public ${ClassName}DetailResp detail(@PathVariable(\"id\") Long id) {\n        return ${businessName}Service.detail(id);\n    }\n\n    /**\n    * 新增${table.comment!}\n    *\n    * @param ${businessName} ${businessName}\n    */\n    @PostMapping(\"/create\")\n    public void create(@RequestBody ${ClassName}SaveReq ${businessName}) {\n        ${businessName}Service.create(${businessName});\n    }\n\n    /**\n    * 修改\n    *\n    * @param id      id\n    * @param ${businessName} ${businessName}\n    */\n    @PutMapping(\"/{id}/modify\")\n    public void modify(@PathVariable(\"id\") Long id ,@RequestBody ${ClassName}SaveReq req) {\n        ${businessName}Service.modify(id,req);\n    }\n\n    /**\n    * 通过id删除${table.comment!}\n    *\n    * @param id id\n    */\n    @DeleteMapping(\"/{id}\")\n    public void remove(@PathVariable Long id) {\n        ${businessName}Service.removeById(id);\n    }\n}\n\n\n\n\n', '2024-12-26 14:52:30', 1, '平台管理员', '2025-02-22 11:58:20', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872173774800424962, 'wp规范mapper', 'backend/src/main/java/${packagePath}/${moduleName}/mapper/${ClassName}Mapper.java', '', 'package ${package}.${moduleName}.repository;\n\nimport com.wemirr.framework.db.mybatisplus.ext.SuperMapper;\nimport ${package}.${moduleName}.domain.entity.${ClassName};\nimport org.springframework.stereotype.Repository;\n\n/**\n * ${table.comment!}接口层\n *\n * @author ${author}\n * @since ${date}\n */\n\n@Repository\npublic interface ${ClassName}Mapper extends SuperMapper<${ClassName}> {\n\n}\n', '2024-12-26 14:53:06', 1, '平台管理员', '2025-02-22 11:58:21', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872180426937208833, 'wp规范crud', 'front/crud.tsx', '', 'import type {\nCreateCrudOptionsProps,\nCreateCrudOptionsRet,\n} from \'@fast-crud/fast-crud\';\nimport * as api from \"./api\";\nimport { dict } from \"@fast-crud/fast-crud\";\nimport dayjs from \"dayjs\";\n\nexport default function (props: CreateCrudOptionsProps): CreateCrudOptionsRet {\nreturn {\ncrudOptions: {\nrequest: {\npageRequest: async (query) => await api.pageList(query),\naddRequest: async ({ form }) => await api.create(form),\neditRequest: async ({ form }) => await api.modify(form),\ndelRequest: async ({ row }) => await api.remove(row.id),\n},\ntoolbar: {},\nactionbar: {\nshow: true,\nbuttons: {},\n},\nrowHandle: {\nwidth: 270,\nbuttons: {\n\n},\n},\ncolumns: {\n// ----------  BEGIN 字段循环遍历  ----------\n<#list columns as field>\n    <#if field.list || field.pk >\n        ${field.propertyName}: {\n        title: \'${field.comment!}\',\n        <#if field.propertyType == \'String\'>\n            type: \"text\",\n        </#if>\n        <#if field.propertyType == \'Instant\'>\n            type: \'datetime\',\n        </#if>\n        <#if field.propertyType == \'Boolean\'>\n            type: \'dict-radio\',\n            // 字典配置\n            dict: dict({\n            data: [\n            { value: true, label: \"启用\", color: \"success\" },\n            { value: false, label: \"禁用\", color: \"error\" },\n            ],\n            }),\n        </#if>\n        <#if field.search>\n            search: { show: true },\n        <#else>\n            search: { show: false },\n        </#if>\n         <#if field.list && !field.pk>\n            column: { show: true, width: 160 },\n        <#else>\n            column: { show: false },\n        </#if>\n         \n        <#if field.edit>\n            form: {\n            // 表单配置\n            rules: [\n            { required: false, message: \"请输入${field.comment!}\" },\n            ],\n            },\n        <#else>\n            form: { show: false },\n        </#if>\n        }\n\n        <#if field_has_next>,</#if>\n    </#if>\n</#list>\n// ----------  END 字段循环遍历  ----------\n},\n},\n};\n}', '2024-12-26 15:19:32', 1, '平台管理员', '2025-02-22 12:48:27', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872187788049780738, 'wp规范api', 'front/api.ts', '', 'import { defHttp } from \'#/api/request\';\n\nconst apiPrefix = \"/<#if moduleName??>${moduleName}</#if>/<#if businessName??>${businessName}</#if>\";\n\nexport function pageList(query : any) {\n    return defHttp.get(apiPrefix + \'/page\', { params: query });\n}\nexport function create(obj  : any) {\n    return defHttp.post(apiPrefix + `/create`,obj);\n}\n\nexport function modify(row : any) {\n    return defHttp.put(apiPrefix + `<#noparse>/${row.id}/modify</#noparse>`, row);\n}\n\nexport function remove(id : any) {\n    return defHttp.delete(apiPrefix + `<#noparse>/${id}</#noparse>`);\n}', '2024-12-26 15:48:47', 1, '平台管理员', '2025-02-22 12:30:53', 1, '平台管理员', b'0', 1);
INSERT INTO `c_generate_template` VALUES (1872188138236416002, 'wp规范index', 'front/index.vue', '', '<template>\n    <fs-page class=\"page-layout-card\">\n        <fs-crud ref=\"crudRef\" v-bind=\"crudBinding\"/>\n    </fs-page>\n</template>\n\n<script lang=\"ts\">\n    import {defineComponent, onMounted} from \"vue\";\n    import {useFs} from \"@fast-crud/fast-crud\";\n    import createCrudOptions from \"./crud\";\n\n    //此处为组件定义\nexport default defineComponent({\n    name: \'${ClassName}\',\n    setup() {\n        const {crudRef, crudBinding, crudExpose} = useFs({createCrudOptions, context: {}});\n        // 页面打开后获取列表数据\n        onMounted(() => {\n            crudExpose.doRefresh();\n        });\n        return {\n            crudBinding,\n            crudRef,\n        };\n    },\n});\n</script>\n', '2024-12-26 15:50:10', 1, '平台管理员', '2025-02-22 11:58:23', NULL, NULL, b'0', 1);

-- ----------------------------
-- Table structure for c_generate_template_group
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_template_group`;
CREATE TABLE `c_generate_template_group`  (
                                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                              `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
                                              `desciption` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板描述',
                                              `is_default` bit(1) NULL DEFAULT b'0' COMMENT '是否作为默认生成分组 (0: 不是, 1: 是)',
                                              `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                              `created_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                                              `created_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
                                              `last_modified_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
                                              `last_modified_by` bigint NULL DEFAULT NULL COMMENT '最后修改人ID',
                                              `last_modified_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后修改人名称',
                                              `deleted` bit(1) NULL DEFAULT b'0' COMMENT '逻辑删除标记 (0: 未删除, 1: 已删除)',
                                              `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户ID',
                                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1872173937937879041 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板分组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c_generate_template_group
-- ----------------------------
INSERT INTO `c_generate_template_group` VALUES (1872173937937879041, 'wp规范模板组', 'wp规范模板组', b'1', '2024-12-26 14:53:45', 1, '平台管理员', '2025-02-22 11:58:33', 1, '平台管理员', b'0', 1);

-- ----------------------------
-- Table structure for c_generate_template_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `c_generate_template_group_relation`;
CREATE TABLE `c_generate_template_group_relation`  (
                                                       `template_id` bigint NOT NULL COMMENT '模板ID',
                                                       `group_id` bigint NOT NULL COMMENT '分组id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
