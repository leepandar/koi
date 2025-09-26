package com.koi.iam.tenant.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_db_setting")
@EqualsAndHashCode(callSuper = true)
public class DbSetting extends SuperEntity<Long> {

    @Schema(description = "连接名")
    private String name;

    @Schema(description = "数据库类型(只支持Mysql)")
    private String dbType;

    @Schema(description = "驱动类名")
    private String driverClassName;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "host")
    private String host;

    @Schema(description = "是否禁用")
    private Boolean locked;

    @Schema(description = "描述")
    private String description;

}
