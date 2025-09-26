package com.koi.iam.system.domain.dto.resp;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.koi.common.core.annotation.remote.Remote;
import com.koi.common.excel.convert.DictConverter;
import com.koi.common.springboot.remote.dict.DictLoadService;
import com.koi.iam.system.domain.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * 用户返回
 *
 * @author lida
 */
@Data
@ColumnWidth(30)
@Schema(name = "RoleResResp", description = "用户返回")
public class UserResp {

    @ExcelIgnore
    @Schema(description = "ID")
    private Long id;

    @ExcelProperty("账号")
    @Schema(description = "账号")
    private String username;

    @ExcelIgnore
    @Schema(description = "租户ID")
    private Long tenantId;

    @ExcelProperty("昵称")
    @Schema(description = "昵称")
    private String nickName;

    @ExcelProperty("身份证")
    @Schema(description = "身份证")
    private String idCard;

    @ExcelProperty("邮箱")
    @Schema(description = "邮箱")
    private String email;

    @ExcelProperty("手机号")
    @Schema(description = "手机号")
    private String mobile;

    @ExcelProperty(value = "性别", converter = DictConverter.class)
    @Schema(description = "性别")
    private Sex sex;

    @ExcelIgnore
    @Schema(description = "头像")
    private String avatar;

    @ExcelIgnore
    @Schema(description = "是否只读")
    private Boolean readonly;

    @ExcelIgnore
    @Schema(description = "状态")
    private Boolean status;

    @ExcelIgnore
    @Schema(description = "民族")
    @Remote(beanClass = DictLoadService.class, tag = "NATION", fields = {@Remote.FieldRef(target = "nationName")})
    private String nation;

    @Schema(description = "民族")
    private String nationName;

    @ExcelIgnore
    @Schema(description = "学历")
    private String education;

    @ExcelProperty("生日")
    @Schema(description = "生日")
    private LocalDate birthday;

    @ExcelIgnore
    @Schema(description = "机构ID")
    private Long orgId;

    @ExcelIgnore
    @Schema(description = "岗位ID")
    private Long positionId;

    @ExcelIgnore
    @Schema(description = "职位状态")
    private String positionStatus;

    @ExcelProperty("描述")
    @Schema(description = "描述")
    private String description;

    @ExcelProperty("创建时间")
    @Schema(description = "创建时间")
    private Instant createdTime;

}
