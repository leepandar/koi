# KOI

![JDK Version](https://img.shields.io/badge/JAVA-JDK8+-red.svg)[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://www.codacy.com/gh/battcn/wemirr-platform/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=battcn/wemirr-platform&amp;utm_campaign=Badge_Grade)  
[![Spring Boot](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-dependencies.svg?label=Spring%20Boot&logo=Spring)](https://search.maven.org/artifact/org.springframework.boot/spring-boot-dependencies)  [![Spring Cloud](https://img.shields.io/maven-central/v/org.springframework.cloud/spring-cloud-dependencies.svg?label=Spring%20Cloud&logo=Spring)](https://search.maven.org/artifact/org.springframework.cloud/spring-cloud-dependencies)  [![Spring Cloud Alibaba](https://img.shields.io/maven-central/v/com.alibaba.cloud/spring-cloud-alibaba-dependencies.svg?label=Spring%20Cloud%20Alibaba&logo=Spring)](https://search.maven.org/artifact/com.alibaba.cloud/spring-cloud-alibaba-dependencies)

## 一、软件架构

Vue、Spring Cloud Alibaba 2023、Spring Cloud 2023、Nacos、Sentinel、 Mybatis-Plus、多租户、灰度、Sa-Token、Redis、Mysql、


## 二、核心依赖

| 依赖                          | 版本         |
|-----------------------------|------------|
| Spring Boot                 | 3.2.3      |
| Spring Cloud                | 2023.0.0   |
| Spring Cloud Alibaba        | 2023.0.0.0 |
| Spring Authorization Server | 1.2.2      |
| Mybatis Plus                | 3.5.5      |

## 三、模块说明

```lua
koi  -- https://github.com/leepandar/koi.git
├
└── doc -- 文档
     ├── db -- 数据库脚本
     ├── nacos -- nacos配置文件与启动包
     ├── nginx -- nginx发布配置文件
└──  koi-api -- 系统公共模块   
     ├── koi-bpm-api -- 工作流服务接口  
     ├── koi-iam-api -- 身份访问管理服务接口  
     ├── koi-suit-api -- 开发工具套件接口  
└── iot-common -- 系统公共模块
     ├── common-core -- 公共工具类核心包
     ├── common-db -- 数据源
     ├── common-excel -- easy-excel
     ├── common-feign -- feign扩展 
     ├── common-i18n -- 多语言
     ├── common-idempotent -- 幂等处理 
     ├── common-job -- 定时任务 
     ├── common-log -- 日志
     ├── common-redis -- redis
     ├── common-robot -- 钉钉、微信、飞书机器人通知
     ├── common-security -- 安全工具类
     ├── common-spring-boot -- 启动类
     ├── common-websocket -- websocket
├── koi-gateway --网关[8888]
└──  koi-modules --业务模块
     ├── koi-bpm -- 工作流模块 [8003]
     ├── koi-iam -- 身份访问管理服务 [8001]
     ├── koi-suite -- 开发工具套件服务 [8002]
├── koi-nacos -- 注册中心 [8848]
└── koi-visual -- 运维可视化
     ├── koi-job-client -- 定时任务客户端 [7003]
     ├── koi-job-server -- 定时任务服务端 [7002]
     ├── koi-monitor -- 监控服务 [7001]
```


## 四、环境安装
### 1.Mysql
执行脚本`doc/db/schema.sql`创建数据库`koi`、`koi_nacos`、`koi_job`、`koi_bpm`
>`koi`：系统数据库(必须)

>`koi_nacos`：nacos配置数据库(必须)

>`koi_job`：定时任务数据库(非必须)

>`koi_bpm`：工作流数据库(非必须)

### 2.Redis
本地安装`Redis`或者使用`Docker`启动,可直接在`docker-compose`中运行
```azure
docker-compose up -d koi-redis
```

### 3.Node.js
`node`版本>20.9.0

### 4.pnpm
```azure
npm install -g pnpm
```

### 5.启动服务
启动顺序
- koi-nacos [8848]
- koi-gateway [8888]
- koi-iam [8001]
- koi-suite [8002] 非必须
- koi-bpm [8003] 非必须
- koi-monitor [7001] 非必须
- koi-job-server [7002] 非必须
- koi-job-client [7003] 非必须


## 五、帐号

> 平台账号 0000 账号 admin 密码 123456

> 租户账号 8888 账号 admin 密码 123456
