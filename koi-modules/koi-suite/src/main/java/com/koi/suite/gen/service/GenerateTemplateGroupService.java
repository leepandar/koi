package com.koi.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.gen.domain.dto.req.GenerateTemplateGroupSaveReq;
import com.koi.suite.gen.domain.entity.GenerateTemplateGroup;
import com.koi.suite.gen.domain.dto.rep.GenerateTemplateGroupPageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;

/**
 * 代码生成模板组接口
 *
 * @author lida
 */
public interface GenerateTemplateGroupService extends SuperService<GenerateTemplateGroup> {

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    IPage<GenerateTemplateGroupPageRep> pageList(GenerateTemplateGroupPageReq req);

    /**
     * 新增
     *
     * @param req
     */
    void create(GenerateTemplateGroupSaveReq req);

    /**
     * 修改
     *
     * @param id
     */
    void modify(Long id, GenerateTemplateGroupSaveReq req);

    /**
     * 删除
     *
     * @param id
     */
    void removeGroup(Long id);
}
