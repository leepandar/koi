package com.koi.common.redis.listener;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;

/**
 * @author lida
 */
public interface MessageEventListener extends MessageListener {

    /**
     * 订阅者订阅的话题
     *
     * @return topic
     */
    Topic topic();

    /**
     * 序列化类型
     *
     * @return 类型
     */
    Type type();
}
