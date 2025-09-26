package com.koi.common.security.configuration.server.annotation;

import com.koi.common.security.configuration.server.AuthorizationServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 授权服务注解
 *
 * @author lida
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AuthorizationServerConfiguration.class})
public @interface EnableOAuth2Server {

}
