package com.koi.bpm;

import com.koi.common.security.configuration.client.annotation.EnableOAuth2Client;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;

/**
 * 工作流服务启动类.
 *
 * @author lida
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.koi")
@MapperScan(value = "com.koi.**.mapper", annotationClass = Repository.class)
@EnableOAuth2Client
public class BpmApplication {

    @SneakyThrows
    public static void main(String[] args) {
        System.setProperty("skipIsolationLevelCheck", "true");
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(BpmApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        final String appName = env.getProperty("spring.application.name");
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("""
                        
                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
                        \tDoc: \thttp://{}:{}/doc.html
                        ----------------------------------------------------------""",
                appName, host, port);
    }

}
