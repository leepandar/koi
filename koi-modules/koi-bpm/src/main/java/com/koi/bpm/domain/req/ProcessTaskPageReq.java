package com.koi.bpm.domain.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ProcessTaskPageReq",description = "任务分页请求参数")
public class ProcessTaskPageReq extends PageRequest {

    @Schema(description = "审批人", hidden = true)
    private Long approverId;

    @Schema(description = "任务名称")
    private String procTaskName;

    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程定义Id")
    private String procDefName;


}
