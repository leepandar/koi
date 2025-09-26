package com.koi.bpm.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SequenceFlowResp",description = "流程连线信息返回参数")
public class SequenceFlowResp {

    @Schema(name = "sequenceFlowId", description = "连线ID")
    private String sequenceFlowId;

    @Schema(name = "sourceBpmnActivityId", description = "来源活动ID")
    private String sourceBpmnActivityId;

    @Schema(name = "targetBpmnActivityId", description = "目标活动节点活动ID")
    private String targetBpmnActivityId;

    @Schema(name = "targetActivityState", description = "目标活动节点状态")
    private int targetActivityState;

    @Schema(name = "processInstanceId", description = "流程实例ID")
    private String processInstanceId;

    @Schema(name = "bpmnSequenceFlowId", description = "连线ID")
    private String bpmnSequenceFlowId;

    @Schema(name = "sequenceFlowName", description = "连线名称")
    private String sequenceFlowName;
}