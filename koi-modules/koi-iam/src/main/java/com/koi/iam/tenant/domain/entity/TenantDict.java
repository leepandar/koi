package com.koi.iam.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@TableName("t_tenant_dict")
@Schema(name = "TenantDict", description = "租户字典")
public class TenantDict extends SuperEntity<Long> {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @TableField(value = TENANT_ID_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "是否只读")
    private Boolean readonly;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "状态")
    private Boolean status;

}
