package com.koi.common.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.koi.common.websocket.WebSocket;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.WebSocket;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.utils.WebSocketUtil;
import com.koi.common.websocket.WebSocket;
import com.koi.common.websocket.WebSocketManager;

/**
 * {
 * "action":"sendMessage",
 * "identifier":"xxx",
 * "message":"xxxxxxxxxxx"
 * }
 * 给webSocket发送消息的action
 *
 * @author lida
 */
public class SendMessageAction implements Action {

    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        if (!object.containsKey(IDENTIFIER)) {
            return;
        }
        if (!object.containsKey(MESSAGE)) {
            return;
        }

        String identifier = object.getString(IDENTIFIER);

        WebSocket webSocket = manager.get(identifier);
        if (null == webSocket) {
            return;
        }
        WebSocketUtil.sendMessage(webSocket.getSession(), object.getString(MESSAGE));
    }
}
