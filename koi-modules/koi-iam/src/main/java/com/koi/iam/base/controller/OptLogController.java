package com.koi.iam.base.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.springboot.log.AccessLogInfo;
import com.koi.iam.base.service.OptLogService;
import com.koi.iam.base.domain.dto.req.OptLogPageReq;
import com.koi.iam.base.domain.entity.OptLog;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Validated
@RestController
@RequestMapping("/opt_logs")
@Tag(name = "操作日志", description = "操作日志")

@RequiredArgsConstructor
public class OptLogController {

    private final OptLogService optLogService;

    @GetMapping
    @Operation(summary = "查询日志", description = "查询日志")
    @SaCheckPermission(value = {"monitor:log:opt"})
    public Page<OptLog> page(OptLogPageReq req) {
        return this.optLogService.page(req.buildPage(), Wraps.<OptLog>lbQ()
                .eq(OptLog::getHttpMethod, req.getHttpMethod())
                .eq(OptLog::getStatus, req.getStatus())
                .eq(OptLog::getPlatform, req.getPlatform()));
    }

    @DeleteMapping("/{day}")
    @Parameters({
            @Parameter(name = "day", description = "天数", in = ParameterIn.PATH),
    })
    @Operation(summary = "查询日志", description = "查询日志")
    public void batchDelete(@PathVariable Integer day) {
        this.optLogService.remove(Wraps.<OptLog>lbQ().le(OptLog::getStartTime, Instant.now().plus(-day, ChronoUnit.DAYS)));
    }

    @PostMapping("/listener")
    @Operation(summary = "监听日志", description = "监听日志")
    public void listener(@RequestBody AccessLogInfo info) {
        this.optLogService.listener(info);
    }

}
