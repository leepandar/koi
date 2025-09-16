package com.koi.common.redis.exception;

import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * Redis Lock 异常
 *
 * @author lida
 * @since 2019-03-13
 */
@Setter
@EqualsAndHashCode(callSuper = true)
public class RedisLockException extends RuntimeException {

    private int code;

    public RedisLockException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public RedisLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisLockException(Throwable cause) {
        super(cause);
    }

    public RedisLockException(String message) {
        super(message);
    }

    public RedisLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
