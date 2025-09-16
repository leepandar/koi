package com.koi.common.websocket.memory;

import com.koi.common.websocket.redis.RedisWebSocketConfiguration;
import com.koi.common.websocket.redis.RedisWebSocketConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @Author: lida
 * @Description: 注入配置
 */
@Import({
        MemoryWebSocketConfiguration.class,
        RedisWebSocketConfiguration.class
})
public class UnifiedWebSocketConfig {

}