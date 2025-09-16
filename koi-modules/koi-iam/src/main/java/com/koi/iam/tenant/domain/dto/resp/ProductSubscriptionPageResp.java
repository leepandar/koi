package com.koi.iam.tenant.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author lida
 */
@Data
public class ProductSubscriptionPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "产品ID")
    private Long productId;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "用户数量")
    private Integer users;

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

    @Schema(description = "开始时间")
    private Instant startTime;

    @Schema(description = "结束时间")
    private Instant endTime;

    @Schema(description = "支付状态(0=待支付;10=部分支付;20=已支付)")
    private String paymentStatus;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;
}
