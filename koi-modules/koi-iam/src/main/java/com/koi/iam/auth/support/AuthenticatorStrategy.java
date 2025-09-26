package com.koi.iam.auth.support;

import com.koi.iam.auth.support.domain.UserTenantAuthentication;

/**
 * 认证策略
 **/
public interface AuthenticatorStrategy {

    String DEFAULT_AUTH_TYPE = "password";

    /**
     * 处理集成认证
     *
     * @param principal principal
     * @return 认证
     */
    UserTenantAuthentication authenticate(AuthenticationPrincipal principal);

    /**
     * 进行预处理
     *
     * @param principal principal
     */
    void prepare(AuthenticationPrincipal principal);

    /**
     * 判断是否支持集成认证类型
     *
     * @param loginType loginType
     * @return 是否统一处理
     */
    default boolean support(String loginType) {
        return loginType != null && loginType.equalsIgnoreCase(loginType());
    }

    /**
     * 登录方式
     *
     * @return 默认密码登录
     */
    default String loginType() {
        return DEFAULT_AUTH_TYPE;
    }

    /**
     * 认证结束后执行
     *
     * @param principal integrationAuthentication
     */
    default void complete(AuthenticationPrincipal principal) {

    }

}