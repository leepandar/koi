package com.koi.common.security.configuration;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.SaTokenContext;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import com.koi.common.core.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author lida
 */
@Slf4j
@Order(-100)
@RestControllerAdvice
public class OAuth2ExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Result<?>> handlerException(NotLoginException e) {
        log.error("no-login => http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.fail(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }

    @ExceptionHandler(SaTokenException.class)
    public ResponseEntity<Result<?>> handlerException(SaTokenException e) {
        SaTokenContext context = SaHolder.getContext();
        if (context != null && context.isValid()) {
            log.error("sa-token => http request uri => {},message => {}", context.getRequest().getUrl(), e.getLocalizedMessage());
            return ResponseEntity.ok(Result.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
        }
        return ResponseEntity.ok(Result.fail(HttpStatus.FORBIDDEN.value(), e.getMessage()));
    }
}