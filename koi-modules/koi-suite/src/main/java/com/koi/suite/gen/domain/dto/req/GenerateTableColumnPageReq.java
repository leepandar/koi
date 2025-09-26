package com.koi.suite.gen.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "GenerateTableColumnPageReq", description = "表字段分页请求")
public class GenerateTableColumnPageReq extends PageRequest {

    @Schema(name = "tableName", description = "表名")
    private String tableName;

    @Schema(name = "name", description = "字段名")
    private String name;
}
