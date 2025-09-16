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
 * 字典类型
 *
 * @author lida
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
@Schema(name = "SysDict", description = "字典类型")
public class SysDict extends SuperEntity<Long> {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "字典类型 0=平台字典;1=租户字典")
    private Integer type;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

}
