package com.koi.iam.auth.service.impl;

import com.koi.iam.auth.configuration.ThirdAuthProperties;
import com.koi.iam.auth.domain.dto.resp.ThirdAuthResp;
import com.koi.iam.auth.service.ThirdAuthService;
import com.koi.iam.system.domain.enums.ThirdAuthType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.stereotype.Service;

/**
 * GITEE 对接文档 <a href="https://www.justauth.cn/guide/oauth/gitee/"/>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ThirdAuthGiteeServiceImpl implements ThirdAuthService {

    private final ThirdAuthProperties thirdAuthProperties;

    @Override
    public ThirdAuthType platform() {
        return ThirdAuthType.GITEE;
    }

    /**
     * 三方授权平台
     *
     * @return
     */
    @Override
    public ThirdAuthResp authorize() {
        AuthRequest authRequest = authRequest();
        // 随机的state字符串
        String state = AuthStateUtils.createState();
        // 构造授权地址
        String authorizeUrl = authRequest.authorize(state);
        return ThirdAuthResp.builder().authorizeUrl(authorizeUrl).state(state).build();
    }

    /**
     * 回调
     *
     * @param user user
     */
    @Override
    public void callback(AuthUser user) {
    }

    /**
     * 授权请求
     *
     * @return
     */
    @Override
    public AuthRequest authRequest() {
        AuthConfig config = thirdAuthProperties.getConfigMap().get(platform());
        return new AuthGiteeRequest(config);
    }

}
