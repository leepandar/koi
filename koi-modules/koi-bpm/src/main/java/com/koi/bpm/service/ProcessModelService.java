package com.koi.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.bpm.domain.entity.ProcessModel;
import com.koi.bpm.domain.req.*;
import com.koi.bpm.domain.resp.DesignModelFormResp;
import com.koi.bpm.domain.resp.DesignModelGroupListResp;
import com.koi.bpm.domain.resp.ProcessModelDetailResp;
import com.koi.bpm.domain.resp.ProcessModelPageResp;
import com.koi.common.db.mybatisplus.ext.SuperService;

import java.util.List;

/**
 * 流程模型管理(DesignModel)业务层接口
 *
 * @author lida
 */
public interface ProcessModelService extends SuperService<ProcessModel> {


    /**
     * 流程模型管理保存
     *
     * @param req req
     */
    void create(DesignModelSaveReq req);


    /**
     * 分页查询
     *
     * @param req ${@link ProcessModelPageReq} 流程模型管理分页查询Vo
     * @return PageDto<DesignModelPageDto> ${@link IPage< ProcessModelPageResp >} 分页查询结果
     */
    IPage<ProcessModelPageResp> pageList(ProcessModelPageReq req);


    /**
     * 通过id查询详情
     *
     * @param modelId ${@link Long} 模型id
     * @return 模型详情
     */
    ProcessModelDetailResp detail(Long modelId);


    /**
     * 模型删除
     *
     * @param id  ${@link Long} 模型ID
     * @param req ${@link DesignModelDeleteReq} 模型删除
     */
    void deleteByModel(Long id, DesignModelDeleteReq req);


    /**
     * 通过ID部署流程定义
     *
     * @param id 流程模型ID
     */
    void deployById(Long id);

    /**
     * 修改模型
     *
     * @param id  模型ID
     * @param req 修改参数
     */
    void modify(Long id, DesignModelSaveReq req);

    /**
     * 保存表单
     *
     * @param id  模型ID
     * @param req 表单信息
     */
    void saveFormDesign(Long id, FormDesignSaveReq req);

    /**
     * 查询设计的表单信息
     *
     * @param id id
     * @return 查询结果
     */
    DesignModelFormResp findFormDesign(Long id);
    /**
     * 模型分组列表
     *
     * @return 查询结果
     */
    List<DesignModelGroupListResp> groupList();

    /**
     * 启动流程实例
     *
     * @param id  模型ID
     * @param req 启动实例
     */
    void startInstance(Long id, InstanceStartReq req);
}
