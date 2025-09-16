package com.koi.common.core.annotation.log;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLog {

    /**
     * 模块
     *
     * @return 模块
     */
    String module();

    /**
     * 描述
     *
     * @return {String}
     */
    String description();

    /**
     * 记录执行参数
     *
     * @return true | false
     */
    boolean request() default true;

    /**
     * 记录返回参数
     *
     * @return true | false
     */
    boolean response() default true;
}
