package com.koi.iam.base.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.koi.common.db.dynamic.DynamicDataSourceHandler;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.db.properties.DatabaseProperties;
import com.koi.common.db.properties.MultiTenantType;
import com.koi.common.websocket.BaseWebSocketEndpoint;
import com.koi.iam.base.service.MessageNotifyService;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.tenant.mapper.TenantMapper;
import com.koi.iam.base.domain.entity.MessageNotify;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * 聊天室
 *
 * @author lida
 * @since 2020/11/11
 */
@Slf4j
@Component
@ServerEndpoint(value = "/message/{tenantCode}/{identifier}")
public class WebSocketMessageEndpoint extends BaseWebSocketEndpoint {

    @OnOpen
    public void openSession(@PathParam("tenantCode") String tenantCode, @PathParam(IDENTIFIER) String userId, Session session) {
        connect(userId, session);
        List<MessageNotify> messages = null;
        final DatabaseProperties properties = SpringUtil.getBean(DatabaseProperties.class);
        final MessageNotifyService service = SpringUtil.getBean(MessageNotifyService.class);
        if (properties.getMultiTenant().getType() == MultiTenantType.DATASOURCE) {
            log.info("WebSocket 租户编码 - {}", tenantCode);
            final DataSource dataSource = SpringUtil.getBean(DataSource.class);
            DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
            log.info("所有的数据源信息 - {}", ds.getDataSources());
            DynamicDataSourceContextHolder.poll();
            final TenantMapper tenantMapper = SpringUtil.getBean(TenantMapper.class);
            final Long count = tenantMapper.selectCount(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode));
            if (count != null && count != 0) {
                final DynamicDataSourceHandler dataSourceProcess = SpringUtil.getBean(DynamicDataSourceHandler.class);
                final String dsKey = dataSourceProcess.buildDb(tenantCode);
                log.debug("设置当前线程数据源 - {}", dsKey);
                DynamicDataSourceContextHolder.push(dsKey);
                // messages = service.list(Wraps.<MessageNotify>lbQ().eq(MessageNotify::getMark, false)
                // .eq(MessageNotify::getReceiveId, userId).orderByAsc(MessageNotify::getId));
                DynamicDataSourceContextHolder.poll();
                log.debug("清空当前线程数据源...");
            }
        } else {
            // messages = service.list(Wraps.<MessageNotify>lbQ().eq(MessageNotify::getMark, false)
            // .eq(MessageNotify::getReceiveId, userId).orderByAsc(MessageNotify::getId));
        }
        if (CollectionUtil.isEmpty(messages)) {
            return;
        }
        messages.forEach(message -> senderMessage(userId, JSON.toJSONString(message)));
    }

    @OnMessage
    public void onMessage(@PathParam(IDENTIFIER) String userId, Session session, String message) {
        log.info("接收到的消息" + message);
    }

    @OnClose
    public void onClose(@PathParam(IDENTIFIER) String userId, Session session) {
        disconnect(userId);
    }

    @OnError
    public void onError(@PathParam(IDENTIFIER) String userId, Session session, Throwable throwable) {
        log.info("发生异常：, identifier {} ", userId);
        log.error(throwable.getMessage(), throwable);
        disconnect(userId);
    }

}
