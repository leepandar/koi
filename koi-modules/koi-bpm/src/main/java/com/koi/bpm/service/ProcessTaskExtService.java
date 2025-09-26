package com.koi.bpm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.bpm.domain.enums.ApprovalStatus;
import com.koi.bpm.domain.req.ProcessTaskApprovalReq;
import com.koi.bpm.domain.req.ProcessTaskAssigneeReq;
import com.koi.bpm.domain.req.ProcessTaskPageReq;
import com.koi.bpm.domain.resp.ProcessTaskExtResp;
import com.koi.bpm.domain.resp.ProcessTaskHistoryResp;

public interface ProcessTaskExtService {

    /**
     * 查询自己的代办任务
     *
     * @param request 分页条件
     * @return 任务信息
     */
    Page<ProcessTaskExtResp> pageList(ProcessTaskPageReq request);

    /**
     * 历史任务
     *
     * @param req req
     * @return 查询结果
     */
    Page<ProcessTaskHistoryResp> hisPageList(ProcessTaskPageReq req);

    /**
     * 审批
     *
     * @param taskId 任务ID
     * @param req    req
     * @param status (title = "审批类型（20：审批通过；-10：审批撤回；-20：审批拒绝）")
     */
    void approval(String taskId, ApprovalStatus status, ProcessTaskApprovalReq req);

    /**
     * 评论
     *
     * @param taskId 任务ID
     * @param req    req
     */
    void comment(String taskId, ProcessTaskApprovalReq req);

    /**
     * 拾取任务
     *
     * @param taskId 任务ID
     */
    void claimTask(String taskId);

    /**
     * 转办任务
     *
     * @param taskId 任务ID
     * @param req    req
     */
    void transfer(String taskId, ProcessTaskAssigneeReq req);

    /**
     * 委派任务
     *
     * @param taskId 任务ID
     * @param req    req
     */
    void delegate(String taskId, ProcessTaskAssigneeReq req);
}
