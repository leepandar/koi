package com.koi.common.websocket.redis.action;

import com.alibaba.fastjson2.JSONObject;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.WebSocketManager;

/**
 * 要做的事情
 *
 * @author lida
 */
public interface Action {

    String IDENTIFIER = "identifier";
    String MESSAGE = "message";
    String ACTION = "action";

    /**
     * 根据消息做自己的事情
     *
     * @param manager webSocket管理器
     * @param object  消息体转化的JSON
     */
    void doMessage(WebSocketManager manager, JSONObject object);
}
