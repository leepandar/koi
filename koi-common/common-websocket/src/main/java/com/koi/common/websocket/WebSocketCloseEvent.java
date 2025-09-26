package com.koi.common.websocket;

import org.springframework.context.ApplicationEvent;

public class WebSocketCloseEvent extends ApplicationEvent {

    public WebSocketCloseEvent(WebSocket webSocket) {
        super(webSocket);
    }

}
