package com.koi.common.log.service.impl;

import com.koi.common.log.service.IDiffLogPerformanceMonitor;
import com.koi.common.log.service.IDiffLogPerformanceMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author lida
 */
@Slf4j
public class DefaultDiffLogPerformanceMonitor implements IDiffLogPerformanceMonitor {

    @Override
    public void print(StopWatch stopWatch) {
        log.debug("diff log performance {}", stopWatch.prettyPrint());
    }
}
