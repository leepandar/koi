package com.koi.common.springboot.log;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

@Data
@Schema(name = "AccessLogInfo", description = "访问日志")
public class AccessLogInfo {

    @Schema(description = "租户ID")
    protected Long tenantId;

    @Schema(description = "租户编码")
    protected String tenantCode;

    @Schema(description = "操作IP")
    protected String ip;

    @Schema(description = "地区信息")
    protected String location;

    @Schema(description = "日志链路追踪id日志标志")
    protected String trace;

    @Schema(description = "操作模块")
    protected String module;

    @Schema(description = "操作描述")
    protected String description;

    @Schema(description = "请求方法")
    protected String action;

    @Schema(description = "请求地址")
    protected String uri;

    @Schema(description = "HTTP Method")
    protected String httpMethod;

    @Schema(description = "请求参数")
    protected String request;

    @Schema(description = "返回值")
    protected String response;

    @Schema(description = "异常描述")
    protected String message;

    @Schema(description = "日志状态(true:正常;false:异常)")
    protected Boolean status;

    @Schema(description = "开始时间")
    protected Instant startTime;

    @Schema(description = "结束时间")
    protected Instant endTime;

    @Schema(description = "消耗时间")
    protected Long duration;

    @Schema(description = "浏览器信息")
    protected String browser;

    @Schema(description = "浏览器引擎")
    protected String engine;

    @Schema(description = "操作系统")
    protected String os;

    @Schema(description = "平台")
    protected String platform;

    @Schema(description = "创建者ID")
    protected Long createdBy;

    @Schema(description = "创建者名字")
    protected String createdName;

    @Schema(description = "请求令牌")
    private String token;

    public Boolean getStatus() {
        return StrUtil.isBlank(message);
    }
}
