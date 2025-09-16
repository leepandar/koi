package com.koi.suite.gen.domain.dto.rep;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lida
 */
@Data
public class GenerateTablePageRep {

    @Schema(description = "ID")
    private Long id;


    /**
     * 表名称
     */
    private String name;

    /**
     * 表描述
     */
    private String comment;


    /**
     * 实体类名称(首字母大写)
     */
    private String className;

    /**
     * 生成包路径
     * com.koi.platform 对应的packagePath = com/koi/platform
     */
    private String packageName;

    /**
     * 生成模块名
     * 前端请求路径 '/${moduleName}/${businessName}/list'
     */
    private String moduleName;

    /**
     * 是否去掉前缀
     */
    private Boolean removePrefix;
    /**
     * 前缀
     */
    private String prefix;

    /*
     * 业务名称 [英文，用于api路径命名] businessName = "user" => users/list, users/add
     */
    private String businessName;

    /**
     * 作者
     */
    private String author;
    /**
     * 模板组id
     */
    private Long templateGroupId;
}
