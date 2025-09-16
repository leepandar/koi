package com.koi.iam.auth.service;

import com.koi.iam.auth.domain.dto.resp.ThirdAuthResp;
import com.koi.iam.system.domain.enums.ThirdAuthType;
import com.koi.iam.auth.domain.dto.resp.ThirdAuthResp;
import com.koi.iam.system.domain.enums.ThirdAuthType;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;

/**
 * @author lida
 */
public interface ThirdAuthService {

    /**
     * 三方授权平台
     *
     * @return 授权平台
     */
    ThirdAuthType platform();

    /**
     * AuthRequest
     *
     * @return AuthRequest
     */
    AuthRequest authRequest();

    /**
     * 授权信息
     *
     * @return 授权信息
     */
    ThirdAuthResp authorize();

    /**
     * 回调
     *
     * @param user user
     */
    void callback(AuthUser user);

}
