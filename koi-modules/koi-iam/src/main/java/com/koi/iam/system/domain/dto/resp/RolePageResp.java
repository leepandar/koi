package com.koi.iam.system.domain.dto.resp;

import com.koi.common.core.security.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 角色分页返回
 *
 * @author lida
 */
@Data
@Schema(name = "RolePageResp", description = "角色分页返回")
public class RolePageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "超期管理员")
    private Boolean superRole;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "内置角色")
    private Boolean readonly;

    @Schema(description = "角色状态")
    private Boolean status;

    @Schema(description = "权限范围")
    private DataScopeType scopeType;

    @Schema(description = "创建时间")
    private Instant createdTime;

}
