package com.koi.common.springboot.remote;

import com.koi.common.core.annotation.remote.Remote;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 封装 Remote 注解中解析出来的参数
 * <p>
 * 必须重写该类的 equals() 和 hashCode() 便于Map操作
 *
 * @author lida
 */
@Getter
@ToString
@EqualsAndHashCode
public class LoadKey {

    /**
     * 执行查询任务的类
     */
    private final String tag;
    /**
     * bean 类型
     */
    private final Class<?> beanClass;

    public LoadKey(Remote rf) {
        this.tag = rf.tag();
        this.beanClass = rf.beanClass();
    }
}
