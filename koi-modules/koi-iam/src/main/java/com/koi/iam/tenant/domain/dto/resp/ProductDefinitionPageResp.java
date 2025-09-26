package com.koi.iam.tenant.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

@Data
public class ProductDefinitionPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "产品编码")
    private String code;

    @Schema(description = "产品名称")
    private String name;

    @Schema(description = "产品Logo链接")
    private String logo;

    @Schema(description = "产品详情")
    private String description;

    @Schema(description = "启用状态")
    private Boolean status;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;

}
