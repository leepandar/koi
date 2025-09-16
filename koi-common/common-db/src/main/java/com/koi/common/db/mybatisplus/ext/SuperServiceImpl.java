package com.koi.common.db.mybatisplus.ext;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 不含缓存的Service实现
 * 2，removeById：重写 ServiceImpl 类的方法，删除db
 * 3，removeByIds：重写 ServiceImpl 类的方法，删除db
 * 4，updateAllById： 新增的方法： 修改数据（所有字段）
 * 5，updateById：重写 ServiceImpl 类的方法，修改db后
 *
 * @param <M> Mapper
 * @param <T> 实体
 * @author lida
 */
public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {

    /**
     * 默认批次提交数量
     */
    private static final int DEFAULT_BATCH_SIZE = 500;

    private static final Logger logger = LoggerFactory.getLogger(SuperServiceImpl.class);

    public SuperMapper<T> getSuperMapper() {
        if (baseMapper != null) {
            return baseMapper;
        }
        throw new RuntimeException("Mapper类转换异常");
    }

    @Override
    public boolean insertBatch(List<T> list) {
        if (CollUtil.isEmpty(list)) {
            logger.warn("请求数据为空....");
            return false;
        }
        CollUtil.split(list, DEFAULT_BATCH_SIZE).forEach(baseMapper::insertBatchSomeColumn);
        return true;
    }

    @Override
    public boolean updateBatch(List<T> list) {
        if (CollUtil.isEmpty(list)) {
            logger.warn("请求数据为空....");
            return false;
        }
        CollUtil.split(list, DEFAULT_BATCH_SIZE).forEach(baseMapper::updateBatchSomeColumnById);
        return true;
    }
}
