package com.koi.bpm.domain.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ProcessInstancePageReq",description = "流程实例查询参数")
public class ProcessInstancePageReq extends PageRequest {

    @Schema(description = "流程定义key")
    private String procDefKey;

    @Schema(description = "流程类别ID")
    private Long procInstCategoryId;

    @Schema(description = "流程定义名称")
    private String procDefName;

    @Schema(description = "流程实例名称")
    private String procInstName;

    @Schema(description = "流程实例状态")
    private Boolean procInstActivate;

}
