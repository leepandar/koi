package com.koi.common.springboot.remote;

import com.koi.common.core.annotation.remote.Remote;
import com.koi.common.core.remote.LoadService;
import com.koi.common.springboot.remote.dict.DictLoadService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 封装 Remote 注解中解析出来的参数
 * <p>
 * 必须重写该类的 equals() 和 hashCode() 便于Map操作
 *
 * @author lida
 */
@Slf4j
@Getter
@ToString
@EqualsAndHashCode
public class CacheLoadKeys {

    private final Class<?> beanClass;
    private String tag;

    /**
     * 动态查询值
     */
    private Set<Object> keys = new HashSet<>();
    private LoadService<Object> loadService;

    public CacheLoadKeys(Remote rf) {
        this.beanClass = rf.beanClass();
    }

    public CacheLoadKeys(LoadKey lk, LoadService<Object> loadService, Set<Object> keys) {
        this.beanClass = lk.getBeanClass();
        this.loadService = loadService;
        this.keys = keys;
        this.tag = lk.getTag();
    }

    /**
     * 加载数据
     *
     * @return 查询指定接口后得到的值
     */
    public Map<Object, Object> loadMap() {
        if (DictLoadService.class.isAssignableFrom(beanClass)) {
            return loadService.findByIds(tag);
        }
        return loadService.findByIds(keys);
    }
}
