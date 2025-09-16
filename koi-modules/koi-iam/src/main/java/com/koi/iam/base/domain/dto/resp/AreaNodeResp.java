package com.koi.iam.base.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaNodeResp {

    @Schema(description = "节点值")
    private Long value;
    @Schema(description = "节点名")
    private String label;
    @Schema(description = "级别")
    private Integer level;
    @Schema(description = "父ID")
    private Long parentId;
    private Boolean isLeaf;
    /**
     * 经度
     */
    @Schema(description = "经度")
    private BigDecimal longitude;
    /**
     * 纬度
     */
    @Schema(description = "纬度")
    private BigDecimal latitude;

}
