package com.koi.iam.base.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 修改密码
 *
 * @author lida
 */
@Data
@Schema(name = "ChangePasswordReq", description = "修改密码")
public class ChangePasswordReq {

    @Schema(description = "旧密码")
    @NotBlank(message = "旧密码不能为空")
//    @Length(max = 64, message = "旧密码长度不能超过 {max}")
    private String currentPassword;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
//    @Length(max = 64, message = "新密码长度不能超过 {max}")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "密码不符合复杂性要求，必须包含至少8个字符，其中包括一个大写字母、一个小写字母、一个数字和一个特殊字符。")
    private String newPassword;

    @Schema(description = "确认密码")
    @NotBlank(message = "确认密码不能为空")
//    @Length(max = 64, message = "确认密码长度不能超过 {max}")
    private String confirmPassword;
}
