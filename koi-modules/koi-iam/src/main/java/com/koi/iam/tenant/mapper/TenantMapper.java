package com.koi.iam.tenant.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.tenant.domain.entity.Tenant;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantMapper extends SuperMapper<Tenant> {
}
