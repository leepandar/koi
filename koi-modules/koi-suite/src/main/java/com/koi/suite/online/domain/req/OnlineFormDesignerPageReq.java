package com.koi.suite.online.domain.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OnlineFormDesignerPageReq", description = "表单分页请求参数")
public class OnlineFormDesignerPageReq extends PageRequest {

    @Schema(description = "定义KEY")
    private String definitionKey;

    @Schema(description = "标题")
    private String title;

}
