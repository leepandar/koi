package com.koi.common.log.service.impl;

import com.alibaba.fastjson2.JSON;
import com.koi.common.log.domain.req.DiffLogInfoQueryReq;
import com.koi.common.log.domain.DiffLogInfo;
import com.koi.common.log.domain.req.DiffLogInfoQueryReq;
import com.koi.common.log.service.IDiffLogService;
import com.koi.common.log.domain.DiffLogInfo;
import com.koi.common.log.domain.req.DiffLogInfoQueryReq;
import com.koi.common.log.service.IDiffLogService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lida
 */
@Slf4j
public class DefaultDiffLogServiceImpl implements IDiffLogService {

    @Override
    public void handler(DiffLogInfo diffLogInfo) {
        log.info("[default diff log] {}", JSON.toJSONString(diffLogInfo));
    }

    @Override
    public List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req) {
        return null;
    }


}
