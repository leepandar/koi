package com.koi.common.db.dynamic;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.properties.DatabaseProperties;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lida
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "extend.mybatis-plus.multi-tenant", name = "type", havingValue = "datasource")
public class DynamicDatasourceWebAutoConfigurer implements WebMvcConfigurer {

    private final AuthenticationContext context;
    private final DatabaseProperties properties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {

            /**
             * 在请求处理之前进行调用（Controller方法调用之前）
             */
            @Override
            public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
                var requestUri = request.getRequestURI();
                var multiTenant = properties.getMultiTenant();
                if (context.anonymous()) {
                    // 匿名用户不做数据源切换,默认走主库
                    log.debug("经过多数据源Interceptor,数据源是 => {},请求地址 => {}", multiTenant.getDefaultDsName(), requestUri);
                    return true;
                }
                var tenantCode = StrUtil.blankToDefault(context.tenantCode(), request.getHeader(multiTenant.getTenantCodeColumn()));
                // 尝试从 header 获取
                if (StringUtils.isBlank(tenantCode)) {
                    tenantCode = request.getHeader(multiTenant.getTenantCodeColumn());
                }
                // 尝试从 params 获取
                if (StringUtils.isBlank(tenantCode)) {
                    tenantCode = request.getParameter(multiTenant.getTenantCodeColumn());
                }
                String prefix = multiTenant.getDsPrefix();
                String dsKey;
                // 如果租户编码不是平台管理员,那么默认以
                if (multiTenant.isSuperTenant(tenantCode)) {
                    dsKey = multiTenant.getDefaultDsName();
                } else {
                    dsKey = prefix + tenantCode;
                }
                log.debug("经过多数据源Interceptor,数据源是 => {},请求地址 => {}", dsKey, requestUri);
                DynamicDataSourceContextHolder.push(dsKey);
                return true;
            }

            /**
             * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
             */
            @Override
            public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) {
                DynamicDataSourceContextHolder.clear();
            }
        });
    }
}