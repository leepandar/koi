package com.koi.common.springboot.remote.configuration;

import com.koi.common.core.remote.LoadService;
import com.koi.common.i18n.I18nMessageProvider;
import com.koi.common.springboot.remote.dict.DictLoadService;
import com.koi.common.springboot.remote.RemoteService;
import com.koi.common.springboot.remote.properties.RemoteProperties;
import com.koi.common.springboot.remote.RemoteService;
import com.koi.common.springboot.remote.dict.DictLoadService;
import com.koi.common.springboot.remote.properties.RemoteProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * 关联字段数据注入工具 自动配置类
 *
 * @author lida
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(RemoteProperties.class)
@ConditionalOnProperty(prefix = RemoteProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class RemoteAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RemoteResultAspect getRemoteResultAspect(RemoteService remoteService) {
        return new RemoteResultAspect(remoteService);
    }

    @Bean
    @ConditionalOnMissingBean
    public DictLoadService dictLoadService(@Qualifier("remoteAloneRedisTemplate") RedisTemplate<String, Object> redisTemplate, I18nMessageProvider i18nMessageProvider) {
        return new DictLoadService(redisTemplate, i18nMessageProvider);
    }

    /**
     * 回显服务
     *
     * @param strategyMap 回显查询实例
     * @return RemoteService
     */
    @Bean
    @ConditionalOnMissingBean
    public RemoteService getRemoteService(RemoteProperties remoteProperties, Map<String, LoadService> strategyMap) {
        return new RemoteService(remoteProperties, strategyMap);
    }
}
