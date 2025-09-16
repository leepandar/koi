package com.koi.common.security.configuration;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

import static com.koi.common.security.configuration.SecurityExtProperties.PLUGIN_PREFIX;

/**
 * 扩展安全配置
 *
 * @author lida
 */
@Data
@ConfigurationProperties(prefix = PLUGIN_PREFIX)
public class SecurityExtProperties {

    public static final String PLUGIN_PREFIX = "extend.security";

    /**
     * 默认的过滤地址
     */
    private List<String> defaultIgnoreUrls = List.of("/captcha", "/sms_captcha", "/message/**",
            "/login", "/error", "/oauth2/**",
            "/favicon.ico", "/css/**", "/webjars/**",
            "/swagger-ui.html", "/doc.html", "/v3/api-docs/**");

    /**
     * 是否启用 oauth2 协议（不启用也不影响正常登录）
     */
    private boolean enabledOauth2;

    /**
     * 忽略资源
     */
    private Ignore ignore = new Ignore();

    /**
     * 服务端配置
     */
    private Server server = new Server();

    /**
     * 客户端配置
     */
    private Client client = new Client();
    private InnerService innerService = new InnerService();

    @Data
    public static class Server {


        /**
         * 用户信息KEY
         * %s = token
         */
        private String tokenInfoKey = "tokenInfo";

    }

    @Data
    public static class Client {

    }

    @Data
    public static class Ignore {

        private List<String> resourceUrls = new ArrayList<>();
    }

    @Data
    public static class InnerService {

        /**
         * 白名单
         */
        private List<String> whiteLists = Lists.newArrayList();

    }

}
