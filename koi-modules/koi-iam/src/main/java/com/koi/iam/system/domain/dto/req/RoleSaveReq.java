package com.koi.iam.system.domain.dto.req;

import com.koi.common.core.security.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 保存角色对象
 *
 * @author lida
 */
@Data
@Schema(name = "RoleSaveReq")
public class RoleSaveReq {

    @Schema(description = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
//    @Length(max = 30, message = "角色名称长度不能超过30")
    private String name;

    @Schema(description = "角色编码")
//    @Length(max = 20, message = "角色编码长度不能超过20")
    private String code;

    @Schema(description = "描述")
//    @Length(max = 100, message = "描述长度不能超过100")
    private String description;

    @NotNull(message = "角色状态不能为空")
    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "内置角色")
    private Boolean readonly;

    @Schema(description = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    private DataScopeType scopeType;

    @Schema(description = "关联的组织id")
    private List<Long> orgList;
}
