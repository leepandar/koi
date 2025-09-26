package com.koi.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("c_generate_table_column")
@Schema(name = "GenerateTableColumn", description = "表字段信息")
public class GenerateTableColumn extends SuperEntity<Long> {

    @Schema(description = "表名")
    private String tableName;

    @Schema(description = "字段名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "字段类型")
    private String type;

    @Schema(description = "注释")
    private String comment;

    @Schema(description = "属性类型")
    private String propertyType;

    @Schema(description = "属性名")
    private String propertyName;

    @Schema(description = "属性包")
    private String propertyPackage;

    @Schema(description = "是否主键")
    private Boolean pk;

    @Schema(description = "是否自增")
    private Boolean increment;

    @Schema(description = "是否必填")
    private Boolean required;

    @Schema(description = "是否为新增")
    private Boolean inserted;

    @Schema(description = "是否编辑")
    private Boolean edit;

    @Schema(description = "是否列表展示")
    private Boolean list;

    @Schema(description = "是否查询")
    private Boolean search;

    @Schema(description = "查询条件")
    private String searchCondition;

    @Schema(description = "是否需要生成;默认设置1：是")
    private Boolean generate;


    @Schema(description = "租户ID")
    private Long tenantId;
}
