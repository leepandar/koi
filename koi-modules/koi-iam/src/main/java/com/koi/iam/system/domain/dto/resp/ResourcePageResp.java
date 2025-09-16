package com.koi.iam.system.domain.dto.resp;

import com.koi.iam.system.domain.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author lida
 */
@Data
public class ResourcePageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "名称")
    private String title;

    @Schema(description = "是否只读")
    private Boolean readonly;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "资源类型（2=按钮，1=菜单）'")
    private ResourceType type;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "是否全局菜单")
    private Boolean global;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "创建时间")
    private Instant createdTime;

    @Schema(description = "描述信息")
    private String description;
}
