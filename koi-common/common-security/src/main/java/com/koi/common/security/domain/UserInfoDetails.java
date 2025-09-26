package com.koi.common.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koi.common.core.security.DataPermission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 用户信息
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "租户编码")
    private String tenantCode;

    @Schema(description = "租户名称")
    private String tenantName;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "用户名")
    private String username;

    @JsonIgnore
    @Schema(hidden = true)
    private String password;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "启用状态")
    private Boolean enabled;

    @Schema(description = "描述信息")
    private String description;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "机构ID")
    private Long orgId;

    @Schema(description = "登录日志")
    private Map<String, Object> loginLog;

    @Schema(description = "功能权限（资源码）")
    @Builder.Default
    private Collection<String> funcPermissions = new ArrayList<>();

    @Builder.Default
    @Schema(description = "功能权限（角色编码）")
    private Collection<String> roles = new ArrayList<>();

    @Schema(description = "数据权限(可视范围)")
    private DataPermission dataPermission;

}
