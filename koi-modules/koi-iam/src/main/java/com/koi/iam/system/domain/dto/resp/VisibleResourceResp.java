package com.koi.iam.system.domain.dto.resp;

import com.koi.iam.system.domain.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 构建前端路由
 *
 * @author lida
 */
@Data
public class VisibleResourceResp {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "父级ID")
    private Long parentId;
    @Schema(description = "组件名称")
    private String name;
    @Schema(description = "路径")
    private String path;
    @Schema(description = "菜单名称")
    private String title;
    @Schema(description = "权限资源")
    private String permission;
    @Schema(description = "图标")
    private String icon;
    @Schema(description = "组件")
    private String component;
    @Schema(description = "面是否开启缓存，开启后页面会缓存，不会重新加载，仅在标签页启用时有效")
    private Boolean keepAlive;
    @Schema(description = "是否可见")
    private Boolean visible;
    @Schema(description = "排序")
    private Integer sequence;
    @Schema(description = "类型（directory=目录;menu=菜单;iframe=内嵌;link=外链;button=按钮）")
    private ResourceType type;
    @Schema(description = "状态")
    private Boolean status;
    @Schema(description = "描述信息")
    private String description;
    @Schema(description = "自定义菜单 meta 属性")
    private String meta;
}
