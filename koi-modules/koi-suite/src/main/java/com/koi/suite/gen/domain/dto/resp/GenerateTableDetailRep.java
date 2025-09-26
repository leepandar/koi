package com.koi.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "GenerateTableDetailRep", description = "表详情返回")
public class GenerateTableDetailRep {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "表名称")
    private String name;

    @Schema(description = "表描述")
    private String comment;

    @Schema(description = "实体类名称(首字母大写)")
    private String className;

    /**
     * com.koi.platform 对应的packagePath = com/koi/platform
     */
    @Schema(description = "生成包路径")
    private String packageName;

    /**
     * 前端请求路径 '/${moduleName}/${businessName}/list'
     */
    @Schema(description = "生成模块名")
    private String moduleName;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "业务名称[英文，用于api路径命名] businessName = \"user\" => users/list, users/add")
    private String businessName;

    @Schema(description = "是否去掉前缀")
    private Boolean removePrefix;

    @Schema(description = "前缀")
    private String prefix;

    @Schema(description = "模板分组")
    private Long templateGroupId;

}
