package com.koi.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "GenerateTemplateGroupListRep", description = "模版分组返回")
public class GenerateTemplateGroupListRep {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "模板名称")
    private String name;


}
