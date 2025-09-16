package com.koi.iam.system.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.koi.common.core.entity.DictEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 登录配置类型
 *
 * @author lida
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "登录配置类型")
@JsonFormat
public enum LoginConfigType implements DictEnum<String> {

    /**
     * PLATFORM
     */
    PLATFORM("platform", "PLATFORM"),
    /**
     * SAAS
     */
    SAAS("saas", "SAAS"),
    ;

    @EnumValue
    @JsonValue
    private String type;

    @Schema(description = "描述")
    private String desc;

    @JsonCreator
    public static LoginConfigType of(String type) {
        if (type == null) {
            return null;
        }
        for (LoginConfigType info : values()) {
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
        return String.valueOf(type);
    }

}
