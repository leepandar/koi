package com.koi.common.db.dynamic.core.redis;

import com.koi.common.db.dynamic.core.DynamicDatasourceEvent;
import com.koi.common.db.dynamic.core.DynamicDatasourceEventPublish;
import com.koi.common.db.dynamic.core.EventAction;
import com.koi.common.db.dynamic.DynamicDataSourceHandler;
import com.koi.common.redis.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.Objects;


/**
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class RedisDynamicDatasourceListener implements AbstractMessageEventListener<DynamicDatasourceEvent> {

    private final DynamicDataSourceHandler dynamicDataSourceHandler;

    @Override
    public void handleMessage(DynamicDatasourceEvent message) {
        if (Objects.isNull(message)) {
            log.warn("event dynamicDatasource is null....");
            return;
        }
        log.info("接收租户事件消息: - {}", message);
        dynamicDataSourceHandler.handler(EventAction.of(message.getAction()), message);
    }

    @Override
    public Topic topic() {
        return new ChannelTopic(DynamicDatasourceEventPublish.DEFAULT_EVENT_TOPIC);
    }

    @Override
    public Type type() {
        return DynamicDatasourceEvent.class;
    }
}
