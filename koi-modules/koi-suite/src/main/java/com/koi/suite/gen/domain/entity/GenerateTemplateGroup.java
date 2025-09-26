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
@TableName("c_generate_template_group")
@Schema(name = "GenerateTemplateGroup", description = "模板组别")
public class GenerateTemplateGroup extends SuperEntity<Long> {

    @Schema(description = "分组名称")
    @NotBlank(message = "分组名称不能为空")
    private String name;

    @Schema(description = "分组描述")
    @NotBlank(message = "分组描述不能为空")
    private String desciption;

    @Schema(description = "是否作为默认分组")
    private Boolean isDefault;

    @Schema(description = "租户ID")
    private Long tenantId;

}
