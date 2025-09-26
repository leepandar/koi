package com.koi.common.springboot.remote.exception;

/**
 * 异常处理
 *
 * @author lida
 **/
public class RemoteAloneRedisException extends RuntimeException {
    public RemoteAloneRedisException(String message) {
        super(message);
    }
}
