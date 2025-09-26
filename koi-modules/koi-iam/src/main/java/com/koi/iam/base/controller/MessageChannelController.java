package com.koi.iam.base.controller;

import com.koi.common.core.annotation.log.AccessLog;
import com.koi.iam.base.service.MessageChannelService;
import com.koi.iam.base.domain.dto.req.MessageChannelSaveReq;
import com.koi.iam.base.domain.dto.resp.MessageChannelDetailResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@Tag(name = "消息通道")
@RequiredArgsConstructor
@RequestMapping("/message-channel")
public class MessageChannelController {

    private final MessageChannelService messageChannelService;

    @PostMapping("/setting")
    @AccessLog(module = "消息通道", description = "渠道设置")
    @Operation(summary = "渠道设置")
    public void setting(@Validated @RequestBody MessageChannelSaveReq req) {
        messageChannelService.setting(req);
    }

    @GetMapping("/detail")
    @Operation(summary = "渠道详情")
    public MessageChannelDetailResp detail(String type) {
        return messageChannelService.detail(type);
    }

}
