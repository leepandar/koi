package com.koi.iam.system.service;

import cn.hutool.captcha.CircleCaptcha;
import com.koi.common.core.entity.Result;

/**
 * @author lida
 */
public interface CaptchaService {

    /**
     * 创建验证码
     *
     * @param key    key
     * @param width  宽度
     * @param height 高度
     * @return 验证码结果
     */
    CircleCaptcha create(String key, Integer width, Integer height);

    /**
     * 验证图形验证码
     *
     * @param key   key
     * @param value val
     * @return 验证结果
     */
    Result<Boolean> valid(String key, String value);

}
