package com.koi.iam.tenant.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lida
 */
@Data
@Schema(name = "TenantSettingResp")
public class TenantSettingResp {

    @Schema(description = "站点地址")
    private String siteUrl;

    @Schema(description = "站点标题")
    private String siteTitle;

    @Schema(description = "站点子标题")
    private String siteSubTitle;

    @Schema(description = "站点LOGO")
    private String siteLogo;

    @Schema(description = "DB-ID")
    private Long dbId;

}
