package com.koi.iam.tenant.service;

import com.koi.common.core.entity.Dict;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.tenant.domain.entity.TenantDict;
import com.koi.iam.tenant.domain.dto.req.TenantDictSaveReq;

import java.util.List;

public interface TenantDictService extends SuperService<TenantDict> {

    /**
     * 添加字典
     *
     * @param req 字典信息
     */
    void create(TenantDictSaveReq req);

    /**
     * 删除字典
     *
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 编辑字典
     *
     * @param id  id
     * @param req 字典信息
     */
    void modify(Long id, TenantDictSaveReq req);

    /**
     * 刷新缓存
     */
    void refresh();

    /**
     * 根据 code 查询
     *
     * @param code code
     * @return 查询结果
     */
    List<Dict<String>> findItemByCode(String code);

    /**
     * 增量同步租户字典
     */
    void incrSyncTenantDict(Long tenantId);
}
