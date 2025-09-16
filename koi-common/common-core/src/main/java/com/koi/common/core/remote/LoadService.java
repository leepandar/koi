package com.koi.common.core.remote;

import cn.hutool.core.lang.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 加载数据
 * </p>
 * 只保留一个方法，若一个表，想要有多重回显场景，可以新建多个实现类，返回不一样的Map
 *
 * @param <VALUE> VALUE
 * @author lida
 */
public interface LoadService<VALUE> {

    /**
     * 根据id查询待回显参数.
     *
     * @param keys 唯一键（可能不是主键ID)
     * @return Map
     */
    Map<Object, VALUE> findByIds(Set<Object> keys);

    /**
     * 根据id查询待回显参数.
     *
     * @param tag tag
     * @return Map
     * @throws RuntimeException 异常
     */
    default Map<Object, VALUE> findByIds(String tag) {
        throw new RuntimeException("暂未实现");
    }

    default void refreshCache(Map<String, List<Pair<String, String>>> data) {

    }

}
