package com.koi.suite.gen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.suite.gen.domain.dto.req.GenerateTemplateGroupSaveReq;
import com.koi.suite.gen.domain.entity.GenerateTemplateGroup;
import com.koi.suite.gen.domain.entity.TemplateGroupRelation;
import com.koi.suite.gen.domain.dto.rep.GenerateTemplateGroupPageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import com.koi.suite.gen.mapper.GenerateTableMapper;
import com.koi.suite.gen.mapper.GenerateTemplateGroupMapper;
import com.koi.suite.gen.mapper.TemplateGroupRelationMapper;
import com.koi.suite.gen.service.GenerateTemplateGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 代码生成模板组实现类
 *
 * @author lida
 */
@RequiredArgsConstructor
@Service
@Transactional
public class GenerateTemplateGroupServiceImpl extends SuperServiceImpl<GenerateTemplateGroupMapper, GenerateTemplateGroup> implements GenerateTemplateGroupService {

    private final TemplateGroupRelationMapper templateGroupRelationMapper;

    private final GenerateTableMapper generateTableMapper;

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    @Override
    public IPage<GenerateTemplateGroupPageRep> pageList(GenerateTemplateGroupPageReq req) {
        return this.baseMapper.selectPageWithTemplateIds(req.buildPage(), req);
    }

    /**
     * 新增
     *
     * @param req
     */
    @Override
    public void create(GenerateTemplateGroupSaveReq req) {
        GenerateTemplateGroup generateTemplateGroup = BeanUtil.toBean(req, GenerateTemplateGroup.class);
        this.baseMapper.insert(generateTemplateGroup);
        if (req.getIsDefault()) {
            this.baseMapper.updateDefaultGroup();
        }
        saveTemplateGroupRelations(generateTemplateGroup.getId(), req.getTemplateIds());
    }

    /**
     * 修改
     *
     * @param id
     * @param req
     */
    @Override
    public void modify(Long id, GenerateTemplateGroupSaveReq req) {
        GenerateTemplateGroup generateTemplateGroup = BeanUtil.toBean(req, GenerateTemplateGroup.class);
        generateTemplateGroup.setId(id);
        if (req.getIsDefault()) {
            updateDefaultGroup(id);
        }
        this.baseMapper.updateById(generateTemplateGroup);
        templateGroupRelationMapper.deleteByGroupId(id);
        saveTemplateGroupRelations(id, req.getTemplateIds());
    }

    /**
     * 删除组
     *
     * @param id
     */
    @Override
    public void removeGroup(Long id) {
        //检查是否有模板组在使用中
        int i = generateTableMapper.countByGroupId(id);
        if (i > 0) {
            throw new CheckedException("该模板组下有生成配置在使用中，无法删除");
        }
        this.baseMapper.deleteById(id);
        templateGroupRelationMapper.deleteByGroupId(id);
    }

    /**
     * 修改默认模板组
     *
     * @param id
     */
    public void updateDefaultGroup(Long id) {
        this.baseMapper.updateDefaultGroup();
        this.baseMapper.updateDefaultGroupById(id);
    }

    /**
     * 保存模板组关联
     *
     * @param groupId
     * @param templateIds
     */
    private void saveTemplateGroupRelations(Long groupId, List<Long> templateIds) {
        List<TemplateGroupRelation> relations = templateIds.stream().map(templateId -> {
            TemplateGroupRelation relation = new TemplateGroupRelation();
            relation.setTemplateId(templateId);
            relation.setGroupId(groupId);
            return relation;
        }).collect(Collectors.toList());
        templateGroupRelationMapper.insertBatch(relations);
    }
}