package com.koi.common.feign;

import com.koi.common.feign.mock.MockProperties;
import com.koi.common.feign.token.AutoRefreshTokenProperties;
import feign.Logger;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lida
 */
@Data
@ConfigurationProperties(prefix = FeignPluginProperties.PLUGIN_PREFIX)
public class FeignPluginProperties {

    public static final String PLUGIN_PREFIX = "extend.feign.plugin";
    private boolean enabled = true;

    /**
     * Feign 日志级别（FULL 意味着会输出详细日志,建议值在非生产环境使用）
     */
    private Logger.Level level = Logger.Level.FULL;
    private MockProperties mock = new MockProperties();
    private AutoRefreshTokenProperties token = new AutoRefreshTokenProperties();

}