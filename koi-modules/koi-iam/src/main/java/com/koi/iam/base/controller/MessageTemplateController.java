package com.koi.iam.base.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.iam.base.service.MessageTemplateService;
import com.koi.iam.base.domain.dto.req.MessageTemplatePageReq;
import com.koi.iam.base.domain.dto.req.MessageTemplateSaveReq;
import com.koi.iam.base.domain.dto.resp.MessageTemplateDetailResp;
import com.koi.iam.base.domain.dto.resp.MessageTemplatePageResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录日志
 *
 * @author lida
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/message-template")
@Tag(name = "消息模板", description = "消息模板")
public class MessageTemplateController {

    private final MessageTemplateService messageTemplateService;

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @SaCheckPermission(value = {"message:template:page"})
    public IPage<MessageTemplatePageResp> pageList(MessageTemplatePageReq req) {
        return messageTemplateService.pageList(req);
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "模板详情")
    public MessageTemplateDetailResp detail(@PathVariable Long id) {
        return messageTemplateService.detail(id);
    }

    @PostMapping("/create")
    @AccessLog(module = "消息模板", description = "添加模板")
    @Operation(summary = "添加模板")
    @SaCheckPermission(value = {"message:template:add"})
    public void create(@Validated @RequestBody MessageTemplateSaveReq req) {
        messageTemplateService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "消息模板", description = "编辑模板")
    @Operation(summary = "编辑模板")
    @SaCheckPermission(value = {"message:template:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody MessageTemplateSaveReq req) {
        messageTemplateService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "消息模板", description = "删除模板")
    @Operation(summary = "删除模板")
    @SaCheckPermission(value = {"message:template:remove"})
    public void remove(@PathVariable Long id) {
        messageTemplateService.removeById(id);
    }

}
