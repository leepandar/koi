package com.koi.common.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.utils.WebSocketUtil;
import com.koi.common.websocket.WebSocketManager;

/**
 * {
 * "action":"broadcast",
 * "message":"xxxxxxxxxxxxx"
 * }
 * 广播给所有的websocket发送消息 action
 *
 */
public class BroadCastAction implements Action {

    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        if (!object.containsKey(MESSAGE)) {
            return;
        }
        String message = object.getString(MESSAGE);
        // 从本地取出所有的websocket发送消息
        manager.localWebSocketMap().values().forEach(
                webSocket -> WebSocketUtil.sendMessage(
                        webSocket.getSession(), message));
    }
}
