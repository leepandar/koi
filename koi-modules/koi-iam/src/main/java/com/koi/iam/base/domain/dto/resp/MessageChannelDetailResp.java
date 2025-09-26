package com.koi.iam.base.domain.dto.resp;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "I18nDataPageResp", description = "消息通道返回")
public class MessageChannelDetailResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态(0=禁用;1=启用)")
    private Boolean status;

    @Schema(description = "设置")
    private JSONObject setting;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "描述信息")
    private String description;
}
