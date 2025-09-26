package com.koi.bpm.domain.req;

import com.koi.bpm.api.domain.enums.ProcessModelStatus;
import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ProcessModelPageReq",description = "流程模型管理分页查询")
public class ProcessModelPageReq extends PageRequest {

    @Schema(description = "模型名称")
    private String diagramName;

    @Schema(description = "类别ID")
    private String categoryId;

    @Schema(description = "模型状态:0-未部署,1-已经部署,2-新版本待部署")
    private ProcessModelStatus status;
}
