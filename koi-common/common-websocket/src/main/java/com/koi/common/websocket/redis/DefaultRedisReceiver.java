package com.koi.common.websocket.redis;

import com.alibaba.fastjson2.JSONObject;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.redis.action.Action;
import com.koi.common.websocket.WebSocketManager;
import com.koi.common.websocket.redis.action.Action;
import com.koi.common.websocket.WebSocketManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * redis消息订阅者
 *
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultRedisReceiver implements RedisReceiver {

    private final ApplicationContext applicationContext;

    /**
     * 此方法会被反射调用
     */
    @Override
    public void receiveMessage(String message) {
        log.info(message);
        JSONObject object = JSONObject.parseObject(message);
        if (!object.containsKey(Action.ACTION)) {
            return;
        }
        String actionName = object.getString(Action.ACTION);
        Action action = getAction(actionName);
        action.doMessage(getWebSocketManager(), object);
    }

    protected Action getAction(String actionName) {
        return applicationContext.getBean(actionName, Action.class);
    }

    protected WebSocketManager getWebSocketManager() {
        return applicationContext.getBean(WebSocketManager.WEBSOCKET_MANAGER_NAME, WebSocketManager.class);
    }
}