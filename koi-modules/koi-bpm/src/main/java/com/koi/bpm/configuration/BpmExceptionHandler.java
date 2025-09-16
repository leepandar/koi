package com.koi.bpm.configuration;

import com.koi.common.core.entity.Result;
import cn.dev33.satoken.context.SaHolder;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * BPMN 异常处理
 *
 * @author lida
 */
@Slf4j
@RestControllerAdvice
public class BpmExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ParseException.class)
    public Result<ResponseEntity<Void>> handlerException(ParseException e) {
        String message = "BPMN 异常[" + e.getLocalizedMessage() + "]";
        log.error("bpm-parse => http request uri => {},message => {}", SaHolder.getRequest().getUrl(), e.getLocalizedMessage());
        return Result.fail(HttpStatus.BAD_REQUEST.value(), message);
    }

}