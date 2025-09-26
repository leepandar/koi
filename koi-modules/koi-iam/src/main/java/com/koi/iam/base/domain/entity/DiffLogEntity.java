package com.koi.iam.base.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.koi.common.db.mybatisplus.handler.type.JsonTypeHandler;
import com.koi.common.log.domain.DiffLogInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("c_diff_log")
@Schema(name = "DiffLogEntity", description = "日志")
public class DiffLogEntity extends DiffLogInfo {

    @TableField(typeHandler = JsonTypeHandler.class)
    @Schema(description = "日志的代码信息")
    protected Map<Object, Object> variables;

    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    @Schema(description = "ID")
    private Long id;
}
