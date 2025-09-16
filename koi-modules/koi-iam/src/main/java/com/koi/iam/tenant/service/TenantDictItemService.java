package com.koi.iam.tenant.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.tenant.domain.entity.TenantDictItem;
import com.koi.iam.base.domain.dto.req.DictItemSaveReq;

/**
 * <p>
 * 业务接口
 * 字典项
 * </p>
 *
 * @author lida
 */
public interface TenantDictItemService extends SuperService<TenantDictItem> {

    /**
     * 添加字典项
     *
     * @param req req
     */
    void create(DictItemSaveReq req);

    /**
     * 修改字典项
     *
     * @param itemId 字典项ID
     * @param req    req
     */
    void modify(Long itemId, DictItemSaveReq req);

    /**
     * 删除字典
     *
     * @param id id
     */
    void delete(Long id);
}
