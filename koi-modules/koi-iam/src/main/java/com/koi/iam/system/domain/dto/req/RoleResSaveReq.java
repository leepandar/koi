package com.koi.iam.system.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 角色的资源
 *
 * @author lida
 */
@Data
@Schema(name = "RoleResSaveReq", description = "角色的资源DTO")
public class RoleResSaveReq implements Serializable {

    @NotEmpty(message = "资源不能为空")
    @Size(min = 1, message = "至少勾选 {min} 条数据")
    private Set<Long> resIdList;

    @Schema(description = "角色id")
    @NotNull(message = "角色不能为空")
    private Long roleId;

}
