package com.koi.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import com.koi.common.db.mybatisplus.handler.type.LongListTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 组织
 *
 * @author lida
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_org", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Org", description = "组织")
public class Org extends SuperEntity<Long> {

    @Schema(description = "标题")
    private String label;

    @Schema(description = "树形结构路径")
    @TableField(typeHandler = LongListTypeHandler.class)
    private List<Long> treePath;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "排序号")
    private Integer sequence;

    @Schema(description = "电话")
    private String tel;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "简称")
    private String alias;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

}
