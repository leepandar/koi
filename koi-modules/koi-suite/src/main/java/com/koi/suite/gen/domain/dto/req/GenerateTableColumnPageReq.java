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
@Schema(name = "GenerateTableColumnPageReq")
public class GenerateTableColumnPageReq extends PageRequest {

    /**
     * 表名
     */
    @Schema(name = "tableName", description = "表名")
    private String tableName;
    /**
     * 字段名
     */
    @Schema(name = "name", description = "字段名")
    private String name;
}
