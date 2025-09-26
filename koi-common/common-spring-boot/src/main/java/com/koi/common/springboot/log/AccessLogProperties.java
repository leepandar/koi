package com.koi.common.springboot.log;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置信息
 *
 * @author lida
 */
@Data
@ConfigurationProperties(AccessLogProperties.PREFIX)
public class AccessLogProperties {

    public static final String PREFIX = "extend.boot.log.access";

    private boolean enabled = true;

    /**
     * 令牌Key
     */
    private String token = "Authorization";

    /**
     * traceId
     */
    private String trace = "traceId";
    /**
     * 日志存储策略
     * 如果不是 authority 服务 请配置 feign 的策略
     */
    private StoreStrategy strategy = StoreStrategy.local;

    /**
     * 存储策略
     */
    public enum StoreStrategy {

        /**
         * feign = feign 请求
         * local = 本地
         */
        feign, local

    }

}
