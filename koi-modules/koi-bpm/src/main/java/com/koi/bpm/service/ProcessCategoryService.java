package com.koi.bpm.service;

import com.koi.bpm.domain.entity.ProcessCategory;
import com.koi.bpm.domain.req.ProcessCategorySaveReq;
import com.koi.common.db.mybatisplus.ext.SuperService;

public interface ProcessCategoryService extends SuperService<ProcessCategory> {

    /**
     * 保存
     *
     * @param vo ${@link ProcessCategorySaveReq} 流程类别保存
     */
    void create(ProcessCategorySaveReq vo);

    /**
     * 通过id更新
     *
     * @param req ${@link ProcessCategorySaveReq} 流程类别更新
     * @param id  ${@link String} 类别id
     */
    void modify(Long id, ProcessCategorySaveReq req);
}
