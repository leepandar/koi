package com.koi.bpm.mapper;

import com.koi.bpm.domain.entity.ProcessInstanceExt;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessInstanceExtMapper extends SuperMapper<ProcessInstanceExt> {
}
