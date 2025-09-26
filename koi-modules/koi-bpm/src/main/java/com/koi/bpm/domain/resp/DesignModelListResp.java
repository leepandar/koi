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
@Schema(name = "DesignModelListResp",description = "流程模型列表返回参数")
public class DesignModelListResp {

    @Schema(description = "模型ID")
    private Long id;

    @Schema(description = "ICON")
    private String diagramIcon;

    @Schema(description = "模型名称")
    private String diagramName;

    @Schema(description = "模型身份ID")
    private String definitionId;


}
