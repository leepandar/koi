package com.koi.bpm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.bpm.domain.entity.ProcessModel;
import com.koi.bpm.domain.req.ProcessModelPageReq;
import com.koi.bpm.domain.resp.ProcessModelPageResp;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProcessModelMapper extends SuperMapper<ProcessModel> {

    /**
     * 分页查询
     *
     * @param req  ${@link ProcessModelPageReq} 查询条件
     * @param page ${@link Page< ProcessModelPageResp >} 分页信息
     * @return IPage<DesignModelPageDto> ${@link IPage< ProcessModelPageResp >} 结果
     */
    Page<ProcessModelPageResp> pageList(@Param("page") Page<ProcessModelPageResp> page, @Param("req") ProcessModelPageReq req);


    /**
     * 通过模型id物理删除
     *
     * @param modelId ${@link String} 模型id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     */
    int physicalDeleteById(@Param("id") String modelId);

    /**
     * 获取某个模型数量，排除指定模型id的数量
     *
     * @param modelId              需要排除的模型id
     * @param processDefinitionKey 流程定义key
     * @return 数量
     */
    int getModelNum(@Param("id") Long modelId, @Param("key") String processDefinitionKey);


    /**
     * 通过模型id物理批量删除
     *
     * @param idList ${@link Collection} 待删除id
     * @return int ${@link Integer} 成功状态:0-失败,大于1-成功
     */
    int physicalDeleteBatchIds(@Param("coll") Collection<String> idList);

}
