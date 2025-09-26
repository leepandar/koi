package com.koi.common.log.service;

/**
 * 方法
 *
 * @author lida
 */
public interface IFunctionService {

    /**
     * 日志记录
     *
     * @param functionName functionName
     * @param value        值
     * @return 追加结果
     */
    String apply(String functionName, Object value);

    /**
     * 在 function 之前执行
     *
     * @param functionName functionName
     * @return boolean
     */
    boolean beforeFunction(String functionName);
}
