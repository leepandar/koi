package com.koi.iam.base.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@Schema(name = "I18nDataPageResp", description = "消息模版详情返回")
public class MessageTemplateDetailResp {

    @Schema(description = "模板ID")
    private Long id;

    @Schema(description = "模板编码")
    private String code;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "消息标题（如邮件标题）")
    private String subject;

    @Schema(description = "消息内容模板，支持占位符")
    private String content;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;

    private Set<String> variables;
}
