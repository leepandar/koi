package com.koi.common.core.security;

import java.util.List;

/**
 * 认证上下文接口
 *
 * @author lida
 */
public interface AuthenticationContext {

    /**
     * 租户编码
     *
     * @return 租户编码
     */
    default String tenantCode() {
        return null;
    }

    default Object getContext() {
        return null;
    }

    /**
     * 登录的客户端ID
     *
     * @return id
     */
    String clientId();

    /**
     * 租户ID
     *
     * @return id
     */
    Long tenantId();

    /**
     * 租户名称
     *
     * @return 租户名称
     */
    String tenantName();

    /**
     * 用户ID
     *
     * @return id
     */
    Long userId();

    /**
     * 名称
     *
     * @return 名称
     */
    String nickName();

    /**
     * 手机号
     *
     * @return 手机号
     */
    String mobile();

    /**
     * 匿名用户
     *
     * @return 是否为匿名
     */
    boolean anonymous();

    /**
     * 功能权限
     *
     * @return 功能权限
     */
    List<String> funcPermissionList();

    /**
     * 角色权限
     *
     * @return 角色权限
     */
    List<String> rolePermissionList();

    /**
     * 数据权限
     *
     * @return 数据权限范围
     */
    DataPermission dataPermission();
}
