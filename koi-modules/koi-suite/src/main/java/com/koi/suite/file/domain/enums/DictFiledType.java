package com.koi.suite.file.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.koi.common.core.entity.DictEnum;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 实体注释中生成的类型枚举
 * 消息中心表
 *
 * @author lida
 */
@Getter
@JsonFormat
@RequiredArgsConstructor
public enum DictFiledType implements DictEnum<String> {

    /**
     * radio
     */
    RADIO("radio", "radio"),
    /**
     * checkbox
     */
    CHECKBOX("checkbox", "checkbox"),
    /**
     * select
     */
    SELECT("select", "select"),
    ;

    private final String type;

    @Parameter(description = "描述信息")
    private final String desc;

    @JsonCreator
    public static DictFiledType of(String type) {
        if (type == null) {
            return null;
        }
        for (DictFiledType info : values()) {
            if (info.type.equals(type)) {
                return info;
            }
        }
        return null;
    }

    @Override
    public String getValue() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
