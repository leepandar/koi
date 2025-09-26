package com.koi.iam.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.iam.tenant.domain.entity.ProductSubscription;
import com.koi.iam.tenant.mapper.ProductSubscriptionMapper;
import com.koi.iam.tenant.service.ProductSubscriptionService;
import com.koi.iam.tenant.domain.dto.req.ProductSubscriptionSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSubscriptionServiceImpl extends SuperServiceImpl<ProductSubscriptionMapper, ProductSubscription> implements ProductSubscriptionService {

    /**
     * 添加
     *
     * @param req req
     */
    @Override
    public void create(ProductSubscriptionSaveReq req) {
        this.baseMapper.insert(BeanUtil.toBean(req, ProductSubscription.class));
    }

    /**
     * 编辑
     *
     * @param id  id
     * @param req req
     */
    @Override
    public void modify(Long id, ProductSubscriptionSaveReq req) {
        throw CheckedException.badRequest("禁止编辑");
    }
}
