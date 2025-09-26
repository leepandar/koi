package com.koi.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "GenerateTemplateDetailRep", description = "模版详情返回")
public class GenerateTemplateDetailRep {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "模板名称")
    private String name;

    /**
     * 用于文件生成路径
     * wp/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java
     */
    @Schema(description = "模板路径")
    private String generatePath;

    @Schema(description = "模板描述")
    private String desc;

    @Schema(description = "模板代码")
    private String code;
}
