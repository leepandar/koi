package com.koi.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lida
 */

@Data
@TableName("c_generate_template_group_relation")
public class TemplateGroupRelation {

    private Long templateId;

    private Long groupId;

}
