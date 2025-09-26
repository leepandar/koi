package com.koi.iam.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.iam.base.mapper.MessageChannelMapper;
import com.koi.iam.base.domain.dto.req.MessageChannelSaveReq;
import com.koi.iam.base.domain.dto.resp.MessageChannelDetailResp;
import com.koi.iam.base.domain.entity.MessageChannel;
import com.koi.iam.base.service.MessageChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageChannelServiceImpl extends SuperServiceImpl<MessageChannelMapper, MessageChannel> implements MessageChannelService {

    /**
     * 渠道设置
     *
     * @param req req
     */
    @Override
    public void setting(MessageChannelSaveReq req) {
        MessageChannel bean = BeanUtilPlus.toBean(req, MessageChannel.class);
        if (req.getId() == null) {
            this.baseMapper.insert(bean);
        } else {
            this.baseMapper.updateById(bean);
        }
    }

    /**
     * 明细
     *
     * @param type 类型
     * @return
     */
    @Override
    public MessageChannelDetailResp detail(String type) {
        MessageChannel channel = this.baseMapper.selectOne(MessageChannel::getType, type);
        MessageChannelDetailResp bean = BeanUtilPlus.toBeanIgnoreError(channel, MessageChannelDetailResp.class);
        if (StrUtil.isNotBlank(channel.getSetting())) {
            bean.setSetting(JSONObject.parseObject(channel.getSetting()));
        }
        return bean;
    }
}
