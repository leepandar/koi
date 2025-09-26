package com.koi.suite.gen.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.gen.domain.entity.GenerateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerateTemplateMapper extends SuperMapper<GenerateTemplate> {
    List<GenerateTemplate> getTemplateDetailByGroupId(Long templateGroupId);
}
