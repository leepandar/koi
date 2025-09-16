package com.koi.iam.system.domain.converts;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.koi.common.db.mybatisplus.page.BasePageConverts;
import com.koi.iam.auth.domain.entity.RegisteredClient;
import com.koi.iam.system.domain.dto.resp.RegisteredClientResp;
import lombok.SneakyThrows;

/**
 * @author lida
 */
public class RegisteredClientConverts {

    public static final RegisteredClientRef2RespConverts REGISTERED_CLIENT_REF_2_RESP_CONVERTS = new RegisteredClientRef2RespConverts();

    public static class RegisteredClientRef2RespConverts implements BasePageConverts<RegisteredClient, RegisteredClientResp> {

        @SneakyThrows
        @Override
        public RegisteredClientResp convert(RegisteredClient source) {
            if (source == null) {
                return null;
            }
            RegisteredClientResp target = new RegisteredClientResp();
            target.setId(source.getId());
            target.setClientName(source.getClientName());
            target.setClientId(source.getClientId());
            target.setClientSecret(source.getClientSecret());
            target.setClientIdIssuedAt(source.getClientIdIssuedAt());
            target.setClientName(source.getClientName());
            target.setClientSecretExpiresAt(source.getClientSecretExpiresAt());
            target.setGrantTypes(StrUtil.split(source.getGrantTypes(), ','));
            target.setRedirectUris(source.getRedirectUris());
            target.setPostLogoutRedirectUris(source.getPostLogoutRedirectUris());
            target.setScopes(StrUtil.split(source.getScopes(), ','));
            target.setStatus(source.getStatus());
            final String tokenSettings = source.getTokenSettings();
            if (StrUtil.isNotBlank(tokenSettings)) {
                JSONObject settings = JSON.parseObject(tokenSettings);
                target.setAccessTokenTimeToLive(settings.getLong("accessTokenTimeToLive"));
                target.setRefreshTokenTimeToLive(settings.getLong("refreshTokenTimeToLive"));
            }
            return target;
        }
    }

}
