package com.koi.common.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.koi.common.websocket.WebSocket;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.WebSocket;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.WebSocket;
import com.koi.common.websocket.WebSocketManager;

import java.util.Map;

/**
 * {
 * "action":"remove",
 * "identifier":"xxx"
 * }
 * 给webSocket发送消息的action
 */
public class RemoveAction implements Action {

    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        if (!object.containsKey(IDENTIFIER)) {
            return;
        }
        String identifier = object.getString(IDENTIFIER);
        Map<String, WebSocket> localWebSocketMap = manager.localWebSocketMap();
        localWebSocketMap.remove(identifier);
    }
}
