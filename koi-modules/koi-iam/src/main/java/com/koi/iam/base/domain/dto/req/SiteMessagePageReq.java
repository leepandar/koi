package com.koi.iam.base.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SiteMessagePageReq", description = "消息分页DTO")
public class SiteMessagePageReq extends PageRequest {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "级别")
    private Integer level;

    @Schema(description = "类型")
    private Integer type;

}
