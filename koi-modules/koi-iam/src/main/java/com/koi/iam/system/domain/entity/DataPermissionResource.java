package com.koi.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.Entity;
import com.koi.common.core.security.DataResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据权限关联表
 *
 * @author lida
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_data_permission_resource")
@EqualsAndHashCode(callSuper = true)
public class DataPermissionResource extends Entity<Long> {

    @Schema(description = "所有者 ID")
    @TableField("owner_id")
    private Long ownerId;

    @Schema(description = "所有者 类型")
    private DataResourceType ownerType;

    @Schema(description = "数据ID")
    @TableField("data_id")
    private Long dataId;

    @Schema(description = "数据类型")
    private DataResourceType dataType;
}
