package com.koi.common.feign.mock;

import cn.hutool.core.util.StrUtil;
import com.koi.common.feign.FeignPluginProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Enumeration;

/**
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class FeignPluginInterceptor implements RequestInterceptor {

    /**
     * 需要排除的头部字段
     */
    public static final String[] EXCLUDED_DEFAULT_HEADERS = {HttpHeaders.CONTENT_LENGTH, HttpHeaders.COOKIE};
    public final String X_MOCK_APPLICATION = "x-mock-application";
    public final String IGNORE_HEADER = "ignore-header";
    private final FeignPluginProperties properties;

    private boolean isExcluded(String headerKey) {
        for (String header : EXCLUDED_DEFAULT_HEADERS) {
            if (headerKey.contains(header) || StrUtil.equalsIgnoreCase(headerKey, header)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void apply(RequestTemplate template) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        final Collection<String> ignoreHeader = template.headers().get(IGNORE_HEADER);
        if (requestAttributes != null && properties.isEnabled()) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            final Enumeration<?> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                final String headerKey = (String) headerNames.nextElement();
                final String headerValue = request.getHeader(headerKey);
                if (isExcluded(headerKey) || ignoreHeader != null && ignoreHeader.contains(headerKey)) {
                    continue;
                }
                template.header(headerKey, headerValue);
            }
        }
        if (properties.getMock() != null && properties.getMock().isEnabled()) {
            log.debug("mock interceptor .....");
            template.header(X_MOCK_APPLICATION, "true");
        }
    }
}
