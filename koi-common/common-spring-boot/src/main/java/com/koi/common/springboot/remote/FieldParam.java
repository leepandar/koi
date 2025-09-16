package com.koi.common.springboot.remote;

import com.koi.common.core.annotation.remote.Remote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装字段上标记了 Remote 注解的字段
 *
 * @author lida
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldParam {

    /**
     * 当前字段上的注解
     */
    private Remote remote;
    /**
     * 从当前字段的值构造出的调用api#method方法的参数
     */
    private Object actualValue;
    /**
     * 当前字段的具体值
     */
    private Object originalValue;

    /**
     * 当前 字段名
     */
    private String fieldName;

    private LoadKey loadKey;
}
