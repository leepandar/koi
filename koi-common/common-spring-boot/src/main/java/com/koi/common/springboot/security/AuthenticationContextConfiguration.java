package com.koi.common.springboot.security;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.core.security.DataPermission;
import com.koi.common.core.threadlocal.ThreadLocalHolder;
import com.koi.common.security.configuration.SecurityExtProperties;
import com.koi.common.security.domain.UserInfoDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

/**
 * 认证上下文
 *
 * @author lida
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthenticationContextConfiguration {

    private static final String USER_INFO = "USER_INFO_KEY";

    @Bean
    public AuthenticationContext authenticationContext(SecurityExtProperties properties) {
        return new AuthenticationContext() {
            @Override
            public UserInfoDetails getContext() {
                return (UserInfoDetails) ThreadLocalHolder.get(USER_INFO,
                        () -> {
                            if (!StpUtil.isLogin()) {
                                return null;
                            }
                            // 优化读取性能,一个线程只读取一次
                            var tokenInfo = StpUtil.getTokenSession().get(properties.getServer().getTokenInfoKey());
                            if (tokenInfo == null) {
                                return null;
                            }
                            return ((JSONObject) tokenInfo).toJavaObject(UserInfoDetails.class);
                        });
            }

            @Override
            public String clientId() {
                return StpUtil.getLoginDeviceType();
            }

            @Override
            public Long tenantId() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getTenantId).orElse(null);
            }

            @Override
            public String tenantName() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getTenantName).orElse(null);
            }

            @Override
            public String tenantCode() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getTenantCode).orElse(null);
            }

            @Override
            public Long userId() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getUserId).orElse(null);
            }

            @Override
            public String nickName() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getNickName).orElse(null);
            }

            @Override
            public String mobile() {
                return Optional.ofNullable(getContext()).map(UserInfoDetails::getMobile).orElse(null);

            }

            @Override
            public List<String> funcPermissionList() {
                return (List<String>) getContext().getFuncPermissions();
            }

            @Override
            public List<String> rolePermissionList() {
                return (List<String>) getContext().getRoles();
            }

            @Override
            public DataPermission dataPermission() {
                return getContext().getDataPermission();
            }

            @Override
            public boolean anonymous() {
                try {
                    if (StpUtil.isLogin()) {
                        return false;
                    }
                    if (getContext() != null) {
                        return false;
                    }
                } catch (Exception e) {
                    log.error("API 访问异常 - {}", e.getLocalizedMessage());
                    return true;
                }
                return true;
            }
        };
    }

}
