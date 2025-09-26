package com.koi.iam.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.system.domain.dto.resp.PositionPageResp;
import com.koi.iam.system.domain.entity.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionMapper extends SuperMapper<Position> {

    /**
     * 分页查询岗位信息（含角色）
     *
     * @param page    page
     * @param wrapper wrapper
     * @return 查询结果
     */
    IPage<PositionPageResp> findStationPage(IPage<?> page, @Param(Constants.WRAPPER) Wrapper<Position> wrapper);

}
