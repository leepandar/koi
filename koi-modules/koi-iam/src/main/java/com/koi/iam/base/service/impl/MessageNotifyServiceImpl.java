package com.koi.iam.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.common.core.MvelHelper;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.iam.base.mapper.MessageNotifyMapper;
import com.koi.iam.base.service.strategy.MessageNotifyEvent;
import com.koi.iam.system.domain.entity.User;
import com.koi.iam.system.mapper.MessageTemplateMapper;
import com.koi.iam.system.mapper.UserMapper;
import com.koi.iam.base.domain.dto.req.MessageNotifyPublishReq;
import com.koi.iam.base.domain.entity.MessageNotify;
import com.koi.iam.base.domain.entity.MessageTemplate;
import com.koi.iam.base.service.MessageNotifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageNotifyServiceImpl extends SuperServiceImpl<MessageNotifyMapper, MessageNotify> implements MessageNotifyService {

    private final AuthenticationContext context;
    private final UserMapper userMapper;
    private final MessageTemplateMapper messageTemplateMapper;
    private final MessageNotifyMapper messageNotifyMapper;

    /**
     * 消息通知
     *
     * @param req req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void publish(MessageNotifyPublishReq req) {
        MessageTemplate template = Optional.ofNullable(this.messageTemplateMapper.selectById(req.getTemplateId()))
                .orElseThrow(() -> CheckedException.notFound("消息模板不存在"));
        JSONObject variables = Optional.ofNullable(req.getVariables()).orElse(new JSONObject());
        List<User> userList = this.userMapper.selectByIds(req.getSubscriberIdList());
        if (CollUtil.isEmpty(userList)) {
            log.warn("订阅信息不存在");
            return;
        }
        log.warn("========== [ 如果服务器资源充足,消息推送较大的情况,请将消息丢到 Redis 或者 MQ 中进行异步推送] ==========");
        String content = MvelHelper.format(template.getContent(), variables);
        List<MessageNotify> list = userList.stream()
                .map(user -> {
                    List<String> typeList = StrUtil.split(template.getType(), ",");
                    return typeList.stream().map(type -> MessageNotify.builder().userId(user.getId())
                            .templateId(template.getId()).variables(variables.toJSONString())
                            .title(template.getSubject()).type(type)
                            .content(content).nickname(user.getNickName())
                            .tenantId(context.tenantId())
                            .subscribe(user.getEmail())
                            .deleted(false).createdBy(context.userId())
                            .createdName(context.nickName()).createdTime(Instant.now())
                            .build()).toList();
                }).flatMap(Collection::stream).collect(Collectors.toList());
        CollUtil.split(list, 600).forEach(messageNotifyMapper::insertBatchSomeColumn);
        // 鉴于大部分系统对性能要求没那么极致,采用 spring event 一样可以解耦提高性能
        // 如果消息负载压力过高可以采用 MQ 异步投递解耦
        SpringUtil.publishEvent(new MessageNotifyEvent(template, list));
    }

    // private final WebSocketManager webSocketManager;

    // void publish(SiteNotify messagePublish, List<Long> userIdList) {
    // for (Long userId : userIdList) {
    // MessageNotify message = new MessageNotify();
    // message.setTitle(messagePublish.getTitle());
    // message.setMark(false);
    // message.setContent(messagePublish.getContent());
    // message.setDescription(messagePublish.getDescription());
    // message.setLevel(messagePublish.getLevel());
    // message.setReceiveId(userId);
    // message.setCreatedTime(Instant.now());
    // this.messageNotifyMapper.insert(message);
    // this.webSocketManager.sendMessage(String.valueOf(userId), JSON.toJSONString(message));
    // }
    // }

}
