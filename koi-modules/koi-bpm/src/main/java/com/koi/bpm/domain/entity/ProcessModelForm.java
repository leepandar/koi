package com.koi.bpm.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wp_process_model_form")
public class ProcessModelForm extends SuperEntity<Long> {

    @Schema(description = "模型ID")
    private Long modelId;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(defaultValue = "脚本")
    private String formScript;

    @Schema(description = "表单配置")
    private String formSchemas;

}