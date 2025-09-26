package com.koi.iam.system.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 角色的资源
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RoleResResp", description = "角色的资源返回")
public class RoleResResp implements Serializable {

    @Schema(description = "菜单ID")
    private List<Long> menuIdList;

    @Schema(description = "资源ID")
    private List<Long> resourceIdList;

}
