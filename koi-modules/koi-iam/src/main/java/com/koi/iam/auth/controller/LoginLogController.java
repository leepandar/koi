package com.koi.iam.auth.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.db.mybatisplus.datascope.util.DataPermissionUtils;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.auth.domain.entity.LoginLog;
import com.koi.iam.auth.service.LoginLogService;
import com.koi.iam.base.domain.dto.req.LoginLogPageReq;
import com.koi.iam.auth.domain.entity.LoginLog;
import com.koi.iam.auth.service.LoginLogService;
import com.koi.iam.base.domain.dto.req.LoginLogPageReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志
 *
 * @author lida
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/login_logs")
@Tag(name = "登录日志", description = "登录日志")
@RequiredArgsConstructor
public class LoginLogController {

    private final LoginLogService loginLogService;

    @GetMapping
    @Operation(summary = "查询日志 - [DONE] - [lida]", description = "查询日志 - [DONE] - [lida]")
    @SaCheckPermission(value = {"monitor:log:login"})
    public Page<LoginLog> pageList(LoginLogPageReq req) {
        return DataPermissionUtils.executeDefaultDataPermissionRule(() -> loginLogService.page(req.buildPage(), Wraps.<LoginLog>lbQ()
                .like(LoginLog::getCreatedBy, req.getNickName())
                .like(LoginLog::getPrincipal, req.getPrincipal())
                .eq(LoginLog::getPlatform, req.getPlatform())));
    }

}
