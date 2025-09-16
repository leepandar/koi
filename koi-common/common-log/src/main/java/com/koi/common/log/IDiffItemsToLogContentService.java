package com.koi.common.log;

/**
 * @author lida
 */
public interface IDiffItemsToLogContentService {

    /**
     * 日志内容
     *
     * @param source 来源
     * @param target 目标
     * @return 日志内容
     */
    String toLogContent(final Object source, final Object target);
}
