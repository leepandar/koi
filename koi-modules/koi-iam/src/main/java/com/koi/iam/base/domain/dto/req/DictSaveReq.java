package com.koi.iam.base.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author lida
 */
@Data
public class DictSaveReq {

    @Schema(description = "字典编码(字典编码唯一)")
    @NotBlank(message = "编码不能为空")
//    @Length(max = 64, message = "类型长度不能超过 {max}")
    private String code;

    @NotNull(message = "字典类型不能为空")
    @Schema(description = "字典类型 0=平台字典;1=租户字典")
    private Integer type;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
//    @Length(max = 64, message = "名称长度不能超过 {max}")
    private String name;

    @Schema(description = "描述")
//    @Length(max = 200, message = "描述长度不能超过 {max}")
    private String description;

}
