package com.koi.common.db.dynamic.core.redis;

import com.alibaba.fastjson2.JSON;
import com.koi.common.db.dynamic.core.DynamicDatasourceEvent;
import com.koi.common.db.dynamic.core.DynamicDatasourceEventPublish;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis 发布动态数据源事件
 *
 * @author lida
 */
@RequiredArgsConstructor
public class RedisDynamicDatasourcePublish implements DynamicDatasourceEventPublish {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void publish(DynamicDatasourceEvent message) {
        log.info("redis publish - {}", message);
        redisTemplate.convertAndSend(DEFAULT_EVENT_TOPIC, JSON.toJSONString(message));
    }

}
