package com.koi.iam.system.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位分页查询
 *
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "PositionPageReq", description = "岗位分页查询DTO")
public class PositionPageReq extends PageRequest {

    @Schema(description = "名称")
    private String title;

    @Schema(description = "组织ID")
    private Long orgId;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "状态")
    private Boolean status;

}
