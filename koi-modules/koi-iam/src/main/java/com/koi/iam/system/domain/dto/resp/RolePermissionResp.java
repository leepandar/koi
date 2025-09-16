package com.koi.iam.system.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionResp {

    @Schema(description = "菜单ID")
    private List<Long> menuIdList;

    @Schema(description = "按钮ID")
    private List<Long> buttonIdList;

}
