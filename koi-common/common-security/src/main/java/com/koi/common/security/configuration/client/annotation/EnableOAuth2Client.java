package com.koi.common.security.configuration.client.annotation;

import com.koi.common.security.configuration.client.ClientResourceServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 资源服务注解
 *
 * @author lida
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@Import({ClientResourceServerConfiguration.class})
public @interface EnableOAuth2Client {

}
