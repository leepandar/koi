package com.koi.iam.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("c_login_log")
@EqualsAndHashCode(callSuper = true)
@Schema(name = "LoginLog", description = "登录日志")
public class LoginLog extends Entity<Long> {

    @Schema(description = "登录IP")
    private String ip;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "租户编码")
    private String tenantCode;

    @Schema(description = "登录人账号")
    private String principal;

    @Schema(description = "平台类型")
    private String platform;

    @Schema(description = "引擎类型")
    private String engine;

    @Schema(description = "浏览器名称")
    private String browser;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "登录地点")
    private String location;

    @Schema(description = "客户端ID")
    private String clientId;

    @Schema(description = "登录类型")
    private String loginType;
}
