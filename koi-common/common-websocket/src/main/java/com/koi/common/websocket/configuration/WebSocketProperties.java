package com.koi.common.websocket.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lida
 */
@Data
@ConfigurationProperties(prefix = "spring.websocket.heart-check")
public class WebSocketProperties {

    private boolean enabled = true;
    /**
     * 每隔 30s 检查一次
     */
    private String trigger = "30 * * * * ?";
    private long timeSpan = 10000;
    private int errorToleration = 30;

}
