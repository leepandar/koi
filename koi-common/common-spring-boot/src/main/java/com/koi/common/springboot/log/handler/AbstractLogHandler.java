package com.koi.common.springboot.log.handler;

import com.koi.common.springboot.log.AccessLogInfo;
import com.koi.common.springboot.log.AccessLogInfo;

/**
 * 日志处理基类
 *
 * @author lida
 */
public abstract class AbstractLogHandler {

    /**
     * 执行日志打印
     *
     * @param info info
     * @return 日志结果
     */
    public abstract AccessLogInfo handler(AccessLogInfo info);

}
