package com.koi.common.job.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "snail-job.server")
public class SnailJobServerProperties {

    private String serverName;

    private String port;
}
