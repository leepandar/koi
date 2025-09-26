package com.koi.suite.gen.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "GenerateTemplateGroupPageReq", description = "模版组分页请求")
public class GenerateTemplateGroupPageReq extends PageRequest {

    @Schema(name = "name", description = "分组名称")
    private String name;
}
