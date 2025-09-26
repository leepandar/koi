package com.koi.suite.file.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_file_storage_setting")
@Schema(name = "FileStorageSetting", description = "文件存储配置")
public class FileStorageSetting extends SuperEntity<Long> {

    @Schema(description = "存储类型")
    private String type;

    @Schema(description = "自动生成;由 type 与 bucket 组成")
    private String platform;

    /**
     * 是否启用存储 [true:启用;false:禁用]
     * 同个租户下只能开启一个配置
     */
    @Schema(description = "存储类型")
    private Boolean status;

    @Schema(description = "访问key")
    private String accessKey;

    @Schema(description = "密钥")
    private String secretKey;

    @Schema(description = "区域")
    private String region;

    @Schema(description = "访问域名")
    private String domain;

    @Schema(description = "存储桶名称")
    private String bucketName;

    @Schema(description = "基础路径")
    private String basePath;

    @Schema(description = "连接地址")
    private String endPoint;

    @Schema(description = "租户ID")
    private Long tenantId;

}
