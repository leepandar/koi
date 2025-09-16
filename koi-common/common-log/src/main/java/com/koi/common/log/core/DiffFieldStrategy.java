package com.koi.common.log.core;

/**
 * 字段策略枚举类
 *
 * @author lida
 */
public enum DiffFieldStrategy {

    /**
     * 任何时候都加入
     */
    ALWAYS,
    /**
     * 非NULL判断
     */
    NOT_NULL,

}
