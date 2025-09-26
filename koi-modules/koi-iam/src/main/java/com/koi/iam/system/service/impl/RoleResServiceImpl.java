package com.koi.iam.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.mapper.RoleResMapper;
import com.koi.iam.system.mapper.UserRoleMapper;
import com.koi.iam.system.domain.dto.req.RoleResSaveReq;
import com.koi.iam.system.domain.dto.req.UserRoleSaveReq;
import com.koi.iam.system.domain.entity.RoleRes;
import com.koi.iam.system.domain.entity.UserRole;
import com.koi.iam.system.service.RoleResService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleResServiceImpl extends SuperServiceImpl<RoleResMapper, RoleRes> implements RoleResService {

    private final UserRoleMapper userRoleMapper;

    /**
     * 给用户分配角色
     *
     * @param req req
     * @return
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public boolean assignUser(UserRoleSaveReq req) {
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, req.getRoleId()));
        List<UserRole> list = req.getUserIdList().stream()
                .map(userId -> UserRole.builder().userId(userId).roleId(req.getRoleId()).build()).toList();
        userRoleMapper.insertBatchSomeColumn(list);
        return true;
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void assignResource(RoleResSaveReq req) {
        // 删除角色和资源的关联
        super.remove(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, req.getRoleId()));
        resHandler(req, req.getRoleId());
    }

    /**
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param data
     * @param roleId
     */
    private void resHandler(RoleResSaveReq data, Long roleId) {
        final Set<Long> resIdList = data.getResIdList();
        if (CollUtil.isEmpty(resIdList)) {
            return;
        }
        final List<RoleRes> list = resIdList.stream()
                .filter(Objects::nonNull)
                .map(resId -> RoleRes.builder().resId(resId).roleId(roleId).build())
                .toList();
        this.baseMapper.insertBatchSomeColumn(list);
    }
}
