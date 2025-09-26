package com.koi.common.log.service.impl;

import com.koi.common.log.service.IDiffLogPerformanceMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * 默认的审计日志性能监控实现
 *
 * @author lida
 */
@Slf4j
public class DefaultDiffLogPerformanceMonitor implements IDiffLogPerformanceMonitor {

    @Override
    public void print(StopWatch stopWatch) {
        log.debug("diff log performance {}", stopWatch.prettyPrint());
    }
}
