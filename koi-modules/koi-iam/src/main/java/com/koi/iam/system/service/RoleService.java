package com.koi.iam.system.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.system.domain.dto.req.RoleSaveReq;
import com.koi.iam.system.domain.dto.resp.RolePermissionResp;
import com.koi.iam.system.domain.entity.Role;

import java.util.List;

public interface RoleService extends SuperService<Role> {

    /**
     * 根据 scope 查询角色
     *
     * @return 查询结果
     */
    @Override
    List<Role> list();

    /**
     * 根据角色ID移除
     *
     * @param roleId roleId
     */
    void removeByRoleId(Long roleId);

    /**
     * 保存角色
     *
     * @param req req
     */
    void create(RoleSaveReq req);

    /**
     * 修改角色
     *
     * @param roleId 角色ID
     * @param req    req
     */
    void modify(Long roleId, RoleSaveReq req);

    /**
     * 给角色分配用户
     *
     * @param roleId     roleId
     * @param userIdList userIdList
     */
    void assignUser(Long roleId, List<Long> userIdList);

    /**
     * 根据角色ID查询资源码
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    RolePermissionResp findRolePermissionById(Long roleId);
}
