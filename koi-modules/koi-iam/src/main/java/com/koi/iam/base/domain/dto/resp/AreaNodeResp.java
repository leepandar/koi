package com.koi.iam.base.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AreaNodeResp", description = "区域节点返回")
public class AreaNodeResp {

    @Schema(description = "节点值")
    private Long value;

    @Schema(description = "节点名")
    private String label;

    @Schema(description = "级别")
    private Integer level;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "是否是叶子结点")
    private Boolean isLeaf;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

}
