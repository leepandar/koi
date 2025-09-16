package com.koi.suite.file.domain.enums;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.koi.common.core.entity.DictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 表达式
 *
 * @author lida
 */
@Getter
@RequiredArgsConstructor
@JsonFormat
public enum Expression implements DictEnum<String> {

    /**
     * eq
     */
    EQ(StringPool.EQUALS),
    NE("<>"),
    GT(StringPool.RIGHT_CHEV),
    GE(">="),
    LT(StringPool.LEFT_CHEV),
    LE("<="),
    IS_NULL("IS NULL"),
    LIKE("LIKE"),
    IS_NOT_NULL("IS NOT NULL"),
    ;

    public final String value;

    @JsonCreator
    public static Expression of(String name) {
        for (Expression expression : values()) {
            if (StringUtils.equalsIgnoreCase(expression.name(), name)) {
                return expression;
            }
        }
        return null;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String getDesc() {
        return value;
    }
}
