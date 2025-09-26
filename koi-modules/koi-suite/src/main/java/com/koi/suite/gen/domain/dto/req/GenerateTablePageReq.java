package com.koi.suite.gen.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "GenerateTableReq", description = "表分页请求")
public class GenerateTablePageReq extends PageRequest {

    @Schema(description = "表名")
    private String name;
}
