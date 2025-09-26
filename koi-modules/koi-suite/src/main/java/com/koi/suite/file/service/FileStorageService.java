package com.koi.suite.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.file.domain.dto.req.FileStoragePageReq;
import com.koi.suite.file.domain.dto.resp.FileStoragePageResp;
import com.koi.suite.file.domain.entity.FileStorage;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService extends SuperService<FileStorage> {

    /**
     * 文件上传
     *
     * @param file file
     * @return FileStorage
     */
    FileStorage upload(MultipartFile file);

    /**
     * 图片上传
     *
     * @param file 文件
     * @return 上传结果
     */
    FileStorage uploadImage(MultipartFile file);

    /**
     * 文件删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 重命名
     *
     * @param id
     * @param originName
     */
    void rename(Long id, String originName);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    IPage<FileStoragePageResp> pageList(FileStoragePageReq req);

}
