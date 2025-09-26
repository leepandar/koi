package com.koi.iam.tenant.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.tenant.domain.entity.TenantDict;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantDictMapper extends SuperMapper<TenantDict> {

}
