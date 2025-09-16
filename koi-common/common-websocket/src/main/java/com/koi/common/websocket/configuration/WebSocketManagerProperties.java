package com.koi.common.websocket.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.websocket.manager")
public class WebSocketManagerProperties {

    private ManagerType type = ManagerType.MEMORY;

    public enum ManagerType {
        MEMORY, REDIS
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }
}
