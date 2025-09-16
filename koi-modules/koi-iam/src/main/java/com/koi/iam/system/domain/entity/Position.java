package com.koi.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * 岗位
 *
 * @author lida
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_position")
@Schema(name = "Position", description = "岗位")
public class Position extends SuperEntity<Long> {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "组织ID")
    private Long orgId;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

}
