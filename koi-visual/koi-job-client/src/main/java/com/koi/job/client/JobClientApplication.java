package com.koi.job.client;

import com.koi.common.security.configuration.server.annotation.EnableOAuth2Server;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.koi")
@EnableOAuth2Server
public class JobClientApplication {

    /**
     * 启动类.
     *
     * @param args 启动参数
     */
    @SneakyThrows
    public static void main(final String[] args) {
        SpringApplication.run(JobClientApplication.class, args);
        log.info("""
                ----------------------------------------------------------
                Application Job Client is running!
                ----------------------------------------------------------"""
        );
    }
}
