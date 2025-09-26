package com.koi.bpm.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import org.springframework.stereotype.Repository;
import com.koi.bpm.domain.entity.ProcessCategory;

@Repository
public interface ProcessCategoryMapper extends SuperMapper<ProcessCategory> {

}