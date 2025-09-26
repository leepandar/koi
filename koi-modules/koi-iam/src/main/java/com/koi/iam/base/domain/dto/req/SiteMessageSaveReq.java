package com.koi.iam.base.domain.dto.req;

import com.koi.iam.system.domain.enums.ReceiverType;
import com.koi.iam.system.domain.enums.ReceiverType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "SiteMessageSaveReq", description = "消息保存DTO")
public class SiteMessageSaveReq {

    @NotBlank(message = "标题不能为空")
    @Schema(description = "标题")
    private String title;

    @NotBlank(message = "类型不能为空")
    @Schema(description = "类型")
    private String level;

    @NotBlank(message = "消息内容不能为空")
    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "描述")
    private String description;

    @NotNull(message = "接收类型不能为空")
    @Schema(description = "接收类型")
    private ReceiverType type;

    @NotEmpty(message = "接收者不能为空")
    @Schema(description = "接收人")
    private List<Long> receiver;

}
