package com.koi.common.db.dynamic.core.local;

import com.koi.common.db.dynamic.DynamicDataSourceHandler;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

/**
 * 本地监听
 *
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class DynamicDatasourceLocalListener implements ApplicationListener<DynamicDatasourceEvent> {

    private final DynamicDataSourceHandler dynamicDataSourceHandler;

    @Override
    @EventListener
    public void onApplicationEvent(@Nonnull DynamicDatasourceEvent event) {
        dynamicDataSourceHandler.handler(event.getAction(), event.getDatasource());
    }
}