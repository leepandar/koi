package com.koi.iam.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * <p>
 * 实体类
 * 字典类型
 * </p>
 *
 * @author lida
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("c_area")
public class AreaEntity extends SuperEntity<Long> {

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    @TableField("level")
    @Schema(description = "层级")
    private Integer level;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @TableField("sequence")
    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "来源")
    @TableField("source")
    private String source;

}
