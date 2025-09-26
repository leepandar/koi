package com.koi.iam.auth.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.auth.domain.entity.RegisteredClient;
import com.koi.iam.system.domain.dto.req.RegisteredClientReq;

public interface RegisteredClientService extends SuperService<RegisteredClient> {

    /**
     * 创建安全终端
     *
     * @param req req
     */
    void create(RegisteredClientReq req);

    /**
     * 修改安全终端
     *
     * @param req req
     */
    void modify(Long id, RegisteredClientReq req);

    /**
     * 根据ID删除
     *
     * @param id id
     */
    void deleteById(String id);
}
