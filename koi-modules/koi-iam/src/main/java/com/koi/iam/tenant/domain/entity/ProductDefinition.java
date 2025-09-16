package com.koi.iam.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author lida
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "plat_product_definition")
@Schema(name = "ProductDefinition", description = "产品定义")
public class ProductDefinition extends SuperEntity<Long> {

    @Schema(description = "产品名称")
    private String name;

    @Schema(description = "产品编码")
    private String code;

    @Schema(description = "产品Logo链接")
    private String logo;

    @Schema(description = "产品详情")
    private String description;

    @Schema(description = "启用状态")
    private Boolean status;

}
