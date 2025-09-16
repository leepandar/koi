package com.koi.suite.online.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.suite.online.domain.entity.OnlineFormData;
import com.koi.suite.online.domain.req.OnlineFormDataSaveReq;
import com.koi.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.koi.suite.online.mapper.OnlineFormDataMapper;
import com.koi.suite.online.service.OnlineFormDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 表单数据服务实现类
 *
 * @author lida
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineFormDataServiceImpl extends SuperServiceImpl<OnlineFormDataMapper, OnlineFormData> implements OnlineFormDataService {

    /**
     * 分页查询
     *
     * @param req req
     * @return
     */
    @Override
    public IPage<JSONObject> pageList(OnlineFormDesignerPageReq req) {
        return this.baseMapper.pageList(req.buildPage(), req).convert(x -> new JSONObject() {

            {
                put("id", x.getId());
                put("definitionKey", x.getDefinitionKey());
                put("tenantId", x.getTenantId());
                put("createdName", x.getCreatedName());
                put("createdTime", x.getCreatedTime());
                putAll(x.getFormData());
            }
        });
    }

    /**
     * 新增
     *
     * @param req req
     */
    @Override
    public void created(OnlineFormDataSaveReq req) {
        var bean = BeanUtilPlus.toBean(req, OnlineFormData.class);
        this.baseMapper.insert(bean);
    }

    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    @Override
    public void modify(Long id, OnlineFormDataSaveReq req) {
        var bean = BeanUtilPlus.toBean(id, req, OnlineFormData.class);
        this.baseMapper.updateById(bean);
    }
}
