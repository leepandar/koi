package com.koi.common.websocket.memory;

import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.configuration.WebSocketHeartBeatChecker;
import com.koi.common.websocket.configuration.WebSocketProperties;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.configuration.WebSocketHeartBeatChecker;
import com.koi.common.websocket.configuration.WebSocketProperties;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.configuration.WebSocketHeartBeatChecker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 内存管理websocket配置
 *
 * @author lida
 */
@Configuration
@EnableConfigurationProperties(WebSocketProperties.class)
@ConditionalOnProperty(name = "spring.websocket.manager.type", havingValue = "MEMORY", matchIfMissing = true)
public class MemoryWebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean(WebSocketManager.WEBSOCKET_MANAGER_NAME)
    @ConditionalOnMissingBean(name = WebSocketManager.WEBSOCKET_MANAGER_NAME)
    public WebSocketManager webSocketManager() {
        return new MemWebSocketManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketHeartBeatChecker webSocketHeartBeatChecker() {
        return new WebSocketHeartBeatChecker();
    }
}