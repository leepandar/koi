package com.koi.common.core.annotation;

import java.lang.annotation.*;

/**
 * 全局响应数据处理注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreGlobalResponse {

    /**
     * 描述
     *
     * @return {String}
     */
    String description();

}
