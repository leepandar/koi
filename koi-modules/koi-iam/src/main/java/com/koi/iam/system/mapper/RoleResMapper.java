package com.koi.iam.system.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.system.domain.entity.RoleRes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResMapper extends SuperMapper<RoleRes> {

    /**
     * 查询租户管理员资源
     *
     * @return 查询结果
     */
    List<Long> selectTenantAdminResIdList();
}
