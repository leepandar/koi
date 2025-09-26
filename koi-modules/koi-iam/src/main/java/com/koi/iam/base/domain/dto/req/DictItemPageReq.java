package com.koi.iam.base.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "DictionaryItemPageReq", description = "字典请求DTO")
public class DictItemPageReq extends PageRequest {

    @Schema(description = "字典编码")
    private String dictCode;

    @Schema(description = "标签")
    private String label;

    @Schema(description = "状态")
    private Boolean status;

}
