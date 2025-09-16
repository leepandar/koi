package com.koi.common.log.service;

import com.koi.common.log.domain.DiffLogInfo;
import com.koi.common.log.domain.req.DiffLogInfoQueryReq;
import com.koi.common.log.domain.DiffLogInfo;
import com.koi.common.log.domain.req.DiffLogInfoQueryReq;

import java.util.List;

/**
 * 日志记录
 *
 * @author lida
 */
public interface IDiffLogService {
    /**
     * 日志信息回调
     *
     * @param logInfo 日志信息
     */
    void handler(DiffLogInfo logInfo);

    /**
     * 返回最多100条记录
     *
     * @param req req
     * @return 操作日志列表
     */
    List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req);
}
