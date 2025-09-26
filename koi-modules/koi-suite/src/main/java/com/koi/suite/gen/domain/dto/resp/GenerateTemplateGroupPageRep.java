package com.koi.suite.gen.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "GenerateTemplateGroupPageRep", description = "模版分组分页返回")
public class GenerateTemplateGroupPageRep {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "分组名称")
    private String name;

    @Schema(description = "分组描述")
    private String desciption;

    @Schema(description = "是否作为默认分组")
    private Boolean isDefault;

    @Schema(description = "模板ID集合")
    private List<Long> templateIds;
}
