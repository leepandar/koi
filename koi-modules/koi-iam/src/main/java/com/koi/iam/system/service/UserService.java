package com.koi.iam.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.common.security.domain.UserInfoDetails;
import com.koi.iam.auth.support.domain.UserTenantAuthentication;
import com.koi.iam.base.domain.dto.req.ChangeUserInfoReq;
import com.koi.iam.system.domain.dto.req.UserOnlinePageReq;
import com.koi.iam.system.domain.dto.req.UserPageReq;
import com.koi.iam.system.domain.dto.req.UserSaveReq;
import com.koi.iam.system.domain.dto.req.UserUpdateReq;
import com.koi.iam.system.domain.dto.resp.UserResp;
import com.koi.iam.system.domain.entity.User;

/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 *
 * @author lida
 */
public interface UserService extends SuperService<User> {

    /**
     * 添加用户
     *
     * @param req 用户信息
     */
    void create(UserSaveReq req);

    /**
     * 修改用户信息
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, UserUpdateReq req);

    /**
     * 数据权限 分页
     *
     * @param req req
     * @return 查询结果
     */
    IPage<UserResp> pageList(UserPageReq req);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param orgPassword 原始密码
     * @param newPassword 新密码
     */
    void changePassword(Long userId, String orgPassword, String newPassword);

    /**
     * 根据ID删除用户
     *
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 修改用户信息
     *
     * @param req req
     */
    void changeInfo(ChangeUserInfoReq req);

    /**
     * 重置密码
     *
     * @param id ID
     */
    void resetPassword(Long id);

    /**
     * 用户信息
     *
     * @param userId 用户信息
     * @return 用户信息
     */
    UserInfoDetails userinfo(Long userId);

    UserInfoDetails userinfo(UserTenantAuthentication authentication);

    /**
     * 查询在线用户列表
     *
     * @param req req
     * @return 查询结果
     */
    IPage<Object> userOnlineList(UserOnlinePageReq req);

}
