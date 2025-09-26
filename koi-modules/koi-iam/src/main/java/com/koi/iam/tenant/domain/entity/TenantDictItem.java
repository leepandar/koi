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
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_tenant_dict_item")
@Schema(name = "TenantDictItem", description = "租户字典项")
public class TenantDictItem extends SuperEntity<Long> {

    @Schema(description = "字典ID")
    private Long dictId;

    @Schema(description = "字典ID")
    private String dictCode;

    @Schema(description = "是否只读")
    private Boolean readonly;

    @Schema(description = "编码")
    private String value;

    @Schema(description = "名称")
    private String label;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "排序")
    private Integer sequence;

    @TableField(value = TENANT_ID_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "租户ID")
    private Long tenantId;

}
