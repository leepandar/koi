package com.koi.iam.base.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.springboot.log.AccessLogInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("c_opt_log")
public class OptLog extends AccessLogInfo {

    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    private Long id;

}
