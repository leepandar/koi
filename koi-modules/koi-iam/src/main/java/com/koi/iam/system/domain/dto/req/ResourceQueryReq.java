package com.koi.iam.system.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源 查询DTO
 *
 * @author lida
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "ResourceSaveReq", description = "资源查询DTO")
public class ResourceQueryReq {

    @Schema(description = "资源类型")
    private Integer type;

    @Schema(description = "父资源ID,用于查询按钮")
    private Long parentId;

    // @Schema(description = "角色ID,查询指定角色可见的")
    // private Long roleId;

    @Schema(description = "true=启用;false=禁用")
    private Boolean status;

    @Schema(description = "指定用户ID，前端不传则自动获取")
    private Long userId;

}
