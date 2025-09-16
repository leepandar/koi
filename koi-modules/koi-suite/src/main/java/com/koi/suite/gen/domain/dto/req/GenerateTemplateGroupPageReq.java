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
@Schema(name = "GenerateTemplateGroupPageReq")
public class GenerateTemplateGroupPageReq extends PageRequest {

    @Schema(name = "name", description = "分组名称")
    private String name;
}
