package com.koi.iam.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.koi.common.db.mybatisplus.datascope.annotation.DataColumn;
import com.koi.common.db.mybatisplus.datascope.annotation.DataScope;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.system.domain.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends SuperMapper<Role> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Role> findRoleByUserId(Long userId);

    /**
     * 根据范围查询角色
     *
     * @return 查询结果
     */
    @DataScope(columns = @DataColumn)
    List<Role> list();

    /**
     * 根据租户ID删除
     *
     * @param tenantId 租户ID
     */
    @InterceptorIgnore(tenantLine = "true")
    @Delete("delete from sys_role where tenant_id = #{tenantId}")
    void deleteByTenantId(@Param("tenantId") Long tenantId);
}
