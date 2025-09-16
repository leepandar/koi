package com.koi.common.feign;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.koi.common.feign.decoder.FeignResponseDecoder;
import com.koi.common.feign.mock.FeignPluginInterceptor;
import com.koi.common.feign.mock.MockLoadBalancerFeignClient;
import com.koi.common.feign.mock.MockProperties;
import com.koi.common.feign.token.AutoRefreshTokenInterceptor;
import com.koi.common.feign.token.AutoRefreshTokenProperties;
import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.loadbalancer.LoadBalancerFeignRequestTransformer;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lida
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = {FeignPluginProperties.class, AutoRefreshTokenProperties.class, MockProperties.class})
public class FeignPluginConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel(FeignPluginProperties properties) {
        log.info("=============================== Feign Full Logger =============================== ");
        return properties.getLevel() != null ? properties.getLevel() : Logger.Level.BASIC;
    }

    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters,
                                ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new OptionalDecoder((new ResponseEntityDecoder(new FeignResponseDecoder(new SpringDecoder(messageConverters, customizers)))));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (s, response) -> {
            log.warn("response status is:{}", response.status());
            return new ErrorDecoder.Default().decode(s, response);
        };
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = MockProperties.MOCK_PREFIX, name = "enabled", havingValue = "true")
    public Client feignClient(LoadBalancerClient loadBalancerClient, LoadBalancerClientFactory loadBalancerClientFactory,
                              List<LoadBalancerFeignRequestTransformer> transformers,
                              MockProperties mockProperties) {
        return new MockLoadBalancerFeignClient(new Client.Default(null, null),
                loadBalancerClient, loadBalancerClientFactory, transformers, mockProperties);
    }

    @Bean
    @Order(-999999)
    public FeignPluginInterceptor feignPluginInterceptor(FeignPluginProperties properties) {
        return new FeignPluginInterceptor(properties);
    }

    @Bean
    @ConditionalOnProperty(prefix = AutoRefreshTokenProperties.TOKEN_PREFIX, name = "enabled", havingValue = "true")
    public AutoRefreshTokenInterceptor feignTokenInterceptor(AutoRefreshTokenProperties properties) {
        final AutoRefreshTokenProperties.Cache cache = properties.getCache();
        Cache<String, String> tokenCache = CacheBuilder.newBuilder().initialCapacity(cache.getInitialCapacity())
                .maximumSize(cache.getMaximumSize()).expireAfterWrite(cache.getExpire(), TimeUnit.SECONDS).build();
        return new AutoRefreshTokenInterceptor(properties, tokenCache);
    }
}
