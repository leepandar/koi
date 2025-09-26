package com.koi.iam.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_registered_client")
@Schema(name = "RegisteredClient", description = "注册客户端")
public class RegisteredClient extends SuperEntity<String> {

    @Schema(description = "客户端ID")
    private String clientId;

    @Schema(description = "客户端秘钥")
    private String clientSecret;

    @Schema(description = "客户端 ID 发放时间")
    private Instant clientIdIssuedAt;

    @Schema(description = "客户端 秘钥失效时间")
    private Instant clientSecretExpiresAt;

    @Schema(description = "客户端名称")
    private String clientName;

    @Schema(description = "授权类型")
    private String grantTypes;

    @Schema(description = "重定向地址")
    private String redirectUris;

    @Schema(description = "退出登录重定向地址")
    private String postLogoutRedirectUris;

    @Schema(description = "授权范围")
    private String scopes;

    @Schema(description = "客户端设置")
    private String clientSettings;

    @Schema(description = "令牌设置")
    private String tokenSettings;

    @Schema(description = "状态")
    private Boolean status;

}
