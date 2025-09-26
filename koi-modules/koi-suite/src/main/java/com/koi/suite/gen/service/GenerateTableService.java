package com.koi.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.gen.domain.entity.GenerateTable;
import com.koi.suite.gen.domain.dto.resp.GenerateTableDetailRep;
import com.koi.suite.gen.domain.dto.resp.GenerateTablePageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableSaveReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GenerateTableService extends SuperService<GenerateTable> {

    /**
     * 根据表名查询表信息
     *
     * @param tableName
     * @return
     */
    List<GenerateTable> getGenTableListFromDs(String tableName) throws Exception;

    /**
     * 导入表
     *
     * @param tableNames
     */
    void importToGenTable(List<String> tableNames);

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    IPage<GenerateTablePageRep> pageList(GenerateTablePageReq req);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    GenerateTableDetailRep detail(Long id);

    /**
     * 修改
     *
     * @param id
     * @param req
     */
    void modify(Long id, GenerateTableSaveReq req);

    /**
     * 删除
     *
     * @param id
     */
    void removeGen(Long id);

    /**
     * 预览代码
     *
     * @param id
     * @return
     */
    Map<String, String> previewCode(Long id);

    /**
     * 生成代码
     *
     * @param id
     * @param request
     * @param response
     */
    void generate(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
