package com.koi.common.i18n.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.TypeUtil;
import com.koi.common.i18n.domain.I18nMessage;
import com.koi.common.i18n.domain.I18nRedisKeyConstants;
import com.koi.common.i18n.I18nMessageProvider;
import com.koi.common.redis.listener.AbstractMessageEventListener;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author lida
 */
@RequiredArgsConstructor
public class DynamicMessageSource extends StaticMessageSource implements InitializingBean, AbstractMessageEventListener<I18nMessage> {

    private final I18nMessageProvider i18nMessageProvider;

    @Override
    public void afterPropertiesSet() {
        List<I18nMessage> list = i18nMessageProvider.list();
        if (CollUtil.isEmpty(list)) {
            return;
        }
        for (I18nMessage message : list) {
            addMessage(message.getCode(), LocaleUtils.toLocale(message.getLocale()), message.getMessage());
        }
    }

    @Override
    public void handleMessage(I18nMessage message) {
        if (message == null) {
            return;
        }
        addMessage(message.getCode(), LocaleUtils.toLocale(message.getLocale()), message.getMessage());
    }

    @Override
    public Topic topic() {
        return new ChannelTopic(I18nRedisKeyConstants.CHANNEL_I18N_DATA_UPDATED);
    }

    @Override
    public Type type() {
        return TypeUtil.getTypeArgument(I18nMessage.class);
    }
}