package com.koi.iam.system.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * 角色分配用户
 *
 * @author lida
 */
@Data
@Schema(name = "RoleUserSaveReq", description = "角色分配用户")
public class RoleUserSaveReq {

    @Schema(description = "用户ID")
    @NotEmpty(message = "分配用户不能为空")
    @Size(min = 1, message = "至少勾选 {min} 条数据")
    private List<Long> userIdList;

    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

}
