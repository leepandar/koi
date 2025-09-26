package com.koi.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "ProductPricingSaveReq")
public class ProductPricingSaveReq {

    @Schema(description = "产品ID")
    @NotNull(message = "产品不能为空")
    private Long productId;

    @Schema(description = "定价名称")
    @NotNull(message = "定价名称不能为空")
    private String name;

    @Schema(description = "用户数量")
    @NotNull(message = "用户数量不能为空")
    private Integer users;

    @Schema(description = "月数")
    @NotNull(message = "采购购买不能为空")
    private Integer months;

    @Schema(description = "每用户单价")
    @NotNull(message = "每用户单价不能为空")
    private Integer pricePerUser;

    @Schema(description = "超额单价")
    private String overPrice;

    @Schema(description = "超额单价")
    @NotNull(message = "超额单价不能为空")
    private Integer price;

    @Schema(description = "描述")
    private String description;

}
