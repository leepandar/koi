package com.koi.iam.base.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.common.springboot.log.AccessLogInfo;
import com.koi.iam.base.domain.entity.OptLog;

public interface OptLogService extends SuperService<OptLog> {

    /**
     * 保存操作日志
     *
     * @param logInfo logInfo
     */
    void listener(AccessLogInfo logInfo);
}
