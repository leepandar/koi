package com.koi.iam.system.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 职位
 *
 * @author lida
 */
@Data
@Schema(name = "StationSaveReq", description = "职位")
public class PositionSaveReq {

    @Schema(description = "名称")
//    @Length(max = 255, message = "职位名称长度不能超过{max}")
    @NotBlank(message = "职位名称不能为空")
    private String title;

    @Schema(description = "职位编码")
    private String code;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "组织ID")
    @NotNull(message = "组织不能为空")
    private Long orgId;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
//    @Length(max = 255, message = "描述长度不能超过 {max}")
    private String description;

}
