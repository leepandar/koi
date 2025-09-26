package com.koi.iam.base.domain.dto.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CaptchaResp", description = "获取验证码返回")
public class CaptchaResp {

    @Schema(description = "验证码ID")
    private String captchaId;

    @JsonIgnore
    @Schema(description = "验证码")
    private String code;

    @Schema(description = "验证码BASE64")
    private String imageData;

}
