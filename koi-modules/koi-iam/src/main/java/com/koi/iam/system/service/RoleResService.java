package com.koi.iam.system.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.system.domain.dto.req.RoleResSaveReq;
import com.koi.iam.system.domain.dto.req.UserRoleSaveReq;
import com.koi.iam.system.domain.entity.RoleRes;

/**
 * <p>
 * 业务接口
 * 角色的资源
 * </p>
 *
 * @author lida
 */
public interface RoleResService extends SuperService<RoleRes> {

    /**
     * 给用户分配角色
     *
     * @param req req
     * @return 保存结果
     */
    boolean assignUser(UserRoleSaveReq req);

    /**
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param req req
     */
    void assignResource(RoleResSaveReq req);
}
