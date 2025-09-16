package com.koi.common.websocket;

import org.springframework.context.ApplicationEvent;

/**
 * @author lida
 */
public class WebSocketConnectEvent extends ApplicationEvent {

    public WebSocketConnectEvent(WebSocket webSocket) {
        super(webSocket);
    }
}
