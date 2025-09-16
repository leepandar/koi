package com.koi.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author lida
 */
@Data
public class ProductSubscriptionSaveReq {

    @NotNull(message = "产品不能为空")
    @Schema(description = "产品ID")
    private Long productId;

    @NotNull(message = "租户不能为空")
    @Schema(description = "租户ID")
    private Long tenantId;

    @NotNull(message = "用户数量不能为空")
    @Schema(description = "用户数量")
    private Integer users;

    @NotNull(message = "订阅时长不能为空")
    @Schema(description = "月数")
    private Integer months;

    @Schema(description = "用户单价")
    private BigDecimal licensePrice;

    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "结算单价")
    private BigDecimal statementPrice;

    @Schema(description = "结算金额")
    private BigDecimal statementAmount;

    @NotNull(message = "订阅起始日期不能为空")
    @Schema(description = "开始时间")
    private Instant startTime;

    @NotNull(message = "订阅结束日期不能为空")
    @Schema(description = "结束时间")
    private Instant endTime;

    @Schema(description = "支付状态(0=待支付;10=部分支付;20=已支付)")
    private String paymentStatus;

    @Schema(description = "描述")
    private String description;

}
