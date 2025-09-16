package com.koi.suite.gen.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author lida
 */
@Data
public class GenerateTemplatePageRep {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "模板名称")
    private String name;

    /**
     * 文件路径模板
     * 用于文件生成路径
     * wp/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java
     */
    @Schema(description = "模板路径")
    private String generatePath;

    @Schema(description = "模板描述")
    private String description;

    @Schema(description = "模板内容")
    private String templateContent;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;
}
