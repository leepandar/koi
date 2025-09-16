package com.koi.common.db.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 多租户策略
 *
 * @author lida
 */
@Getter
@AllArgsConstructor
public enum MultiTenantStrategy {

    /**
     * 本地服务
     */
    LOCAL("本地服务"),
    /**
     * Feign远程调用
     */
    FEIGN("Feign远程调用"),
    ;
    /**
     * 描述信息
     */
    private final String description;
}
