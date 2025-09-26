package com.koi.common.redis.sequence;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis序列号生成
 *
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class RedisSequenceHelper {
    // redis key的日期前缀 隔离业务的DATE_FORMAT
    private static final DateTimeFormatter KEY_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final StringRedisTemplate redisTemplate;

    /**
     * 生成流水号 默认步进1
     *
     * @param sequence
     * @return
     */
    public String generate(Sequence sequence) {
        return generate(sequence, null, false, 1);
    }

    /**
     * 生成流水号
     *
     * @param sequence
     * @return
     */
    public String generateIncr(Sequence sequence, Integer incr) {
        return generate(sequence, null, false, incr);
    }

    /**
     * 生成流水号
     *
     * @param sequence
     * @param incr         步进
     * @param autoExpire   是否自动过期
     * @param isolationKey 隔离（比如 租户ID）
     * @return 流水号 = 前缀 + 日期时间 + 自增流水号
     */
    public String generate(Sequence sequence, Object isolationKey, boolean autoExpire, Integer incr) {
        DateTimeFormatter formatter = sequence.formatter();
        String localDate = "";
        if (formatter != null) {
            localDate = LocalDateTime.now().format(formatter);
        }
        String redisKey = sequence.key() + LocalDateTime.now().format(KEY_DATE_FORMAT);
        Long increment;
        if (isolationKey != null) {
            increment = redisTemplate.opsForHash().increment(redisKey, String.valueOf(isolationKey), incr);
        } else {
            increment = redisTemplate.opsForValue().increment(redisKey, incr);
        }

        // 避免重复刷新过期时间
        if (autoExpire && redisTemplate.getExpire(redisKey) <= 0) {
            // 计算过期时间
            long between = DateUtil.between(new Date(), DateUtil.endOfDay(new Date()), DateUnit.SECOND) + 60 * 60;
            redisTemplate.expire(redisKey, between, TimeUnit.SECONDS);
        }
        return sequence.prefix()
                + localDate
                + StrUtil.blankToDefault(sequence.delimiter(), "")
                + StrUtil.padPre(increment + "", sequence.size(), '0');
    }

    /**
     * 按规则生成流水号
     *
     * @param sequence     规则
     * @param isolationKey 隔离（比如 租户ID）
     * @return 流水号 = 前缀 + 日期时间 + 自增流水号
     */
    public String generate(Sequence sequence, Object isolationKey) {
        return generate(sequence, isolationKey, false, 1);
    }

}
