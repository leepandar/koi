package com.koi.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典
 *
 * @param <T> T
 * @author lida
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dict<T> {

    /**
     * 值
     */
    private T value;

    /**
     * 标题
     */
    private String label;

}
