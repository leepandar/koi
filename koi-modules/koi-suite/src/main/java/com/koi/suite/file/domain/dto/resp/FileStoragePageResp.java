package com.koi.suite.file.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

@Data
@Schema(name = "FileStoragePageResp", description = "文件分页返回")
public class FileStoragePageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "文件访问地址")
    private String url;

    @Schema(description = "文件大小，单位字节")
    private String formatSize;

    @Schema(description = "原始文件名")
    private String originalFilename;

    @Schema(description = "基础存储路径")
    private String basePath;

    @Schema(description = "存储路径")
    private String path;

    @Schema(description = "文件扩展名")
    private String ext;

    @Schema(description = "存储平台")
    private String platform;

    @Schema(description = "文件类型")
    private String category;

    @Schema(description = "上传者")
    private String createdName;

    @Schema(description = "上传时间")
    private Instant createdTime;

}
