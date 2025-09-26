package com.koi.iam.base.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CommonDataResp", description = "公共返回")
public class CommonDataResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "名称")
    private String name;
}
