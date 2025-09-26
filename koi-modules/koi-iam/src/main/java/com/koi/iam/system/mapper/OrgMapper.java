package com.koi.iam.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.system.domain.entity.Org;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgMapper extends SuperMapper<Org> {

    /**
     * 根据租户ID删除组织机构数据
     *
     * @param tenantId 租户ID
     */
    @InterceptorIgnore(tenantLine = "true")
    @Delete("delete from sys_org where tenant_id = #{tenantId}")
    void deleteByTenantId(@Param("tenantId") Long tenantId);
}
