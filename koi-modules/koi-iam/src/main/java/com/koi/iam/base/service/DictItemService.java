package com.koi.iam.base.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.base.domain.dto.req.DictItemSaveReq;
import com.koi.iam.base.domain.entity.SysDictItem;
import com.koi.iam.base.domain.entity.SysDictItem;

/**
 * <p>
 * 业务接口
 * 字典项
 * </p>
 *
 * @author lida
 */
public interface DictItemService extends SuperService<SysDictItem> {

    /**
     * 添加字典项
     *
     * @param dictId 字典ID
     * @param req    req
     */
    void create(Long dictId, DictItemSaveReq req);

    /**
     * 修改字典项
     *
     * @param dictId 字典ID
     * @param itemId 字典项ID
     * @param req    req
     */
    void modify(Long dictId, Long itemId, DictItemSaveReq req);
}
