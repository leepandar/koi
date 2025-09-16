package com.koi.iam.tenant.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

/**
 * @author lida
 */
@Data
@Schema(name = "ProductDefPermissionReq")
public class ProductDefPermissionReq {

    @NotNull(message = "产品定义不能为空")
    @Schema(description = "产品定义ID")
    private Long productId;

    @NotEmpty(message = "权限资源不能为空")
    @Schema(description = "权限资源ID")
    private Set<Long> resIdList;

}
