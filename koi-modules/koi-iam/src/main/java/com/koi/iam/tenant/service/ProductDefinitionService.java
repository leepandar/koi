package com.koi.iam.tenant.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.tenant.domain.entity.ProductDefinition;
import com.koi.iam.system.domain.dto.resp.RolePermissionResp;
import com.koi.iam.tenant.domain.dto.req.ProductDefPermissionReq;
import com.koi.iam.tenant.domain.dto.req.ProductDefinitionSaveReq;

public interface ProductDefinitionService extends SuperService<ProductDefinition> {

    /**
     * 创建产品定义
     *
     * @param req req
     */
    void create(ProductDefinitionSaveReq req);

    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, ProductDefinitionSaveReq req);

    /**
     * 产品授予权限
     *
     * @param productId productId
     * @param req       req
     */
    void permissions(Long productId, ProductDefPermissionReq req);

    /**
     * 查询套餐权限
     *
     * @param id id
     * @return 查询权限
     */
    RolePermissionResp findPermissions(Long id);

    /**
     * 删除产品
     *
     * @param id id
     */
    void delete(Long id);

}
