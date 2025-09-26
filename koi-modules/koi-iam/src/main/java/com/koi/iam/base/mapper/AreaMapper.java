package com.koi.iam.base.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.base.domain.entity.AreaEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper extends SuperMapper<AreaEntity> {

    /**
     * 根据 parentId 查询数据集
     *
     * @param parentId parentId
     * @return 查询结果
     */
    List<AreaEntity> listArea(@Param("parentId") Integer parentId);
}
