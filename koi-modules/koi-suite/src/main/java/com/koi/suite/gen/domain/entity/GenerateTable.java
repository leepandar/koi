package com.koi.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("c_generate_table")
@Schema(name = "GenerateTable", description = "表配置信息")
public class GenerateTable extends SuperEntity<Long> {

    @Schema(description = "表名")
    @NotBlank(message = "表名称不能为空")
    private String name;

    @Schema(description = "表描述")
    @NotBlank(message = "表描述不能为空")
    private String comment;

    @Schema(description = "实体类名")
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    @Schema(description = "包名")
    @NotBlank(message = "包名不能为空")
    private String packageName;

    @Schema(description = "生成模块名")
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    @Schema(description = "表列信息")
    @Valid
    @TableField(exist = false)
    private List<GenerateTableColumn> columns;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "业务名称")
    private String businessName;

    @Schema(description = "是否去掉前缀")
    private Boolean removePrefix;

    @Schema(description = "前缀")
    private String prefix;

    @Schema(description = "是否开启swagger配置")
    private Boolean swagger;

    @Schema(description = "关联模板组")
    private Long templateGroupId;

    @Schema(description = "租户ID")
    private Long tenantId;
}



