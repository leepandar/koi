package com.koi.iam.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.base.domain.dto.req.I18nDataSaveReq;
import com.koi.iam.base.domain.dto.req.I18nPageReq;
import com.koi.iam.base.domain.dto.resp.I18nDataPageResp;
import com.koi.iam.base.domain.entity.I18nData;
import com.koi.iam.base.domain.entity.I18nData;

/**
 * @author lida
 */
public interface I18nDataService extends SuperService<I18nData> {

    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<I18nDataPageResp> pageList(I18nPageReq req);

    /**
     * 添加 i18n 数据
     *
     * @param req req
     */
    void add(I18nDataSaveReq req);

    /**
     * 编辑I18N数据
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, I18nDataSaveReq req);
}
