package com.koi.iam.base.service.strategy;

import com.koi.iam.base.domain.entity.MessageChannel;
import com.koi.iam.base.domain.entity.MessageNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息推送策略
 */
public interface MessageNotifyStrategy {

    Logger log = LoggerFactory.getLogger(MessageNotifyStrategy.class);

    String channelType();

    /**
     * 消息处理
     *
     * @param channel 通知渠道
     * @param notify  通知内容
     */

    void handler(MessageChannel channel, MessageNotify notify);

}
