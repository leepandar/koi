package com.koi.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("c_generate_template_group_relation")
@Schema(name = "TemplateGroupRelation", description = "模板组与模版关系")
public class TemplateGroupRelation {

    @Schema(description = "模版ID")
    private Long templateId;

    @Schema(description = "分组ID")
    private Long groupId;

}
