package com.koi.bpm.domain.req;

import com.alibaba.fastjson2.JSONArray;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author lida
 */
@Data
@Schema(name = "FormDesignSaveReq")
public class FormDesignSaveReq {

    @Schema(defaultValue = "脚本")
    private String script;

    @NotEmpty(message = "表单字段不能为空")
    private JSONArray schemas;
}
