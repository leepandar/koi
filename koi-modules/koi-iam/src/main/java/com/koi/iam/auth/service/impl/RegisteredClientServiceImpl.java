package com.koi.iam.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.auth.domain.entity.RegisteredClient;
import com.koi.iam.auth.service.RegisteredClientService;
import com.koi.iam.system.domain.dto.req.RegisteredClientReq;
import com.koi.iam.system.mapper.RegisteredClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lida
 */
@Service
@RequiredArgsConstructor
public class RegisteredClientServiceImpl extends SuperServiceImpl<RegisteredClientMapper, RegisteredClient> implements RegisteredClientService {

    @Override
    public void create(RegisteredClientReq req) {
        baseMapper.existsCallback(RegisteredClient::getClientId, req.getClientId(), () -> CheckedException.badRequest("终端已存在,注册失败"));
        var bean = BeanUtilPlus.toBean(req, RegisteredClient.class);
        if (CollUtil.isNotEmpty(req.getGrantTypes())) {
            bean.setGrantTypes(CollUtil.join(req.getGrantTypes(), ","));
        }
        if (Objects.nonNull(req.getClientSettings())) {
            bean.setClientSettings(JSON.toJSONString(req.getClientSettings()));
        }
        if (Objects.nonNull(req.getTokenSettings())) {
            bean.setTokenSettings(JSON.toJSONString(req.getTokenSettings()));
        }
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(Long id, RegisteredClientReq req) {
        Long count = baseMapper.selectCount(Wraps.<RegisteredClient>lbQ()
                .ne(RegisteredClient::getId, id).eq(RegisteredClient::getClientId, req.getClientId()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("终端已存在,修改失败");
        }
        var bean = BeanUtilPlus.toBean(id, req, RegisteredClient.class);
        if (CollUtil.isNotEmpty(req.getGrantTypes())) {
            bean.setGrantTypes(CollUtil.join(req.getGrantTypes(), ","));
        }
        if (Objects.nonNull(req.getClientSettings())) {
            bean.setClientSettings(JSON.toJSONString(req.getClientSettings()));
        }
        if (Objects.nonNull(req.getTokenSettings())) {
            bean.setTokenSettings(JSON.toJSONString(req.getTokenSettings()));
        }
        this.baseMapper.updateById(bean);
    }

    @Override
    public void deleteById(String id) {
        this.baseMapper.removeById(id);
    }
}
