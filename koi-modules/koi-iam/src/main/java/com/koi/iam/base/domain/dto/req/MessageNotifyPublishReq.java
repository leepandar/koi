package com.koi.iam.base.domain.dto.req;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "MessageNotifyPublishReq", description = "消息通知发布DTO")
public class MessageNotifyPublishReq {

    @Schema(description = "模板ID")
    private Long templateId;

    @Schema(description = "类型")
    private Integer subscriberType;

    @NotEmpty(message = "订阅者不能为空")
    @Schema(description = "订阅人")
    private List<Long> subscriberIdList;

    @Schema(description = "变量")
    private JSONObject variables;
}
