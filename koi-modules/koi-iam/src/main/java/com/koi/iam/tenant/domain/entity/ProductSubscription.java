package com.koi.iam.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName("plat_product_subscription")
@Schema(name = "ProductSubscription", description = "产品订阅信息")
public class ProductSubscription extends SuperEntity<Long> {

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

    @Schema(description = "描述信息")
    private String description;

}