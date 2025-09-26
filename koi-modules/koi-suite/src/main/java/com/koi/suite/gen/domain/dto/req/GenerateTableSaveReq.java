package com.koi.suite.gen.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "GenerateTableReq", description = "生成表配置信息请求")
public class GenerateTableSaveReq {

    @Schema(description = "表名称")
    @NotBlank(message = "表名称不能为空")
    private String name;

    @Schema(description = "表描述")
    @NotBlank(message = "表描述不能为空")
    private String comment;

    @Schema(description = "实体类名称(首字母大写)")
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    @Schema(description = "生成包路径")
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    @Schema(description = "生成模块名")
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    @NotBlank(message = "作者不能为空")
    @Schema(description = "作者")
    private String author;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "业务名称")
    @NotBlank(message = "业务名称不能为空")
    private String businessName;

    @Schema(description = "是否去掉前缀")
    @NotNull(message = "是否去掉前缀不能为空")
    private Boolean removePrefix;

    @Schema(description = "前缀 eg: sys_user => 前缀 sys_")
    private String prefix;

    @Schema(description = "关联模板组")
    private Long templateGroupId;

}
