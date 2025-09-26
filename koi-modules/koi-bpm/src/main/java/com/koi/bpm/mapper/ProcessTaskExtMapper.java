package com.koi.bpm.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.bpm.domain.entity.ProcessTaskExt;
import com.koi.bpm.domain.req.ProcessTaskPageReq;
import com.koi.bpm.domain.resp.ProcessTaskExtResp;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessTaskExtMapper extends SuperMapper<ProcessTaskExt> {

    /**
     * 查看任务列表
     *
     * @param page 分页对象
     * @param req  req
     * @return 分页结果
     */
    Page<ProcessTaskExtResp> pageList(@Param("page") Page<ProcessTaskExt> page, @Param("req") ProcessTaskPageReq req);
}
