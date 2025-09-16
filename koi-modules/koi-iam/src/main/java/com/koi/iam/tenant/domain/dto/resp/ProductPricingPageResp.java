package com.koi.iam.tenant.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author lida
 */
@Data
@Schema(name = "ProductPricingPageResp")
public class ProductPricingPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "定价名称")
    private String name;

    @Schema(description = "产品ID")
    private String productId;

    @Schema(description = "用户数量")
    private Integer users;

    @Schema(description = "月数")
    private Integer months;

    @Schema(description = "每用户单价")
    private Integer pricePerUser;

    @Schema(description = "超额单价")
    private String overPrice;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;

}
