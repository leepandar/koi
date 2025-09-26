package com.koi.iam.system.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色权限返回
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RolePermissionResp", description = "角色权限返回")
public class RolePermissionResp {

    @Schema(description = "菜单ID")
    private List<Long> menuIdList;

    @Schema(description = "按钮ID")
    private List<Long> buttonIdList;

}
