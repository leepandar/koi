package com.koi.iam.system.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.system.domain.entity.UserRole;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author lida
 */

@Repository
public interface UserRoleMapper extends SuperMapper<UserRole> {

}
