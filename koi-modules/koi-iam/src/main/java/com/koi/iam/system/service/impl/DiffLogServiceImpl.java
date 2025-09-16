package com.koi.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.log.domain.DiffLogInfo;
import com.koi.common.log.domain.req.DiffLogInfoQueryReq;
import com.koi.common.log.service.IDiffLogService;
import com.koi.iam.base.domain.entity.DiffLogEntity;
import com.koi.iam.base.mapper.DiffLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lida
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DiffLogServiceImpl implements IDiffLogService {

    private final DiffLogMapper diffLogMapper;

    @Override
    public void handler(DiffLogInfo logInfo) {
        log.info("[diff log] {}", JSON.toJSONString(logInfo));
        DiffLogEntity entity = BeanUtil.toBean(logInfo, DiffLogEntity.class);
        this.diffLogMapper.insert(entity);
    }

    @Override
    public List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req) {
        return this.diffLogMapper.selectObjs(Wraps.<DiffLogEntity>lbQ()
                .eq(DiffLogEntity::getBusinessGroup, req.getBusinessGroup())
                .eq(DiffLogEntity::getBusinessTag, req.getBusinessTag())
                .eq(DiffLogEntity::getBusinessKey, req.getBusinessKey()));
    }

}
