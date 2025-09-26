package com.koi.job.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * KoiJob Server 启动程序
 *
 * @author lida
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class JobServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.aizuda.snailjob.server.SnailJobServerApplication.class, args);
        log.info("""
                        ----------------------------------------------------------
                        Application Job Server is running!
                        ----------------------------------------------------------"""
        );
    }

}
