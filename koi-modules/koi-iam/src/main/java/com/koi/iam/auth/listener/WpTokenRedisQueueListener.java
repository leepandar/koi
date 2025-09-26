package com.koi.iam.auth.listener;

import com.koi.common.redis.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 在线用户队列监听
 *
 * @author lida
 */

@Slf4j
@RequiredArgsConstructor
public class WpTokenRedisQueueListener implements AbstractMessageEventListener<String> {

    private final RedisProperties properties;

    @Override
    public void handleMessage(String message) {
        if (Objects.isNull(message)) {
            log.warn("event wp-token-redis-queue is null....");
            return;
        }
        log.info("receiver wp-token redis message: - {}", message);
    }

    @Override
    public Topic topic() {
        int database = properties.getDatabase();
        return new PatternTopic("__keyevent@" + database + "__:expired");
    }

    @Override
    public Type type() {
        return String.class;
    }
}
