package com.koi.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import com.koi.common.log.core.DiffFieldStrategy;
import com.koi.common.log.core.annotation.DiffField;
import com.koi.iam.system.domain.enums.Sex;
import com.koi.iam.system.domain.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author lida
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
@Schema(name = "User", description = "用户")
public class User extends SuperEntity<Long> {

    @Schema(description = "用户名")
    @DiffField(name = "用户名", strategy = DiffFieldStrategy.NOT_NULL)
    private String username;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    @DiffField(name = "昵称")
    private String nickName;

    @DiffField(name = "描述")
    @Schema(description = "描述")
    private String description;

    @Schema(description = "身份证")
    @DiffField(name = "身份证")
    private String idCard;

    @Schema(description = "邮箱")
    @DiffField(name = "邮箱")
    private String email;

    @Schema(description = "手机号")
    @DiffField(name = "手机号")
    private String mobile;

    @Schema(description = "性别")
    @DiffField(name = "性别")
    private Sex sex;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "是否只读")
    private Boolean readonly;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "民族")
    private String nation;

    @Schema(description = "学历")
    private String education;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "机构ID")
    private Long orgId;

    @Schema(description = "岗位ID")
    private Long positionId;

    @Schema(description = "职位状态")
    private String positionStatus;

    @Schema(description = "最后登录IP")
    private String lastLoginIp;

    @Schema(description = "最后登录时间")
    private Instant lastLoginTime;

}
