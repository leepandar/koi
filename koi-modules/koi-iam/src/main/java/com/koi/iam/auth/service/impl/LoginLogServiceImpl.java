package com.koi.iam.auth.service.impl;

import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.iam.auth.domain.entity.LoginLog;
import com.koi.iam.auth.mapper.LoginLogMapper;
import com.koi.iam.auth.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl extends SuperServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
