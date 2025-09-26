package com.koi.suite.online.domain.entity;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.Fastjson2TypeHandler;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_online_form_data")
@EqualsAndHashCode(callSuper = true)
@Schema(name = "EpicDesignerField", description = "表单数据")
public class OnlineFormData extends SuperEntity<Long> {

    @Schema(defaultValue = "定义KEY")
    private String definitionKey;

    @TableField(typeHandler = Fastjson2TypeHandler.class)
    @Schema(defaultValue = "表单数据")
    private JSONObject formData;

    @Schema(defaultValue = "租户ID")
    private Long tenantId;

}
