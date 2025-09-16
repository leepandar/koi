package com.koi.suite.file.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "FileStoragePageReq")
public class FileStoragePageReq extends PageRequest {

    @Schema(description = "文件类型")
    private String category;

    @Schema(description = "原始文件名称")
    private String originalFilename;

    @Schema(description = "上传人")
    private String createdName;

}
