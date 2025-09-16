package com.koi.iam.base.domain.entity;

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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "b_message_channel")
public class MessageChannel extends SuperEntity<Long> {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态(0=禁用;1=启用)")
    private Boolean status;

    @Schema(description = "设置")
    private String setting;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "描述信息")
    private String description;

}
