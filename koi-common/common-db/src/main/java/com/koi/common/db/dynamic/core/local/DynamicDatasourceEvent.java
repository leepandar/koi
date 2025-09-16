package com.koi.common.db.dynamic.core.local;

import com.koi.common.db.dynamic.core.EventAction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author lida
 */
@Getter
@Setter
public class DynamicDatasourceEvent extends ApplicationEvent {

    private EventAction action;
    private com.koi.common.db.dynamic.core.DynamicDatasourceEvent datasource;

    public DynamicDatasourceEvent(EventAction action, com.koi.common.db.dynamic.core.DynamicDatasourceEvent datasource) {
        super(datasource);
        this.datasource = datasource;
        this.action = action;
    }
}
