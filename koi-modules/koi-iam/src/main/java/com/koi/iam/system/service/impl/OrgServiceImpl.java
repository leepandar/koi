package com.koi.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.entity.Entity;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.domain.dto.req.OrgSaveReq;
import com.koi.iam.system.domain.entity.Org;
import com.koi.iam.system.mapper.OrgMapper;
import com.koi.iam.system.service.OrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrgServiceImpl extends SuperServiceImpl<OrgMapper, Org> implements OrgService {

    /**
     * 批量删除以及删除其子节点
     *
     * @param id id
     */
    @Override
    public void remove(Long id) {
        final Long count = this.baseMapper.selectCount(Org::getParentId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("当前组织下还存在子节点,请先移除子节点");
        }
        this.baseMapper.deleteById(id);
    }

    /**
     * 添加组织
     *
     * @param req req
     */
    @Override
    public void create(OrgSaveReq req) {
        final Org bean = BeanUtil.toBean(req, Org.class);
        bean.setTreePath(buildNewTreePath(req.getParentId()));
        this.baseMapper.insert(bean);
    }

    /**
     * 获取当前机构本级及子级的ID
     *
     * @param id id
     * @return
     */
    @Override
    public List<Long> getFullTreeIdPath(Long id) {
        if (id == null) {
            return null;
        }
        final Org org = this.baseMapper.selectById(id);
        if (org == null) {
            return null;
        }
        List<Long> treePath = org.getTreePath();
        treePath.add(org.getId());
        final List<Long> list = this.baseMapper.selectList(Wraps.<Org>lbQ()
                        .likeRight(Org::getTreePath, StrUtil.join(StrUtil.COMMA, treePath)))
                .stream()
                .map(Entity::getId)
                .distinct()
                .collect(toList());
        list.add(org.getId());
        return list;
    }

    private List<Long> buildNewTreePath(Long id) {
        final Org org = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("父节点不存在"));
        final List<Long> treePath = org.getTreePath();
        treePath.add(org.getId());
        return treePath;
    }

}
