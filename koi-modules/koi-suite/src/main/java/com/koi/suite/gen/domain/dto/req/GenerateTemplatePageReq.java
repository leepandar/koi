package com.koi.suite.gen.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "GenerateTemplatePageReq")
public class GenerateTemplatePageReq extends PageRequest {

    @Schema(name = "name", description = "模板名称")
    private String name;
}
