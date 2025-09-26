package com.koi.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.db.mybatisplus.wrap.query.LbqWrapper;
import com.koi.iam.system.mapper.PositionMapper;
import com.koi.iam.system.service.OrgService;
import com.koi.iam.system.domain.dto.req.PositionPageReq;
import com.koi.iam.system.domain.dto.req.PositionSaveReq;
import com.koi.iam.system.domain.dto.resp.PositionPageResp;
import com.koi.iam.system.domain.entity.Position;
import com.koi.iam.system.service.SysPositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionServiceImpl extends SuperServiceImpl<PositionMapper, Position> implements SysPositionService {

    private final OrgService orgService;

    /**
     * 按权限查询岗位的分页信息
     *
     * @param req req
     * @return
     */
    @Override
    public IPage<PositionPageResp> pageList(PositionPageReq req) {
        final LbqWrapper<Position> wrapper = Wraps.<Position>lbQ()
                .like(Position::getTitle, req.getTitle())
                .eq(Position::getStatus, req.getStatus())
                .in(Position::getOrgId, orgService.getFullTreeIdPath(req.getOrgId()))
                .eq(Position::getDeleted, false)
                .orderByAsc(Position::getSequence);
        return baseMapper.selectPage(req.buildPage(), wrapper)
                .convert(x -> BeanUtil.toBean(x, PositionPageResp.class));
    }

    /**
     * 添加岗位
     *
     * @param req req
     */
    @Override
    public void create(PositionSaveReq req) {
        this.baseMapper.existsCallback(Position::getCode, req.getCode(), () -> CheckedException.badRequest("职位编码已存在"));
        var bean = BeanUtil.toBean(req, Position.class);
        this.baseMapper.insert(bean);
    }

    /**
     * 修改岗位数据
     *
     * @param id  id
     * @param req req
     */
    @Override
    public void modify(Long id, PositionSaveReq req) {
        Long count = baseMapper.selectCount(Wraps.<Position>lbQ()
                .ne(Position::getId, id).eq(Position::getCode, req.getCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("职位编码已存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, Position.class);
        this.baseMapper.updateById(bean);
    }

}
