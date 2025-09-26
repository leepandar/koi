package com.koi.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@TableName("c_generate_template")
@Schema(name = "GenerateTemplate", description = "模板")
public class GenerateTemplate extends SuperEntity<Long> {

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
    @NotBlank(message = "模板描述不能为空")
    private String description;

    @Schema(description = "模板代码")
    @NotBlank(message = "模板代码不能为空")
    private String code;

    @Schema(description = "租户ID")
    private Long tenantId;

}
