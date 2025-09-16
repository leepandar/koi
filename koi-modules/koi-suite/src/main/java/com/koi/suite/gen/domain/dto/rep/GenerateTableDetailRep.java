package com.koi.suite.gen.domain.dto.rep;

import lombok.Data;

/**
 * @author lida
 */
@Data
public class GenerateTableDetailRep {
    /**
     * 表ID
     */
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
     * 作者
     */
    private String author;

    /**
     * 邮箱
     */
    private String email;
    /*
     * 业务名称 [英文，用于api路径命名] businessName = "user" => users/list, users/add
     */
    private String businessName;
    /**
     * 是否去掉前缀
     */
    private Boolean removePrefix;
    /**
     * 前缀
     */
    private String prefix;
    /**
     * 模板分组
     */
    private Long templateGroupId;

}
