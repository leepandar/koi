package com.koi.suite.file.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 文件存储
 *
 * @author lida
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_file_storage")
public class FileStorage extends SuperEntity<Long> {

    @TableField(value = "url")
    @Schema(description = "文件访问地址")
    private String url;

    @Schema(description = "文件大小，单位字节")
    private Long size;

    @TableField(value = "format_size")
    @Schema(description = "格式化后的文件大小")
    private String formatSize;

    @Schema(description = "文件名称")
    @TableField(value = "filename")
    private String filename;

    @Schema(description = "原始文件名")
    private String originalFilename;

    @TableField(value = "base_path")
    @Schema(description = "基础存储路径")
    private String basePath;

    /**
     * 存储路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 文件扩展名
     */
    @TableField(value = "ext")
    private String ext;

    /**
     * MIME类型
     */
    @TableField(value = "content_type")
    private String contentType;

    /**
     * 存储平台
     */
    @TableField(value = "platform")
    private String platform;

    /**
     * 缩略图访问路径
     */
    @TableField(value = "th_url")
    private String thUrl;

    /**
     * 缩略图名称
     */
    @TableField(value = "th_filename")
    private String thFilename;

    /**
     * 缩略图大小，单位字节
     */
    @TableField(value = "th_size")
    private Long thSize;

    /**
     * 缩略图MIME类型
     */
    @TableField(value = "th_content_type")
    private String thContentType;

    /**
     * 文件所属对象id
     */
    @TableField(value = "object_id")
    private String objectId;

    /**
     * 文件所属对象类型，例如用户头像，评价图片
     */
    @TableField(value = "object_type")
    private String objectType;

    /**
     * 文件元数据
     */
    @TableField(value = "metadata")
    private String metadata;

    /**
     * 文件用户元数据
     */
    @TableField(value = "user_metadata")
    private String userMetadata;

    /**
     * 缩略图元数据
     */
    @TableField(value = "th_metadata")
    private String thMetadata;

    /**
     * 缩略图用户元数据
     */
    @TableField(value = "th_user_metadata")
    private String thUserMetadata;

    /**
     * 附加属性
     */
    @TableField(value = "attr")
    private String attr;

    @TableField(value = "hash_info")
    private String hashInfo;

    @Schema(description = "文件分类")
    private String category;

    @Schema(description = "租户ID")
    private Long tenantId;
}
