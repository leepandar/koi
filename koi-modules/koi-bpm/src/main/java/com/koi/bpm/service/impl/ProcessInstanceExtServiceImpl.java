package com.koi.bpm.service.impl;

import com.koi.bpm.domain.entity.ProcessInstanceExt;
import com.koi.bpm.mapper.ProcessInstanceExtMapper;
import com.koi.bpm.service.ProcessInstanceExtService;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 流程实例扩展信息表业务实现层
 *
 * @author lida
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstanceExtServiceImpl extends SuperServiceImpl<ProcessInstanceExtMapper, ProcessInstanceExt> implements ProcessInstanceExtService {
}
