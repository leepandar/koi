package com.koi.common.websocket.memory;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lida
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MemoryWebSocketConfiguration.class})
public @interface EnableMemWebSocket {
}
