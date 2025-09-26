package com.koi.iam.base.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.base.domain.entity.DiffLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DiffLogMapper extends SuperMapper<DiffLogEntity> {
}
