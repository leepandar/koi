package com.koi.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.bpm.domain.enums.TaskCommentType;
import com.koi.common.core.entity.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("wp_process_task_comment")
@Schema(name = "ProcessTaskComment",description = "流程任务评论信息")
public class ProcessTaskComment extends Entity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "任务ID")
    private String procTaskId;

    @Schema(description = "流程实例ID")
    private String procInstId;

    @Schema(description = "任务定义Key")
    private String taskDefinitionKey;

    @Schema(description = "内容")
    private String remark;

    @Schema(description = "附件")
    private String attachment;

    @Schema(description = "0=评论;1=审批")
    private TaskCommentType type;


}
