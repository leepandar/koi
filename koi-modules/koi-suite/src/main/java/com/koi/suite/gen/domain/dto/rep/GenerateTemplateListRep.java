package com.koi.suite.gen.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lida
 */
@Data
public class GenerateTemplateListRep {

    /*
        id
     */
    private Long id;


    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    private String name;


}
