package com.koi.bpm.listener.base;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.bpm.domain.entity.ProcessTaskExt;
import com.koi.bpm.domain.entity.ProcessTaskHistory;
import com.koi.bpm.domain.enums.ProcTaskStatus;
import com.koi.bpm.mapper.ProcessTaskExtMapper;
import com.koi.bpm.mapper.ProcessTaskHistoryMapper;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * 任务审核拒绝
 *
 * @author lida
 */
@Slf4j
@Component
public class TaskDeletedListener implements TaskListener {

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void notify(DelegateTask task) {
        log.info("deleted task - {}", task.getName());
        var taskService = SpringUtil.getBean(TaskService.class);
        var taskExtMapper = SpringUtil.getBean(ProcessTaskExtMapper.class);
        var variables = taskService.getVariablesLocal(task.getId());
        log.debug("审批内容 - {}", variables);
        String remark = (String) variables.get("remark");
        var tenantId = Long.parseLong(task.getTenantId());
        var procInstId = task.getProcessInstanceId();
        ProcTaskStatus taskStatus = ProcTaskStatus.of(task.getEventName());
        ProcessTaskHistoryMapper taskHistoryMapper = SpringUtil.getBean(ProcessTaskHistoryMapper.class);
        ProcessTaskHistory history = ProcessTaskHistory.builder().tenantId(tenantId)
                .procTaskId(task.getId()).procTaskName(task.getName()).procTaskStatus(taskStatus)
                .procTaskDefKey(task.getTaskDefinitionKey())
                .procInstId(procInstId)
                .remark(remark)
                .description("审批退回")
                .build();
        taskHistoryMapper.insert(history);
        //删除任务运行扩展表中的数据
        taskExtMapper.delete(Wraps.<ProcessTaskExt>lbQ().eq(ProcessTaskExt::getProcTaskId, task.getId()));
    }

}
