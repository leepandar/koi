package com.koi.iam.base.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.base.domain.entity.AreaEntity;
import com.koi.iam.base.domain.entity.AreaEntity;

import java.util.List;

/**
 * @author lida
 */
public interface AreaService extends SuperService<AreaEntity> {

    /**
     * 根据 parentId 查询数据集
     *
     * @param parentId parentId
     * @return 查询结果
     */
    List<AreaEntity> listArea(Integer parentId);

    /**
     * 保存或者修改地区
     *
     * @param area area
     */
    void saveOrUpdateArea(AreaEntity area);

}
