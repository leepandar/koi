package com.koi.common.security.configuration.client;

import com.koi.common.security.configuration.SecurityExtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(SecurityExtProperties.class)
public class ClientResourceServerConfiguration {

    private final SecurityExtProperties properties;

}
