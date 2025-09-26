package com.koi.iam.tenant.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantDictionaryItemResp {

    @Schema(description = "字典名")
    private String label;

    @Schema(description = "字典值")
    private String value;

}
