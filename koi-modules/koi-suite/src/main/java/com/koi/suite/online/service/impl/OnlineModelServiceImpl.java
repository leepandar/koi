package com.koi.suite.online.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.suite.online.dialect.EpicDesignerField;
import com.koi.suite.online.domain.req.OnlineFormDesignSaveReq;
import com.koi.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.koi.suite.online.domain.req.OnlineFormDesignerSaveReq;
import com.koi.suite.online.domain.resp.OnlineFormDesignerDetailResp;
import com.koi.suite.online.domain.resp.OnlineFormDesignerPageResp;
import com.koi.suite.online.dialect.FastCrudDialect;
import com.koi.suite.online.domain.entity.OnlineModel;
import com.koi.suite.online.mapper.OnlineModelMapper;
import com.koi.suite.online.service.OnlineModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineModelServiceImpl extends SuperServiceImpl<OnlineModelMapper, OnlineModel> implements OnlineModelService {

    /**
     * 分页查询
     *
     * @param req req
     * @return
     */
    @Override
    public IPage<OnlineFormDesignerPageResp> pageList(OnlineFormDesignerPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<OnlineModel>lbQ()
                        .eq(OnlineModel::getDefinitionKey, req.getDefinitionKey())
                        .eq(OnlineModel::getTitle, req.getTitle()))
                .convert(x -> BeanUtilPlus.toBean(x, OnlineFormDesignerPageResp.class));
    }

    /**
     * 新增
     *
     * @param req req
     */
    @Override
    public void created(OnlineFormDesignerSaveReq req) {
        var bean = BeanUtilPlus.toBean(req, OnlineModel.class);
        this.baseMapper.insert(bean);

    }

    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    @Override
    public void modify(Long id, OnlineFormDesignerSaveReq req) {
        var bean = BeanUtilPlus.toBean(id, req, OnlineModel.class);
        this.baseMapper.updateById(bean);
    }

    /**
     * 详情
     *
     * @param id id
     * @return OnlineFormDesignerDetailResp
     */
    @Override
    public OnlineFormDesignerDetailResp detail(Long id) {
        var entity = this.baseMapper.selectById(id);
        var bean = BeanUtilPlus.toBean(entity, OnlineFormDesignerDetailResp.class);
        if (StrUtil.isNotBlank(entity.getFormSchemas())) {
            bean.setFormSchemas(JSONArray.parseArray(entity.getFormSchemas()));
        }
        return bean;
    }

    /**
     * 详情
     *
     * @param definitionKey definitionKey
     * @return
     */
    @Override
    public OnlineFormDesignerDetailResp detail(String definitionKey) {
        var entity = Optional.ofNullable(this.baseMapper.selectOne(OnlineModel::getDefinitionKey, definitionKey))
                .orElseThrow(() -> CheckedException.notFound("模型不存在"));
        var bean = BeanUtilPlus.toBean(entity, OnlineFormDesignerDetailResp.class);
        if (StrUtil.isNotBlank(entity.getFormSchemas())) {
            bean.setFormSchemas(JSONArray.parseArray(entity.getFormSchemas()));
        }
        return bean;
    }

    /**
     * 设计表单
     *
     * @param id  id
     * @param req req
     */
    @Override
    public void formDesign(Long id, OnlineFormDesignSaveReq req) {
        OnlineModel model = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("模型不存在"));
        if (model.getStatus() != null && !model.getStatus()) {
            throw CheckedException.badRequest("模型已禁用");
        }
        JSONObject fastCrud = FastCrudDialect.toFastCrud(req.getSchemas().toJavaList(EpicDesignerField.class));
        if (fastCrud == null) {
            throw CheckedException.notFound("未解析出 CRUD 模板");
        }
        this.baseMapper.updateById(OnlineModel.builder().id(id)
                .formScript(req.getScript())
                .formSchemas(req.getSchemas().toJSONString())
                .formCrudConfig(fastCrud.toJSONString())
                .build());
    }
}
