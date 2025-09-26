package com.koi.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.utils.TenantHelper;
import com.koi.common.springboot.log.AccessLogInfo;
import com.koi.iam.base.mapper.OptLogMapper;
import com.koi.iam.base.domain.entity.OptLog;
import com.koi.iam.base.service.OptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {

    private final OptLogMapper optLogMapper;

    /**
     * 保存操作日志
     *
     * @param info logInfo
     */
    @Override
    public void listener(AccessLogInfo info) {
        TenantHelper.executeWithTenantDb(info.getTenantCode(), () -> {
            log.debug("[日志信息] - {}", JSON.toJSONString(info));
            return this.optLogMapper.insert(BeanUtil.toBean(info, OptLog.class));
        });
    }

}
