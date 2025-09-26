package com.koi.bpm.api.domain.resp;

import com.koi.bpm.api.domain.enums.ProcessModelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 启动流程实例返回参数
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartInstanceResp {

    @Schema(defaultValue = "流程实例ID")
    private String processInstanceId;

    @Schema(defaultValue = "模型状态")
    private ProcessModelStatus modelStatus;

    @Schema(defaultValue = "业务KEY")
    private String businessKey;

}
