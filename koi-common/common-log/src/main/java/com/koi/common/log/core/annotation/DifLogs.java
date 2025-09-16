package com.koi.common.log.core.annotation;

import java.lang.annotation.*;

/**
 * @author lida
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DifLogs {

    /**
     * 日志 @DiffLog
     *
     * @return DiffLog[]
     */
    DiffLog[] value();
}
