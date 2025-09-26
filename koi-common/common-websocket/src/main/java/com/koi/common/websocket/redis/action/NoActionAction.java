package com.koi.common.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.koi.common.websocket.WebSocketManager;

/**
 * do nothing action
 */
public class NoActionAction implements Action {

    @Override
    public void doMessage(WebSocketManager manager, JSONObject object) {
        // do no thing
    }
}
