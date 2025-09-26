package com.koi.suite.gen.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "GenerateTemplatePageReq", description = "生成模板请求")
public class GenerateTemplateSaveReq {

    @Schema(description = "模板名称")
    @NotBlank(message = "模板名称不能为空")
    private String name;

    /**
     * 用于文件生成路径
     * wp/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java
     */
    @Schema(description = "模板路径")
    @NotBlank(message = "模板路径不能为空")
    private String generatePath;

    @Schema(description = "模板描述")
    //   @NotBlank(message = "模板描述不能为空")
    private String description;

    @Schema(description = "模板代码")
    @NotBlank(message = "模板代码不能为空")
    private String code;

    @Schema(description = "关联的模板ids")
    private List<Long> templateIds;

}
