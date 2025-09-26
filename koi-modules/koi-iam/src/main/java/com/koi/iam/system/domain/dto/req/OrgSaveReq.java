package com.koi.iam.system.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 组织保存DTO
 *
 * @author lida
 */
@Data
@Schema(name = "OrgSaveReq", description = "组织保存DTO")
public class OrgSaveReq {

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过 {max}")
    private String label;

    @Schema(description = "简称")
    @Length(max = 255, message = "简称长度不能超过 {max}")
    private String abbreviation;

    @Schema(description = "部门电话")
    private String tel;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
    @Length(max = 255, message = "描述长度不能超过 {max}")
    private String description;

}
