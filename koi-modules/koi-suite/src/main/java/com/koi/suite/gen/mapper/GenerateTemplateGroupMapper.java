package com.koi.suite.gen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.gen.domain.entity.GenerateTemplateGroup;
import com.koi.suite.gen.domain.dto.resp.GenerateTemplateGroupPageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateTemplateGroupMapper extends SuperMapper<GenerateTemplateGroup> {

    @Select("SELECT g.*, GROUP_CONCAT(r.template_id) AS templateIds " +
            "FROM c_generate_template_group g " +
            "LEFT JOIN c_generate_template_group_relation r ON g.id = r.group_id " +
            "WHERE (#{req.name} IS NULL OR g.name LIKE CONCAT('%', #{req.name}, '%')) " +
            "GROUP BY g.id")
    IPage<GenerateTemplateGroupPageRep> selectPageWithTemplateIds(Page<GenerateTemplateGroup> page, @Param("req") GenerateTemplateGroupPageReq req);


    /**
     * 更新默认分组
     */
    @Update("update c_generate_template_group set is_default = 0 where is_default = 1")
    void updateDefaultGroup();


    /**
     * 更新默认分组
     */
    @Update("update c_generate_template_group set is_default = 1 where is_default = #{id}")
    void updateDefaultGroupById(Long id);
}
