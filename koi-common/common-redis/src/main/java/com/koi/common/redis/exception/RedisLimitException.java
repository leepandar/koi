package com.koi.common.redis.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Redis 限流异常
 *
 * @author lida
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RedisLimitException extends RuntimeException {

    private int code;

    public RedisLimitException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public RedisLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisLimitException(Throwable cause) {
        super(cause);
    }

    public RedisLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
