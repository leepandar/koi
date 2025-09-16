package com.koi.suite.gen.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.gen.domain.entity.TemplateGroupRelation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * 代码生成模板组
 *
 * @author lida
 */
@Repository
public interface TemplateGroupRelationMapper extends SuperMapper<TemplateGroupRelation> {

    /**
     * 根据组id删除
     *
     * @param id
     */
    @Delete("delete from c_generate_template_group_relation where group_id = #{id}")
    void deleteByGroupId(Long id);

    /**
     * 根据模版id删除
     *
     * @param id
     */
    @Delete("delete from c_generate_template_group_relation where template_id = #{id}")
    void deleteTemplateId(Long id);
}
