package com.koi.common.springboot.sensitive;

/**
 * 脱敏策略
 *
 * @author lida
 */
public interface SensitiveStrategy {

    /**
     * 脱敏策略
     *
     * @param original original
     * @return 脱敏后的值
     */
    default String serialize(String original) {
        return original;
    }

}
