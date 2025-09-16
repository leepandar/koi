package com.koi.iam.tenant.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.tenant.domain.entity.ProductSubscription;
import com.koi.iam.tenant.domain.dto.req.ProductSubscriptionSaveReq;
import com.koi.iam.tenant.domain.dto.req.ProductSubscriptionSaveReq;
import com.koi.iam.tenant.domain.entity.ProductSubscription;

/**
 * @author lida
 */
public interface ProductSubscriptionService extends SuperService<ProductSubscription> {

    /**
     * 添加
     *
     * @param req req
     */
    void create(ProductSubscriptionSaveReq req);

    /**
     * 编辑
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, ProductSubscriptionSaveReq req);

}
