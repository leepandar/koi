package com.koi.iam.auth.listener;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson2.JSONObject;
import com.koi.common.core.RegionUtils;
import com.koi.common.db.utils.TenantHelper;
import com.koi.common.security.configuration.SecurityExtProperties;
import com.koi.common.security.domain.UserInfoDetails;
import com.koi.iam.auth.domain.entity.LoginLog;
import com.koi.iam.auth.mapper.LoginLogMapper;
import com.koi.iam.system.domain.entity.User;
import com.koi.iam.system.service.UserService;
import com.koi.iam.auth.support.AuthenticationPrincipal;
import com.koi.iam.auth.support.domain.UserTenantAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 登录监听器
 *
 * @author lida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WpTokenListener implements SaTokenListener {

    private static final String USER_AGENT = "User-Agent";
    private final SecurityExtProperties extProperties;
    private final LoginLogMapper loginLogMapper;
    private final UserService userService;
    private final HttpServletRequest request;

    /**
     * 登陆
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue 本次登录产生的 token 值
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginParameter loginParameter) {
        String ip = JakartaServletUtil.getClientIP(request);
        String region = RegionUtils.getRegion(ip);
        String ua = request.getHeader(USER_AGENT);
        final UserAgent userAgent = UserAgentUtil.parse(ua);
        final Browser browser = userAgent.getBrowser();
        final long userId = (long) loginId;
        String principal = SaHolder.getStorage().getString(AuthenticationPrincipal.PRINCIPAL);
        String principalType = SaHolder.getStorage().getString(AuthenticationPrincipal.PRINCIPAL_TYPE);
        UserTenantAuthentication authentication = SaHolder.getStorage().getModel(AuthenticationPrincipal.AUTHENTICATION, UserTenantAuthentication.class);
        UserInfoDetails info = this.userService.userinfo(authentication);
        LoginLog loginLog = LoginLog.builder().principal(principal)
                .clientId(loginParameter.getDeviceType())
                .tenantId(info.getTenantId()).tenantCode(info.getTenantCode())
                .location(region).ip(ip)
                .platform(userAgent.getPlatform().getName())
                .engine(userAgent.getEngine().getName())
                .browser(browser.getName())
                .os(userAgent.getOs().getName())
                .loginType(principalType)
                .createdBy(userId).createdTime(Instant.now()).createdName(info.getNickName())
                .build();
        info.setLoginLog(JSONObject.from(loginLog));
        StpUtil.getTokenSessionByToken(tokenValue).set(extProperties.getServer().getTokenInfoKey(), info);
        // 记录登录日志
        TenantHelper.executeWithTenantDb(info.getTenantCode(), () -> this.loginLogMapper.insert(loginLog));
        // 刷新登录时间和IP
        this.userService.updateById(User.builder().id(userId).lastLoginIp(ip).lastLoginTime(Instant.now()).build());
    }

    @Override
    public void doRenewTimeout(String loginType, Object loginId, String tokenValue, long timeout) {

    }

    /**
     * 登出
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
    }

    /**
     * 每次被踢下线时触发
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        // 数据库可以记录一下操作日志
    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {

    }

    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {

    }

    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }


}