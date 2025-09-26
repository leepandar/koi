package com.koi.iam.base.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.base.domain.dto.req.MessageNotifyPublishReq;
import com.koi.iam.base.domain.entity.MessageNotify;

public interface MessageNotifyService extends SuperService<MessageNotify> {

    /**
     * 消息通知
     *
     * @param req req
     */
    void publish(MessageNotifyPublishReq req);

}
