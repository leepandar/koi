package com.koi.common.job;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.collection.CollUtil;
import com.aizuda.snailjob.client.common.appender.SnailLog4j2Appender;
import com.aizuda.snailjob.client.common.appender.SnailLogbackAppender;
import com.aizuda.snailjob.client.common.event.SnailChannelReconnectEvent;
import com.aizuda.snailjob.client.common.event.SnailClientStartingEvent;
import com.aizuda.snailjob.client.starter.EnableSnailJob;
import com.koi.common.job.properties.SnailJobServerProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.Layout;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

/**
 * SnailJob基础配置
 *
 * @author lida
 */
@AutoConfiguration
@EnableConfigurationProperties(SnailJobServerProperties.class)
@ConditionalOnProperty(prefix = "snail-job", name = "enabled", havingValue = "true")
@EnableScheduling
@EnableSnailJob
public class SnailJobConfig {

    @Autowired
    private SnailJobServerProperties properties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @EventListener(SnailClientStartingEvent.class)
    public void onStarting(SnailClientStartingEvent event) {
        // 从 nacos 获取 server 服务连接
        registerServer();
        // 注册 日志监控配置
        registerLogging();
    }

    @EventListener(SnailChannelReconnectEvent.class)
    public void onReconnect(SnailChannelReconnectEvent event) {
        // 连接中断 重新从 nacos 获取存活的服务连接(高可用配置)
        registerServer();
    }

    private void registerServer() {
        String serverName = properties.getServerName();
        if (StringUtils.isNotBlank(serverName)) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
            if (CollUtil.isNotEmpty(instances)) {
                ServiceInstance instance = instances.get(0);
                System.setProperty("snail-job.server.host", instance.getHost());
                System.setProperty("snail-job.server.port", properties.getPort());
            }
        }
    }

    private void registerLogging() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        SnailLogbackAppender<ILoggingEvent> ca = new SnailLogbackAppender<>();
        ca.setName("snail_log_appender");
        ca.start();
        Logger rootLogger = lc.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(ca);
    }

//    /**
//     * Logback配置类
//     */
//    @Configuration
//    @ConditionalOnClass(value = {ch.qos.logback.classic.LoggerContext.class, SnailLogbackAppender.class})
//    public static class LogbackConfiguration {
//
//        @EventListener(SnailClientStartingEvent.class)
//        public void onStarting(SnailClientStartingEvent event) {
//            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//            SnailLogbackAppender<ILoggingEvent> appender = new SnailLogbackAppender<>();
//            appender.setName("snail_log_appender");
//            appender.setContext(context);
//            appender.start();
//            Logger rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);
//            rootLogger.addAppender(appender);
//        }
//    }
//
//    /**
//     * Log4j2配置类
//     */
//    @Configuration
//    @ConditionalOnClass(value = {org.apache.logging.log4j.core.LoggerContext.class, SnailLog4j2Appender.class})
//    public static class Log4j2Configuration {
//
//        @EventListener(SnailClientStartingEvent.class)
//        public void onStarting(SnailClientStartingEvent event) {
//            org.apache.logging.log4j.core.LoggerContext context =
//                    (org.apache.logging.log4j.core.LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
//            org.apache.logging.log4j.core.config.Configuration config = context.getConfiguration();
//            Layout<?> layout = org.apache.logging.log4j.core.layout.PatternLayout.createDefaultLayout(config);
//            SnailLog4j2Appender snailAppender = SnailLog4j2Appender.create("snail_log_appender",null,layout,"true",null,null);
//            snailAppender.start();
//            config.addAppender(snailAppender);
//            config.getRootLogger().addAppender(snailAppender, org.apache.logging.log4j.Level.INFO, null);
//            context.updateLoggers();
//        }
//    }
}
