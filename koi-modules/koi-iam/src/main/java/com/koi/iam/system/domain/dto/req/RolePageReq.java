package com.koi.iam.system.domain.dto.req;

import com.koi.common.core.security.DataScopeType;
import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色分页DTO
 *
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "RolePageReq", description = "角色分页DTO")
public class RolePageReq extends PageRequest {

    @Schema(description = "名称")
    private String name;

    @Parameter(description = "是否禁用", in = ParameterIn.QUERY)
    @Schema(description = "是否禁用")
    private Boolean status;

    @Parameter(description = "数据类型范围", in = ParameterIn.QUERY)
    @Schema(description = "数据类型范围")
    private DataScopeType scopeType;

}
