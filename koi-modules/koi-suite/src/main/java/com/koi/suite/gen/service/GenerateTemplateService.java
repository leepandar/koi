package com.koi.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.gen.domain.entity.GenerateTemplate;
import com.koi.suite.gen.domain.dto.resp.GenerateTemplateDetailRep;
import com.koi.suite.gen.domain.dto.resp.GenerateTemplatePageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTemplatePageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTemplateSaveReq;

import java.util.List;

public interface GenerateTemplateService extends SuperService<GenerateTemplate> {

    /**
     * 新增
     *
     * @param req
     */
    void create(GenerateTemplateSaveReq req);

    /**
     * 修改
     *
     * @param id
     * @param req
     */
    void modify(Long id, GenerateTemplateSaveReq req);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    GenerateTemplateDetailRep detail(Long id);

    /**
     * 删除
     *
     * @param id
     */
    void removeTemplate(Long id);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    IPage<GenerateTemplatePageRep> pageList(GenerateTemplatePageReq req);

    /**
     * 根据分组id查询模板信息
     *
     * @param templateGroupId
     * @return
     */
    List<GenerateTemplate> getTemplateDetailByGroupId(Long templateGroupId);
}
