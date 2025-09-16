package com.koi.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author lida
 */
@Data
@Schema(name = "ProductDefinitionSaveReq")
public class ProductDefinitionSaveReq {

    @NotBlank(message = "产品名称不能为空")
//    @Length(min = 1, max = 100, message = "产品名称长度 {min} - {max}")
    @Schema(description = "产品名称")
    private String name;

    @Schema(description = "产品Logo链接")
    private String logo;

    //    @Length(max = 10086, message = "产品描述超过 {max} 描述,请简化描述")
    @Schema(description = "产品描述")
    private String description;

}
