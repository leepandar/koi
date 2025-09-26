package com.koi.common.security.configuration;


import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 认证配置
 *
 * @author lida
 */
@EnableConfigurationProperties(SecurityExtProperties.class)
public class OAuth2AutoConfiguration implements WebMvcConfigurer {
    @Resource
    private SecurityExtProperties extProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 拦截的 path 列表，可以写多个 /**
            SaRouter.match("/**")
                    .notMatch(extProperties.getDefaultIgnoreUrls())
                    .notMatch(extProperties.getIgnore().getResourceUrls())
                    .check(r -> {
                        String sameToken = SaHolder.getRequest().getHeader(SaSameUtil.SAME_TOKEN);
                        if (StringUtils.isNotBlank(sameToken)) {
                            SaSameUtil.checkToken(sameToken);
                            return;
                        }
                        StpUtil.checkLogin();
                    });
        })).addPathPatterns("/**");
    }
}
