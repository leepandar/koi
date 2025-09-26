package com.koi.common.feign.token;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import com.google.common.cache.Cache;
import com.koi.common.core.exception.CheckedException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

/**
 * 获取自动刷新Token
 *
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class AutoRefreshTokenInterceptor implements RequestInterceptor {

    private final AutoRefreshTokenProperties properties;
    private final Cache<String, String> tokenCache;

    @SneakyThrows
    @Override
    public void apply(RequestTemplate template) {
        final Map<String, Collection<String>> headers = template.headers();
        if (!headers.containsKey(properties.getIncludeTokenHeader())) {
            return;
        }
        final String tokenHeader = properties.getServerTokenHeader();
        if (template.headers().containsKey(tokenHeader)) {
            log.debug("当前上下文中已包含token,跳过自动获取流程...");
            return;
        }
        template.header(tokenHeader, tokenCache.get(tokenHeader, this::loadCache));
    }

    @SneakyThrows
    private String loadCache() {
        final AutoRefreshTokenProperties.Login auth = properties.getLogin();
        // 设置访问参数
        JSONObject params = new JSONObject();
        params.put("clientId", auth.getClientId());
        params.put("clientSecret", auth.getClientSecret());
        params.put("username", auth.getUsername());
        params.put("password", auth.getPassword());
        params.put("tenantCode", auth.getTenantCode());
        params.put("loginType", auth.getLoginType());
        String url = properties.getUri();
        if (properties.isLoadBalance()) {
            // 没找到好方案,只能用这种笨办法了
            final DiscoveryClient client = SpringUtil.getBean(DiscoveryClient.class);
            final URI uri = new URI(properties.getUri());
            final String serviceId = uri.getHost();
            final ServiceInstance instance = client.getInstances(serviceId).get(0);
            final String hostAndPort = instance.getHost() + ":" + instance.getPort();
            url = StrUtil.replace(url, serviceId, hostAndPort);
        }
        final String response = HttpUtil.createPost(url).body(params.toJSONString())
                .basicAuth(auth.getClientId(), auth.getClientSecret()).execute().body();
        log.info("自动获取Token响应结果 - {}", response);
        final String accessToken = (String) JSONPath.eval(response, "data.accessToken");
        if (StrUtil.isBlank(accessToken)) {
            throw CheckedException.badRequest("未获取到有效的 Token 数据");
        }
        return "Bearer " + accessToken;
    }
}
