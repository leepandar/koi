package com.koi.common.log.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

/**
 * 审计日志
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "审计日志")
public class DiffLogInfo {

    @Schema(description = "服务名（比如 WMS 服务/TMS 服务）")
    protected String serviceName;

    @Schema(description = "业务组，比如：订单类型、商品类型")
    protected String businessGroup;

    @Schema(description = "日志标签 比如 编辑用户")
    protected String businessTag;

    @Schema(description = "业务ID")
    protected String businessKey;

    @Schema(description = "日志内容")
    protected String description;

    @Schema(description = "日志状态")
    protected Boolean status;

    @Schema(description = "租户ID")
    protected Long tenantId;

    @Schema(description = "操作人ID")
    protected Long createdBy;

    @Schema(description = "操作人名字")
    protected String createdName;

    @Schema(description = "日志的创建时间")
    protected Instant createdTime;

    @Schema(description = "日志的额外信息")
    protected String extra;

    @Schema(description = "日志的代码信息")
    protected Map<Object, Object> variables;
}