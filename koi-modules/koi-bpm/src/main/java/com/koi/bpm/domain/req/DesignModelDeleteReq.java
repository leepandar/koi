
package com.koi.bpm.domain.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "DesignModelDeleteReq",description = "删除流程模型")
public class DesignModelDeleteReq {

    @Schema(title = "是否级联，默认false")
    private Boolean cascade = false;

    @Schema(title = "是否跳过自定义监听器，默认false")
    private Boolean skipCustomListeners = false;

    @Schema(title = "是否跳过io映射，默认false")
    private Boolean skipIoMappings = false;
}
