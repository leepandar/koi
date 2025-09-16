package com.koi.iam.base.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.koi.iam.base.service.PlatService;
import com.koi.iam.base.domain.dto.resp.SiteSettingDetailResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台管理
 *
 * @author lida
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/plat")
@Tag(name = "平台管理", description = "平台管理")
public class PlatController {

    private final PlatService platService;

    @SaIgnore
    @GetMapping("/site-setting")
    @Operation(summary = "租户列表")
    public SiteSettingDetailResp siteSetting(HttpServletRequest request) {
        return platService.siteSetting(request);
    }

}
