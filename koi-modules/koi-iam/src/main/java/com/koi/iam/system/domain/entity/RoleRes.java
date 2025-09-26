package com.koi.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色的资源
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_res")
@Schema(name = "RoleRes", description = "角色的资源")
public class RoleRes {

    @Schema(description = "资源ID")
    private Long resId;

    @Schema(description = "角色ID")
    private Long roleId;

}
