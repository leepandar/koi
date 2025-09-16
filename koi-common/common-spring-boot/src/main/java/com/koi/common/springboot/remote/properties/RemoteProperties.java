package com.koi.common.springboot.remote.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author lida
 */
@Data
@ConfigurationProperties(RemoteProperties.PREFIX)
public class RemoteProperties {

    public static final String PREFIX = "extend.boot.remote";
    /**
     * 是否启用远程查询
     */
    private Boolean enabled = true;

    /**
     * 递归最大深度
     */
    private Integer maxDepth = 3;

    /**
     * 本地缓存配置信息
     */
    private LocalCache localCache = new LocalCache();


    /**
     * 独立Redis配置信息
     */
    private AloneRedis alone = new AloneRedis();

    @Data
    public static class LocalCache {

        /**
         * Remote
         * 是否启用本地缓存
         * <p>
         * 注意：本地缓存开启后，会存在短暂的数据不一致情况(由localCacheRefreshWriteTime决定)， 所以对数据正确性有要求的项目建议禁用，然后在@Remote.method 方法上执行添加redis等缓存！
         */
        private Boolean enabled = false;
        /**
         * guava缓存的 最大数
         */
        private Integer maximumSize = 1000;
        /**
         * guava更新缓存的下一次时间,分钟
         */
        private Integer refreshWriteTime = 2;
        /**
         * guava自动刷新缓存的线程数量
         */
        private Integer refreshThreadPoolSize = 10;
    }

    @Data
    public static class AloneRedis {
        public static final String PREFIX = "extend.boot.remote.alone";

        /**
         * 是否启用独立Redis
         */
        private Boolean enabled = false;
    }
}
