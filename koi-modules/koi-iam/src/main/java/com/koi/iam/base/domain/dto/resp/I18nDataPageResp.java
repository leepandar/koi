package com.koi.iam.base.domain.dto.resp;

import com.koi.iam.base.domain.entity.I18nLocaleMessage;
import com.koi.iam.base.domain.entity.I18nLocaleMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Schema(name = "I18nDataPageResp", description = "国际化返回")
public class I18nDataPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;

    @Schema(description = "语言区域")
    private List<I18nLocaleMessage> languages;

}
