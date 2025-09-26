package com.koi.suite.gen.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "GenerateTableColumnSaveReq", description = "表字段保存请求")
public class GenerateTableColumnSaveReq {

    @Schema(description = "ID")
    private Long id;

    @NotBlank(message = "表名称不能为空")
    @Schema(description = "表名称")
    private String tableName;

    @NotBlank(message = "字段名称不能为空")
    @Schema(description = "字段名称")
    private String name;

    @NotBlank(message = "排序不能为空")
    @Schema(description = "排序")
    private Integer sort;

    @NotBlank(message = "字段类型不能为空")
    @Schema(description = "字段类型")
    private String type;

    @Schema(description = "注释")
    private String comment;

    @Schema(description = "字段类型")
    private String propertyType;

    @Schema(description = "属性名")
    private String propertyName;

    @Schema(description = "属性包")
    private String propertyPackage;

    @Schema(description = "是否主键")
    @NotNull(message = "是否主键不能为空")
    private Boolean pk;

    @Schema(description = "是否自增")
    @NotNull(message = "是否自增不能为空")
    private Boolean increment;

    @Schema(description = "是否必填")
    @NotNull(message = "是否必填不能为空")
    private Boolean required;

    @Schema(description = "是否为新增")
    @NotNull(message = "是否为新增不能为空")
    private Boolean inserted;

    @Schema(description = "是否编辑")
    @NotNull(message = "是否编辑不能为空")
    private Boolean edit;

    @Schema(description = "是否列表展示")
    @NotNull(message = "是否列表展示不能为空")
    private Boolean list;

    @Schema(description = "是否查询")
    @NotNull(message = "是否查询不能为空")
    private Boolean search;

    @Schema(description = "查询条件")
    // @NotBlank(message = "查询条件不能为空")
    private String searchCondition;

    @Schema(description = "是否需要生成不能为空")
    @NotNull(message = "是否需要生成不能为空")
    private Boolean generate;
}
