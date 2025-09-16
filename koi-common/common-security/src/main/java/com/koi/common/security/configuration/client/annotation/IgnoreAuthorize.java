package com.koi.common.security.configuration.client.annotation;

import java.lang.annotation.*;

/**
 * 跳过指定 resource-id 的认证操作
 * 加上该注解，接口地址将无法获得安全保护
 *
 * @author lida
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IgnoreAuthorize {

    /**
     * 默认全局忽略(如果是 false 那么就是内网忽略,外网保护)
     *
     * @return true | false
     */
    boolean global() default true;

}
