package com.koi.common.springboot.base;

import com.koi.common.core.threadlocal.ThreadLocalHolder;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

/**
 * 专门用于设置 ThreadLocal 值的拦截器
 *
 * @author lida
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
        // 从请求头中获取地区信息
        Locale locale = request.getLocale();
        ThreadLocalHolder.setLocal(locale);
        log.debug("http header locale - {}", locale);
        return true;
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object object, Exception exception) {
        ThreadLocalHolder.clear();
    }
}
