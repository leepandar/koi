package com.koi.bpm.domain.resp;

import com.alibaba.fastjson2.JSONArray;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "DesignModelFormResp",description = "模型表单请求参数")
public class DesignModelFormResp {

    @Schema(defaultValue = "脚本")
    private String script;

    @NotEmpty(message = "表单字段不能为空")
    private JSONArray schemas;

    @NotEmpty(message = "模型ID")
    private Long modelId;

}
