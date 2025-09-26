package com.koi.suite.file.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FileDelReq", description = "文件删除请求")
public class FileStorageDelReq {

    @Schema(description = "文件id")
    private Long id;

    @Schema(description = "文件url")
    private String fileUrl;

    @Schema(description = "存储平台")
    private String platform;
}
