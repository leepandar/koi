package com.koi.suite.file.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "FileStorageSettingPageReq", description = "文件存储配置请求")
public class FileStorageSettingPageReq extends PageRequest {

    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态")
    private Boolean status;

}
