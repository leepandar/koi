package com.koi.suite.online.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.online.domain.entity.OnlineFormData;
import com.koi.suite.online.domain.req.OnlineFormDataSaveReq;
import com.koi.suite.online.domain.req.OnlineFormDesignerPageReq;

/**
 * 表单数据接口
 *
 * @author lida
 */
public interface OnlineFormDataService extends SuperService<OnlineFormData> {

    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<JSONObject> pageList(OnlineFormDesignerPageReq req);

    /**
     * 新增数据
     *
     * @param req req
     */
    void created(OnlineFormDataSaveReq req);

    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, OnlineFormDataSaveReq req);

}
