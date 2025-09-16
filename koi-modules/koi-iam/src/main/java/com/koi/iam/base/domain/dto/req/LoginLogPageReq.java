package com.koi.iam.base.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "LoginLogPageReq")
public class LoginLogPageReq extends PageRequest {

    @Schema(description = "账号")
    private String principal;

    @Schema(description = "名称")
    private String nickName;

    @Schema(description = "请求平台")
    private String platform;

}
