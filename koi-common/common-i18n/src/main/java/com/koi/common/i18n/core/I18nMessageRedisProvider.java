package com.koi.common.i18n.core;

import com.alibaba.fastjson2.JSON;
import com.koi.common.i18n.domain.I18nMessage;
import com.koi.common.i18n.domain.I18nRedisKeyConstants;
import com.koi.common.i18n.I18nMessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lida
 */
@RequiredArgsConstructor
public class I18nMessageRedisProvider implements I18nMessageProvider {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getI18nMessage(String code, Locale locale) {
        String buildKey = I18nMessage.builder().code(code).locale(locale.toString()).build().buildKey();
        I18nMessage message = (I18nMessage) redisTemplate.opsForHash().get(I18nRedisKeyConstants.I18N_DATA_PREFIX, buildKey);
        return message == null ? null : message.getMessage();
    }

    @Override
    public List<I18nMessage> list() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(I18nRedisKeyConstants.I18N_DATA_PREFIX);
        return entries.values().stream().filter(Objects::nonNull).map(x -> (I18nMessage) x).toList();
    }

    @Override
    public void loadI18nMessage(List<I18nMessage> messages) {
        if (messages == null) {
            return;
        }
        redisTemplate.delete(I18nRedisKeyConstants.I18N_DATA_PREFIX);
        final Map<String, I18nMessage> map = messages.stream().collect(Collectors.toMap(I18nMessage::buildKey, Function.identity()));
        redisTemplate.opsForHash().putAll(I18nRedisKeyConstants.I18N_DATA_PREFIX, map);
    }

    public void publish(List<I18nMessage> list) {
        if (list == null) {
            return;
        }
        for (I18nMessage message : list) {
            publish(message);
        }
    }

    public void publish(I18nMessage message) {
        redisTemplate.convertAndSend(I18nRedisKeyConstants.CHANNEL_I18N_DATA_UPDATED, JSON.toJSONString(message));
    }
}
