package com.koi.iam.base.controller;

import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.koi.iam.system.service.CaptchaService;
import com.koi.iam.base.domain.dto.resp.CaptchaResp;
import com.koi.iam.system.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lida
 */
@RestController
@RequestMapping
@Tag(name = "验证码", description = "验证码")
@RequiredArgsConstructor
public class CaptchaController {

    private final CaptchaService captchaService;

    @GetMapping("/captcha")
    @Operation(summary = "验证码 - [DONE] - [lida]", description = "验证码 - [DONE] - [lida]")
    public CaptchaResp create(@RequestParam(value = "key", required = false) String key,
                              @RequestParam(defaultValue = "130", required = false) Integer width,
                              @RequestParam(defaultValue = "34", required = false) Integer height) {
        final String captchaId = StrUtil.blankToDefault(key, IdUtil.fastSimpleUUID());
        final CircleCaptcha captcha = captchaService.create(captchaId, width, height);
        return CaptchaResp.builder().captchaId(captchaId).code(captcha.getCode()).imageData(captcha.getImageBase64Data()).build();
    }

}
