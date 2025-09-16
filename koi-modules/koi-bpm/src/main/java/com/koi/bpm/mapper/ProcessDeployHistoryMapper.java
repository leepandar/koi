package com.koi.bpm.mapper;

import com.koi.bpm.domain.entity.ProcessDeployHistory;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 流程模型历史(ProcessDeployHistory)持久层
 *
 * @author lida
 */
@Repository
public interface ProcessDeployHistoryMapper extends SuperMapper<ProcessDeployHistory> {

    /**
     * 通过历史模型id物理删除
     *
     * @param modelId ${@link Long} 历史模型id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author Levin
     */
    int physicalDeleteByModelId(@Param("modelId") Long modelId);
}
