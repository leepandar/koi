package com.koi.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.gen.domain.entity.GenerateTableColumn;
import com.koi.suite.gen.domain.dto.resp.GenerateTableColumnPageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;

import java.util.Collection;
import java.util.List;

public interface GenerateTableColumnService extends SuperService<GenerateTableColumn> {

    /**
     * 批量插入表字段
     *
     * @param entityList
     * @return
     */
    int insertBatchSomeColumn(Collection<GenerateTableColumn> entityList);

    /**
     * 根据表名查询字段列表
     *
     * @param name
     * @return
     */
    List<GenerateTableColumn> listByTableName(String name);

    /**
     * 分页查询表字段列表
     *
     * @param req
     * @return
     */
    IPage<GenerateTableColumnPageRep> pageList(GenerateTableColumnPageReq req);

    /**
     * 新增表字段
     *
     * @param req
     */
    void create(GenerateTableColumnSaveReq req);

    /**
     * 修改表字段
     *
     * @param id
     * @param req
     */
    void modify(Long id, GenerateTableColumnSaveReq req);

    /**
     * 批量修改表字段
     *
     * @param req
     */
    void batchModify(List<GenerateTableColumnSaveReq> req);
}
