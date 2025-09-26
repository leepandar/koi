package com.koi.iam.base.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "MessageTemplatePageReq", description = "消息模版分页DTO")
public class MessageTemplatePageReq extends PageRequest {

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "消息标题（如邮件标题）")
    private String subject;

}
