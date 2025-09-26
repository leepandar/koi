package com.koi.bpm.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "DesignModelFormResp",description = "模型分组请求参数")
public class DesignModelGroupListResp {

    @Schema(description = "类别ID")
    private Long categoryId;

    @Schema(description = "类别名称")
    private String categoryName;

    @Schema(description = "模型")
    private List<DesignModelListResp> modelList;


}
