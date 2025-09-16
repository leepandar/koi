package com.koi.common.core.annotation;

import java.lang.annotation.*;

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
