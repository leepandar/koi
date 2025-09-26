package com.koi.iam.system.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.auth.domain.entity.UserThirdAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdAccountMapper extends SuperMapper<UserThirdAccount> {
}
