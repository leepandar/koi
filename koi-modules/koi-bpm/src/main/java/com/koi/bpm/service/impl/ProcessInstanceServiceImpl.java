package com.koi.bpm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.bpm.api.domain.enums.ProcessModelStatus;
import com.koi.bpm.api.domain.req.StartInstanceReq;
import com.koi.bpm.api.domain.resp.StartInstanceResp;
import com.koi.bpm.domain.entity.ProcessInstanceExt;
import com.koi.bpm.domain.entity.ProcessModel;
import com.koi.bpm.domain.entity.ProcessTaskComment;
import com.koi.bpm.domain.enums.ProcInstStatus;
import com.koi.bpm.domain.enums.TaskCommentType;
import com.koi.bpm.domain.req.ProcessInstancePageReq;
import com.koi.bpm.domain.resp.*;
import com.koi.bpm.mapper.ProcessInstanceExtMapper;
import com.koi.bpm.mapper.ProcessTaskCommentMapper;
import com.koi.bpm.service.ProcessInstanceService;
import com.koi.bpm.service.ProcessModelService;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流程实例业务层实现
 *
 * @author lida
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    private final AuthenticationContext context;
    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;
    private final HistoryService historyService;
    private final ProcessInstanceExtMapper processInstanceExtMapper;
    private final ProcessModelService processModelService;
    private final ProcessTaskCommentMapper processTaskCommentMapper;

    @Override
    public IPage<ProcessInstancePageResp> pageList(ProcessInstancePageReq req) {
        //获取流程定义扩展表信息
        IPage<ProcessInstancePageResp> page = processInstanceExtMapper.selectPage(req.buildPage(), Wraps.<ProcessInstanceExt>lbQ()
                        .eq(ProcessInstanceExt::getProcInstCategoryId, req.getProcInstCategoryId())
                        .like(ProcessInstanceExt::getProcDefName, req.getProcDefName())
                        .like(ProcessInstanceExt::getProcDefKey, req.getProcDefKey())
                        .like(ProcessInstanceExt::getProcInstName, req.getProcInstName())
                        .eq(ProcessInstanceExt::getProcInstActivate, req.getProcInstActivate()).orderByDesc(ProcessInstanceExt::getId))
                .convert(x -> BeanUtil.toBean(x, ProcessInstancePageResp.class));
        List<ProcessInstancePageResp> records = page.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            return new Page<>();
        }
        //获取流程任务并按流程实例ID分组
        List<Task> taskList = taskService.createTaskQuery().processInstanceIdIn(records.stream().map(ProcessInstancePageResp::getProcInstId).toArray(String[]::new)).list();
        List<ProcessTaskResp> processTaskRespList = taskList.stream().map(task ->
                ProcessTaskResp.builder().id(task.getId()).procInstId(task.getProcessInstanceId()).taskName(task.getName())
                        .createdName(task.getAssignee()).createdTime(task.getCreateTime()).build()).collect(Collectors.toList());
        final Map<String, List<ProcessTaskResp>> taskByProcInstId = processTaskRespList.parallelStream().collect(Collectors.groupingBy(ProcessTaskResp::getProcInstId));
        //组装数据
        List<ProcessInstancePageResp> list = records.stream().peek(record -> {
            List<ProcessTaskResp> tasks = taskByProcInstId.get(record.getProcInstId());
            record.setTasks(tasks);
        }).toList();
        page.setRecords(list);
        return page;
    }


    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void suspendOrResumeInstance(Long instanceId, Boolean activate) {
        final ProcessInstanceExt instance = this.processInstanceExtMapper.selectById(instanceId);
        processInstanceExtMapper.updateById(ProcessInstanceExt.builder().id(instanceId).procInstActivate(activate).build());
        if (activate) {
            runtimeService.activateProcessInstanceById(instance.getProcInstId());
        } else {
            runtimeService.suspendProcessInstanceById(instance.getProcInstId());
        }
    }

    @Override
    public ProcessInstanceDetailResp detail(String id) {
        var instanceExt = Optional.ofNullable(processInstanceExtMapper.selectOne(ProcessInstanceExt::getProcInstId, id)).orElseThrow(() -> CheckedException.badRequest("流程信息不存在"));
        //获取历史任务信息
        var historicActivityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(id).orderByHistoricActivityInstanceStartTime().asc().list();
        BpmnModelInstance instance = repositoryService.getBpmnModelInstance(instanceExt.getProcDefId());
        Collection<SequenceFlow> sequenceFlows = instance.getModelElementsByType(SequenceFlow.class);
        // 使用 Stream API 优化遍历
        var nodeList = historicActivityInstances.stream()
                .flatMap(activityInstance -> sequenceFlows.stream()
                        .filter(flow -> StrUtil.equals(flow.getTarget().getId(), activityInstance.getActivityId()))
                        .flatMap(flow -> {
                            FlowNode sourceNode = flow.getSource();
                            FlowNode targetNode = flow.getTarget();
                            // 创建节点信息对象
                            var line = ProcessInstanceDetailResp.ProcessInstanceActivityNode.builder().id(flow.getId()).type("line").build();
                            var source = ProcessInstanceDetailResp.ProcessInstanceActivityNode.builder().id(sourceNode.getId()).type("node").build();
                            var target = ProcessInstanceDetailResp.ProcessInstanceActivityNode.builder().id(targetNode.getId()).type("node").build();
                            return Stream.of(line, source, target);
                        })
                )
                .collect(Collectors.toList());
        return ProcessInstanceDetailResp.builder().diagramData(instanceExt.getDiagramData()).diagramName(instanceExt.getDiagramName())
                .processDefinitionId(instanceExt.getProcDefId()).processDefinitionKey(instanceExt.getProcDefKey())
                .processInstanceId(instanceExt.getProcInstId()).processInstanceName(instanceExt.getProcInstName()).version(instanceExt.getProcInstVersion())
                .processStartTime(instanceExt.getProcInstStartTime())
                .processEndTime(instanceExt.getProcInstEndTime())
                .commentList(comments(instanceExt.getProcInstId(), TaskCommentType.APPROVAL))
                .nodeList(nodeList).build();
    }

    @Override
    public ProcessInstanceFormPreviewResp formPreview(String id) {
        final ProcessInstanceExt ext = Optional.ofNullable(processInstanceExtMapper.selectOne(ProcessInstanceExt::getProcInstId, id))
                .orElseThrow(() -> CheckedException.notFound("流程不存在"));
        //通过实例ID获取表单数据
        return ProcessInstanceFormPreviewResp.builder().formDesign(ProcessInstanceFormPreviewResp.FormDesign.builder()
                        .schemas(JSONArray.parseArray(ext.getFormSchemas())).script(ext.getFormScript()).build())
                .formData(JSONObject.parse(ext.getFormData())).build();

    }

    @Override
    public List<ProcessTaskCommentResp> comments(String procInstId, TaskCommentType type) {
        List<ProcessTaskComment> comments = this.processTaskCommentMapper.selectList(ProcessTaskComment::getProcInstId, procInstId,
                ProcessTaskComment::getType, type);
        return comments.stream().map(x -> ProcessTaskCommentResp.builder().taskId(x.getProcTaskId())
                .taskDefinitionKey(x.getTaskDefinitionKey())
                .remark(x.getRemark()).attachments(StrUtil.split(x.getAttachment(), ','))
                .approverTime(x.getCreatedTime()).approverName(x.getCreatedName())
                .build()).collect(Collectors.toList());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public StartInstanceResp startProcess(StartInstanceReq req) {
        String businessKey = StrUtil.blankToDefault(IdUtil.fastSimpleUUID(), req.getBusinessKey());
        ProcessModel processModel = Optional.ofNullable(this.processModelService.getOne(Wraps.<ProcessModel>lbQ()
                        .eq(ProcessModel::getCode, req.getModelCode())))
                .orElseGet(() -> ProcessModel.builder().code(req.getModelCode()).status(ProcessModelStatus.NOT_EXIST).build());
        StartInstanceResp result = StartInstanceResp.builder().businessKey(businessKey).modelStatus(processModel.getStatus()).build();
        if (processModel.getStatus() != ProcessModelStatus.DEPLOYED) {
            return result;
        }
        // 设置流程参数
        JSONObject variables = new JSONObject(req.getVariables());
        variables.put("ext.processInstName", req.getProcessInstName());
        variables.put("ext.businessKey", req.getBusinessKey());
        variables.put("ext.businessGroup", req.getBusinessGroup());
        variables.put("ext.businessTag", req.getBusinessTag());
        variables.put("ext.remark", req.getRemark());
        variables.put("ext.variables", req.getVariables());
        ProcessInstance instance = runtimeService.createProcessInstanceByKey(processModel.getDefinitionKey())
                .processDefinitionTenantId(context.tenantId().toString()).businessKey(businessKey).setVariables(variables).execute();
        result.setProcessInstanceId(instance.getProcessInstanceId());
        return result;
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void cancel(String instanceId) {
        final ProcessInstanceExt instance = Optional.ofNullable(this.processInstanceExtMapper.selectOne(ProcessInstanceExt::getProcInstId, instanceId))
                .orElseThrow(() -> CheckedException.badRequest("bpm.process-instance.config-not-exists"));
        if (!instance.getProcInstActivate()) {
            throw CheckedException.badRequest("无法作废挂起的流程");
        }
        this.processInstanceExtMapper.updateById(ProcessInstanceExt.builder().id(instance.getId()).procInstStatus(ProcInstStatus.CANCEL).build());
        try {
            runtimeService.createProcessInstanceModification(instance.getProcInstId()).cancelActivityInstance(instance.getProcInstId()).execute();
        } catch (ProcessEngineException e) {
            log.error("流程不存在或已作废", e);
            throw CheckedException.badRequest("流程不存在或已作废({0})", e.getLocalizedMessage());
        }
    }
}
