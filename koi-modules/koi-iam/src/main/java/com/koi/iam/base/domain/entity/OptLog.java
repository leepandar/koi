package com.koi.iam.base.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.springboot.log.AccessLogInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("c_opt_log")
@Schema(name = "OptLog", description = "操作日志")
public class OptLog extends AccessLogInfo {

    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    private Long id;

}
