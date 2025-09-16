package com.koi.common.springboot.log.event;

import com.koi.common.springboot.log.AccessLogInfo;
import com.koi.common.springboot.log.AccessLogInfo;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author lida
 */
public class AccessLogEvent extends ApplicationEvent {

    public AccessLogEvent(AccessLogInfo source) {
        super(source);
    }
}
