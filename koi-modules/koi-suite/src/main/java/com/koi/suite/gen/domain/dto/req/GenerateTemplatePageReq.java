package com.koi.suite.gen.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "GenerateTemplatePageReq", description = "模板分页请求")
public class GenerateTemplatePageReq extends PageRequest {

    @Schema(description = "模板名称")
    private String name;
}
