package com.koi.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.MvelHelper;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.mapper.MessageTemplateMapper;
import com.koi.iam.base.domain.dto.req.MessageTemplatePageReq;
import com.koi.iam.base.domain.dto.req.MessageTemplateSaveReq;
import com.koi.iam.base.domain.dto.resp.MessageTemplateDetailResp;
import com.koi.iam.base.domain.dto.resp.MessageTemplatePageResp;
import com.koi.iam.base.domain.entity.MessageTemplate;
import com.koi.iam.base.service.MessageTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements MessageTemplateService {

    /**
     * 分页列表
     *
     * @param req req
     * @return
     */
    @Override
    public IPage<MessageTemplatePageResp> pageList(MessageTemplatePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<MessageTemplate>lbQ()
                        .eq(MessageTemplate::getType, req.getType())
                        .eq(MessageTemplate::getSubject, req.getSubject())
                        .eq(MessageTemplate::getName, req.getName()))
                .convert(x -> BeanUtil.toBean(x, MessageTemplatePageResp.class));
    }

    /**
     * 创建模板
     *
     * @param req req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(MessageTemplateSaveReq req) {
        MessageTemplate bean = BeanUtil.toBean(req, MessageTemplate.class);
        if (CollUtil.isNotEmpty(req.getType())) {
            bean.setType(StrUtil.join(",", req.getType()));
        }
        this.baseMapper.insert(bean);
    }

    /**
     * 修改模板
     *
     * @param id  id
     * @param req req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, MessageTemplateSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("模板不存在"));
        MessageTemplate bean = BeanUtilPlus.toBean(id, req, MessageTemplate.class);
        if (CollUtil.isNotEmpty(req.getType())) {
            bean.setType(StrUtil.join(",", req.getType()));
        }
        this.baseMapper.updateById(bean);
    }

    /**
     * 模板详情
     *
     * @param id id
     * @return
     */
    @Override
    public MessageTemplateDetailResp detail(Long id) {
        var template = Optional.ofNullable(baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("模板不存在"));
        var bean = BeanUtil.toBean(template, MessageTemplateDetailResp.class);
        if (StrUtil.isBlank(template.getContent())) {
            return bean;
        }
        List<String> variables = MvelHelper.getVariables(template.getContent());
        bean.setVariables(new HashSet<>(variables));
        return bean;
    }
}
