package com.koi.iam.system.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.system.domain.dto.resp.UserRoleResp;
import com.koi.iam.system.domain.entity.UserRole;

/**
 * <p>
 * 业务接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author lida
 */
public interface UserRoleService extends SuperService<UserRole> {

    /**
     * 根据劫色查询用户
     *
     * @param roleId 角色id
     * @return 查询结果
     */
    UserRoleResp findUserByRoleId(Long roleId);

}
