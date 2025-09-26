package com.koi.iam.auth.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.security.domain.UserInfoDetails;
import com.koi.iam.system.domain.dto.req.UserOnlinePageReq;
import com.koi.iam.system.service.UserService;
import com.koi.iam.auth.domain.dto.req.LoginReq;
import com.koi.iam.auth.domain.dto.resp.LoginResp;
import com.koi.iam.auth.strategy.AuthenticatorStrategyTemplate;
import com.koi.iam.auth.support.AuthenticationPrincipal;
import com.koi.iam.base.domain.dto.req.ChangePasswordReq;
import com.koi.iam.base.domain.dto.req.ChangeUserInfoReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Tag(name = "Token管理", description = "Token管理")
public class TokenController {

    private final SaTokenConfig tokenConfig;
    private final AuthenticationContext context;
    private final UserService userService;
    private final AuthenticatorStrategyTemplate strategyTemplate;

    @SaIgnore
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录")
    public LoginResp login(HttpServletRequest request, @Validated @RequestBody LoginReq req) {
        AuthenticationPrincipal principal = AuthenticationPrincipal.builder()
                .loginType(req.getLoginType()).tenantCode(req.getTenantCode())
                .clientId(req.getClientId()).clientSecret(req.getClientSecret())
                .username(req.getUsername()).password(req.getPassword())
                .request(request)
                .build();
        strategyTemplate.prepare(principal);
        strategyTemplate.authenticate(principal);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return LoginResp.builder()
                .accessToken(tokenInfo.getTokenValue())
                .expiresIn(tokenInfo.getTokenTimeout())
                .clientId(principal.getClientId())
                .tokenType(tokenConfig.getTokenPrefix()).build();
    }

    @GetMapping("/userinfo")
    @Operation(summary = "用户信息", description = "获取用户信息")
    public UserInfoDetails userinfo() {
        return (UserInfoDetails) context.getContext();
    }

    @GetMapping("/func_permissions")
    @Operation(summary = "用户信息", description = "获取用户信息")
    public List<String> funcPermissionList() {
        return context.funcPermissionList();
    }

    @PutMapping("/change_password")
    @Operation(summary = "修改密码")
    public void changePassword(@Validated @RequestBody ChangePasswordReq req) {
        if (!StringUtils.equals(req.getNewPassword(), req.getConfirmPassword())) {
            throw CheckedException.badRequest("新密码与确认密码不一致");
        }
        this.userService.changePassword(StpUtil.getLoginIdAsLong(), req.getCurrentPassword(), req.getNewPassword());
    }

    @PutMapping("/change_info")
    @Operation(summary = "信息修改")
    public void changeInfo(@Validated @RequestBody ChangeUserInfoReq req) {
        this.userService.changeInfo(req);
    }

    @SaIgnore
    @DeleteMapping("/logout")
    @Operation(summary = "退出登录")
    public void logout() {
        StpUtil.logout();
    }

    @Operation(summary = "在线用户", description = "分页查询在线用户列表")
    @SaCheckPermission("monitor:online:token-list")
    @GetMapping("/online")
    public IPage<Object> userOnlinePage(UserOnlinePageReq req) {
        return userService.userOnlineList(req);
    }

    @Operation(summary = "强退用户", description = "强退在线用户")
    @Parameter(name = "token", description = "令牌", example = "123", in = ParameterIn.PATH)
    @SaCheckPermission("token:online:logout")
    @DeleteMapping("/{token}")
    public void forceLogout(@PathVariable String token) {
        if (StrUtil.equals(token, StpUtil.getTokenValue())) {
            throw CheckedException.badRequest("不能强退自己");
        }
        StpUtil.kickoutByTokenValue(token);
    }

}
