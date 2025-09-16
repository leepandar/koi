package com.koi.bpm.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import org.springframework.stereotype.Repository;
import com.koi.bpm.domain.entity.ProcessCategory;

/**
 * 流程类别(ProcessCategory)持久层
 *
 * @author lida
 */
@Repository
public interface ProcessCategoryMapper extends SuperMapper<ProcessCategory> {

}