package com.koi.common.websocket;

import java.util.List;

/**
 * @author lida
 */
@FunctionalInterface
public interface TodoAtRemoved {

    /**
     * 在删除的时候额外要干什么
     *
     * @param webSockets webSockets
     */
    void todoWith(List<WebSocket> webSockets);
}
