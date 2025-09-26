package com.koi.suite.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.koi.suite.file.domain.dto.req.FileStorageSettingSaveReq;
import com.koi.suite.file.domain.dto.resp.FileStorageSettingPageResp;
import com.koi.suite.file.domain.entity.FileStorageSetting;

public interface FileStorageSettingService extends SuperService<FileStorageSetting> {

    /**
     * 添加存储配置
     *
     * @param req 存储配置保存对象，包含要添加的存储配置信息
     */
    void create(FileStorageSettingSaveReq req);

    /**
     * 删除配置
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 修改配置
     *
     * @param id
     * @param req
     */
    void modify(Long id, FileStorageSettingSaveReq req);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    IPage<FileStorageSettingPageResp> pageList(FileStorageSettingPageReq req);
}
