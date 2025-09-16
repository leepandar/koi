package com.koi.iam.system.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "UserOnlinePageReq")
public class UserOnlinePageReq extends PageRequest {

    @Schema(description = "租户")
    private Long tenantId;

    @Schema(description = "账户名")
    private String principal;

    @Schema(description = "操作平台")
    private String platform;

    @Schema(description = "客户端ID")
    private String clientId;
}
