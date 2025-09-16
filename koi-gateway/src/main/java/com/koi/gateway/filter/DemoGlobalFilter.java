package com.koi.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import com.koi.gateway.utils.MonoHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * 演示模式过滤器
 *
 * @author lida
 */
@Slf4j
@Component
@Profile("demo")
public class DemoGlobalFilter implements GlobalFilter {


    private static final Map<HttpMethod, List<String>> IGNORE_URL = Map.of(HttpMethod.POST, List.of("/resources/create"));

    private boolean isReject(ServerWebExchange exchange) {
        HttpMethod method = exchange.getRequest().getMethod();
        List<String> urlList = IGNORE_URL.get(method);
        if (CollUtil.isEmpty(urlList)) {
            return false;
        }
        String path = exchange.getRequest().getURI().getPath();
        return urlList.contains(path);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpMethod method = exchange.getRequest().getMethod();
        if (method == HttpMethod.PUT || method == HttpMethod.DELETE || isReject(exchange)) {
            return MonoHelper.wrap(exchange, "演示环境,不允许操作");
        }
        return chain.filter(exchange);
    }

}
