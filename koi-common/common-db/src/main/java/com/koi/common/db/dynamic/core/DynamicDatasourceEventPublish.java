package com.koi.common.db.dynamic.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lida
 */
public interface DynamicDatasourceEventPublish {

    Logger log = LoggerFactory.getLogger(DynamicDatasourceEventPublish.class);

    String DEFAULT_EVENT_TOPIC = "dynamic-db-topic";

    /**
     * 发布数据源
     *
     * @param message 数据源
     */
    void publish(DynamicDatasourceEvent message);

}
