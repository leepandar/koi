package com.koi.suite.gen.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.gen.domain.entity.GenerateTable;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateTableMapper extends SuperMapper<GenerateTable> {

    @Select("select count(*) from generate_table where template_group_id = #{id}")
    int countByGroupId(Long id);
}
