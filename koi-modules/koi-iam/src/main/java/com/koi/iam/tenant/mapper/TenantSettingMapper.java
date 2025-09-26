package com.koi.iam.tenant.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.tenant.domain.entity.TenantSetting;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantSettingMapper extends SuperMapper<TenantSetting> {
}
