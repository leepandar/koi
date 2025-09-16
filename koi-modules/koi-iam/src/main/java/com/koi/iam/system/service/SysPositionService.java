package com.koi.iam.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.system.domain.dto.req.PositionPageReq;
import com.koi.iam.system.domain.dto.req.PositionSaveReq;
import com.koi.iam.system.domain.dto.resp.PositionPageResp;
import com.koi.iam.system.domain.entity.Position;
import com.koi.iam.system.domain.entity.Position;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author lida
 */
public interface SysPositionService extends SuperService<Position> {

    /**
     * 按权限查询岗位的分页信息
     *
     * @param req req
     * @return Station
     */
    IPage<PositionPageResp> pageList(PositionPageReq req);

    /**
     * 添加岗位
     *
     * @param req req
     */
    void create(PositionSaveReq req);

    /**
     * 修改岗位数据
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, PositionSaveReq req);

}
