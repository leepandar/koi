package com.koi.iam.auth.strategy;

import com.koi.common.core.entity.Result;
import com.koi.common.core.exception.CheckedException;
import com.koi.iam.system.service.CaptchaService;
import com.koi.iam.auth.support.AuthenticationPrincipal;
import com.koi.iam.auth.support.AuthenticationPrincipal;
import com.koi.iam.system.service.CaptchaService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 集成验证码认证.
 *
 * @author lida
 **/
@Component
public class VerificationCodeAuthenticatorStrategy extends UsernamePasswordAuthenticatorStrategy {

    private static final String VERIFICATION_CODE_AUTH_TYPE = "vc";

    @Resource
    private CaptchaService captchaService;

    @Override
    public void prepare(final AuthenticationPrincipal principal) {
        String vcToken = principal.getParameter("vc_token");
        String vcCode = principal.getParameter("vc_code");
        // 验证验证码
        final Result<Boolean> result = captchaService.valid(vcToken, vcCode);
        if (!result.isSuccessful()) {
            throw CheckedException.badRequest(result.getMessage());
        }
        super.prepare(principal);
    }

    @Override
    public String loginType() {
        return VERIFICATION_CODE_AUTH_TYPE;
    }
}
