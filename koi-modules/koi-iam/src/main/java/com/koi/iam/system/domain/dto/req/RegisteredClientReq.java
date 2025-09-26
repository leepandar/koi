package com.koi.iam.system.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

/**
 * 客户端注册DTO
 *
 * @author lida
 */
@Data
@Schema(name = "RegisteredClientRefReq", description = "客户端注册DTO")
public class RegisteredClientReq {

    @Schema(description = "客户端名称")
    private String clientName;

    @Schema(description = "客户端ID")
    private String clientId;

    @Schema(description = "客户端ID生效时间")
    private Instant clientIdIssuedAt;

    @Schema(description = "客户端秘钥")
    private String clientSecret;

    @Schema(description = "秘钥失效时间")
    private Instant clientSecretExpiresAt;

    @Schema(description = "授权类型")
    private Set<String> grantTypes;

    @Schema(description = "重定向地址")
    private String redirectUris;

    @Schema(description = "授权范围")
    private Set<String> scopes;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "令牌配置")
    private TokenSettingsReq tokenSettings;

    @Schema(description = "客户端配置(暂时不做设计,主要没啥用)", hidden = true)
    private ClientSettingsReq clientSettings;

    @Data
    public static class ClientSettingsReq {

    }

    @Data
    public static class TokenSettingsReq {

        @Schema(description = "accessToken 有效时长(2小时) ", example = "120")
        private Long accessTokenTimeToLive;

        @Schema(description = "refreshToken 有效时长(7天)", example = "10080")
        private Long refreshTokenTimeToLive;

    }
}
