package com.koi.common.websocket;

import org.springframework.context.ApplicationEvent;

/**
 * @author lida
 */
public class WebSocketCloseEvent extends ApplicationEvent {

    public WebSocketCloseEvent(WebSocket webSocket) {
        super(webSocket);
    }

}
