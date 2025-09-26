package com.koi.iam.base.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.base.domain.dto.req.MessageChannelSaveReq;
import com.koi.iam.base.domain.dto.resp.MessageChannelDetailResp;
import com.koi.iam.base.domain.entity.MessageChannel;

public interface MessageChannelService extends SuperService<MessageChannel> {

    /**
     * 渠道设置
     *
     * @param req req
     */
    void setting(MessageChannelSaveReq req);

    /**
     * 明细
     *
     * @param type 类型
     * @return 查询结果
     */
    MessageChannelDetailResp detail(String type);

}
