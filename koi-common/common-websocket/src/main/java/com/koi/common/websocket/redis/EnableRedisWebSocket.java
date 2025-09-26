package com.koi.common.websocket.redis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({RedisWebSocketConfiguration.class})
public @interface EnableRedisWebSocket {
}
