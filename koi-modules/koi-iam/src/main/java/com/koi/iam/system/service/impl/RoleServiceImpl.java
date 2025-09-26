package com.koi.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.DataResourceType;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.domain.dto.req.RoleSaveReq;
import com.koi.iam.system.domain.dto.resp.RolePermissionResp;
import com.koi.iam.system.domain.entity.*;
import com.koi.iam.system.domain.enums.ResourceType;
import com.koi.iam.system.service.RoleService;
import com.koi.iam.system.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleResMapper roleResMapper;
    private final DataPermissionResourceMapper dataPermissionResourceMapper;
    private final UserRoleMapper userRoleMapper;
    private final ResourceMapper resourceMapper;

    /**
     * 列表
     *
     * @return
     */
    @Override
    public List<Role> list() {
        return baseMapper.list();
    }

    /**
     * 根据角色ID移除
     *
     * @param roleId roleId
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void removeByRoleId(Long roleId) {
        final Role role = Optional.ofNullable(baseMapper.selectById(roleId)).orElseThrow(() -> CheckedException.notFound("角色不存在"));
        if (role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法删除");
        }
        if (role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法删除");
        }
        baseMapper.deleteById(roleId);
        dataPermissionResourceMapper.delete(Wraps.<DataPermissionResource>lbQ()
                .eq(DataPermissionResource::getOwnerId, roleId)
                .eq(DataPermissionResource::getOwnerType, DataResourceType.ROLE));
        roleResMapper.delete(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId));
        userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
    }

    /**
     * 保存角色
     *
     * @param req req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(RoleSaveReq req) {
        Role role = BeanUtil.toBean(req, Role.class);
        role.setReadonly(false);
        super.save(role);
        addDataPermission(role.getId(), req.getOrgList());
    }

    /**
     * 修改角色
     *
     * @param roleId 角色ID
     * @param req    req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long roleId, RoleSaveReq req) {
        Role role = Optional.ofNullable(this.baseMapper.selectById(roleId)).orElseThrow(() -> CheckedException.notFound("角色不存在"));
        if (role.getReadonly() != null && role.getReadonly()) {
            throw CheckedException.badRequest("内置角色无法编辑");
        }
        if (role.getSuperRole() != null && role.getSuperRole()) {
            throw CheckedException.badRequest("超级角色无法编辑");
        }
        final long count = this.baseMapper.selectCount(Wraps.<Role>lbQ().ne(Role::getId, roleId).eq(Role::getCode, req.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("角色编码已存在");
        }
        var bean = BeanUtilPlus.toBean(roleId, req, Role.class);
        this.baseMapper.updateById(bean);
        addDataPermission(role.getId(), req.getOrgList());
    }

    /**
     * 给角色分配用户
     *
     * @param roleId     roleId
     * @param userIdList userIdList
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void assignUser(Long roleId, List<Long> userIdList) {
        this.userRoleMapper.delete(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, roleId));
        if (CollUtil.isEmpty(userIdList)) {
            return;
        }
        final List<UserRole> userRoles = userIdList.stream()
                .map(userId -> UserRole.builder().roleId(roleId).userId(userId).build())
                .toList();
        this.userRoleMapper.insertBatchSomeColumn(userRoles);
    }

    /**
     * 根据角色ID查询资源码
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public RolePermissionResp findRolePermissionById(Long roleId) {
        final List<Resource> resourceList = resourceMapper.selectList();
        if (CollUtil.isEmpty(resourceList)) {
            return null;
        }
        List<Long> resIdList = this.roleResMapper.selectList(RoleRes::getRoleId, roleId)
                .stream().map(RoleRes::getResId).distinct().toList();
        List<Long> buttonIdList = resourceList.stream()
                .filter(x -> x.getType() == ResourceType.BUTTON)
                .filter(x -> resIdList.contains(x.getId()))
                .map(Resource::getId)
                .toList();
        List<Long> menuIdList = resourceList.stream()
                .filter(x -> x.getType() != ResourceType.BUTTON)
                .filter(x -> resIdList.contains(x.getId()))
                .map(Resource::getId)
                .toList();
        return RolePermissionResp.builder().menuIdList(menuIdList).buttonIdList(buttonIdList).build();
    }

    private void addDataPermission(Long roleId, List<Long> orgList) {
        dataPermissionResourceMapper.delete(Wraps.<DataPermissionResource>lbQ()
                .eq(DataPermissionResource::getOwnerId, roleId)
                .eq(DataPermissionResource::getOwnerType, DataResourceType.ROLE)
                .eq(DataPermissionResource::getDataType, DataResourceType.ORG));
        if (CollectionUtil.isEmpty(orgList)) {
            return;
        }
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        List<DataPermissionResource> list = orgList.stream()
                .map(orgId -> DataPermissionResource.builder().dataId(orgId)
                        .dataType(DataResourceType.ORG)
                        .ownerType(DataResourceType.ROLE)
                        .ownerId(roleId).build())
                .collect(toList());
        dataPermissionResourceMapper.insertBatchSomeColumn(list);
    }
}
