package com.koi.iam.system.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import com.koi.iam.system.domain.enums.Sex;
import com.koi.iam.system.domain.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageReq extends PageRequest {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "性别")
    private Sex sex;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "组织ID")
    private Long orgId;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "学历")
    private String education;

}
