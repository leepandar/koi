package com.koi.common.springboot.remote;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.koi.common.springboot.remote.properties.RemoteProperties;
import com.koi.common.springboot.remote.properties.RemoteProperties;
import com.koi.common.springboot.remote.properties.RemoteProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 默认的缓存加载器
 *
 * @author lida
 */
@Slf4j
public class DefCacheLoader extends CacheLoader<CacheLoadKeys, Map<Object, Object>> {

    /**
     * 侦听执行器服务
     */
    private final ListeningExecutorService backgroundRefreshPools;

    public DefCacheLoader(RemoteProperties.LocalCache localCache) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("remote-cache-pool-%d").build();
        this.backgroundRefreshPools = MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(localCache.getRefreshThreadPoolSize(), localCache.getRefreshThreadPoolSize(),
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(), namedThreadFactory));
    }

    /**
     * 内存缓存不存在时， 调用时触发加载数据
     *
     * @param type 扩展参数
     * @return 加载后的数据
     */
    @Override
    public Map<Object, Object> load(@NonNull CacheLoadKeys type) {
        log.info("首次读取缓存: " + type);
        return type.loadMap();
    }

    /**
     * 重新载入数据
     *
     * @param key      扩展参数
     * @param oldValue 原来的值
     * @return 重新加载后的数据
     */
    @Override
    public ListenableFuture<Map<Object, Object>> reload(@NonNull CacheLoadKeys key, @NonNull Map<Object, Object> oldValue) {
        return backgroundRefreshPools.submit(() -> load(key));
    }
}
