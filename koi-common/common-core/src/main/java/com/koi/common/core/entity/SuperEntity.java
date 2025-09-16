package com.koi.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * 增强实体类
 *
 * @param <T> ID 类型
 * @author lida
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SuperEntity<T> extends Entity<T> {

    public static final String DELETED = "deleted";

    public static final String UPDATE_TIME = "lastModifiedTime";
    public static final String UPDATE_USER = "lastModifiedBy";
    public static final String UPDATE_USER_NAME = "lastModifiedName";

    public static final String UPDATE_TIME_COLUMN = "last_modified_time";
    public static final String UPDATE_USER_COLUMN = "last_modified_by";
    public static final String UPDATE_USER_NAME_COLUMN = "last_modified_name";

    @Schema(description = "最后修改时间")
    @TableField(value = UPDATE_TIME_COLUMN, fill = FieldFill.UPDATE)
    private Instant lastModifiedTime;

    @Schema(description = "最后修改人ID")
    @TableField(value = UPDATE_USER_COLUMN, fill = FieldFill.UPDATE)
    private T lastModifiedBy;

    @Schema(description = "最后修改人名称")
    @TableField(value = UPDATE_USER_NAME_COLUMN, fill = FieldFill.UPDATE)
    private String lastModifiedName;

    @TableLogic(value = "false", delval = "true")
    @TableField(value = DELETED, fill = FieldFill.INSERT)
    @Schema(description = "逻辑删除")
    private Boolean deleted;

}
