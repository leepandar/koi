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

### 后端
```lua
koi  -- https://e.coding.net/honghuzl/koi/koi.git
├
├── doc -- 文档
     ├── db -- 数据库脚本
     ├── nacos -- nacos配置文件与启动包
     ├── nginx -- nginx发布配置文件
├── koi-api -- 系统公共模块   
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
├── koi-modules --业务模块
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

> 必要环境

``` shell script
docker pull redis:latest
docker run -itd --name redis -p 6379:6379 redis

安装 Mysql 
docker pull mysql:latest
docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql
```

> 可选环境

``` shell script
## Sentinel 
docker pull bladex/sentinel-dashboard
docker run -i -t -d -p 8858:8858 -p 8719:8719  bladex/sentinel-dashboard

## skywalking
docker pull elasticsearch:7.9.3
docker pull apache/skywalking-oap-server:8.5.0-es7
docker pull apache/skywalking-ui:8.5.0

## 安装 Nacos
docker pull nacos/nacos-server
docker run --name nacos -itd -p 8848:8848 -p 9848:9848 -p 9849:9849 --restart=always -e MODE=standalone nacos/nacos-server

## 安装 RabbitMQ
docker pull docker.io/macintoshplus/rabbitmq-management
docker run -d  -p 5671:5671 -p 5672:5672  -p 15672:15672 -p 15671:15671  -p 25672:25672  rabbitmq_image_id

# 如果要后台运行 请加 -d
docker network create koi
docker run --name elasticsearch --net koi -p 9200:9200 -p 9300:9300 -d -e "discovery.type=single-node" elasticsearch:7.9.3
docker run --name oap --net koi --restart always -p 1234:1234 -p 12800:12800 -p 11800:11800 -d -e SW_STORAGE=elasticsearch7 -e SW_STORAGE_ES_CLUSTER_NODES=elasticsearch:9200 apache/skywalking-oap-server:8.5.0-es7
docker run --name oap-ui --net koi --restart always -p 10086:8080 -d -e TZ=Asia/Shanghai -e SW_OAP_ADDRESS=oap:12800 apache/skywalking-ui:8.5.0

# IDEA 配置
VmOption -javaagent:/Users/lida/Documnet/tool/apache-skywalking-apm-bin/agent/skywalking-agent.jar
Environment variables SW_AGENT_NAME=koi-gateway
Environment variables SW_AGENT_NAME=koi-iam

# 启动命令
nohup java -javaagent:/opt/koi/skywalking/agent/skywalking-agent.jar -Dskywalking.agent.service_name=koi-gateway -Dskywalking.collector.backend_service=127.0.0.1:11800 -jar koi-gateway.jar -d > logs/start_gateway.log &
nohup java -javaagent:/opt/koi/skywalking/agent/skywalking-agent.jar -Dskywalking.agent.service_name=koi-iam -Dskywalking.collector.backend_service=127.0.0.1:11800 -jar koi-iam.jar -d --spring.profiles.active=demo > logs/start_iam.log &
```

## 五、帐号

> 平台账号 0000 账号 admin 密码 123456

> 租户账号 8888 账号 admin 密码 123456
