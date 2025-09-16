package com.koi.bpm.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.koi.bpm.domain.entity.ProcessTaskHistory;
import com.koi.bpm.domain.req.ProcessTaskPageReq;
import com.koi.bpm.domain.resp.ProcessTaskHistoryResp;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lida
 */
@Repository
public interface ProcessTaskHistoryMapper extends SuperMapper<ProcessTaskHistory> {

    /**
     * 分页查询
     *
     * @param page page
     * @param req  req
     * @return 查询结果
     */
    Page<ProcessTaskHistoryResp> pageList(@Param("page") Page<ProcessTaskHistory> page, @Param("req") ProcessTaskPageReq req);
}
