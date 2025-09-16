package com.koi.iam.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author lida
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "plat_product_def_res")
@Schema(name = "ProductDefinitionRes", description = "产品授权")
public class ProductDefinitionRes {

    @Schema(description = "产品ID")
    private Long productId;

    @Schema(description = "权限资源ID")
    private Long resId;

}
