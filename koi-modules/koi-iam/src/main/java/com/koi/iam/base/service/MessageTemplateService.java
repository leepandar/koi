package com.koi.iam.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.koi.iam.base.domain.dto.req.MessageTemplatePageReq;
import com.koi.iam.base.domain.dto.req.MessageTemplateSaveReq;
import com.koi.iam.base.domain.dto.resp.MessageTemplateDetailResp;
import com.koi.iam.base.domain.dto.resp.MessageTemplatePageResp;
import com.koi.iam.base.domain.entity.MessageTemplate;
import com.koi.iam.base.domain.entity.MessageTemplate;

/**
 * @author lida
 */
public interface MessageTemplateService extends IService<MessageTemplate> {

    /**
     * 分页列表
     *
     * @param req req
     * @return 查询结果
     */
    IPage<MessageTemplatePageResp> pageList(MessageTemplatePageReq req);

    /**
     * 创建模板
     *
     * @param req req
     */
    void create(MessageTemplateSaveReq req);

    /**
     * 修改模板
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, MessageTemplateSaveReq req);

    /**
     * 模板详情
     *
     * @param id id
     * @return 查询结果
     */
    MessageTemplateDetailResp detail(Long id);
}
