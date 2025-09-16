package com.koi.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.gen.domain.entity.GenerateTableColumn;
import com.koi.suite.gen.domain.dto.rep.GenerateTableColumnPageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;
import com.koi.suite.gen.domain.entity.GenerateTableColumn;

import java.util.Collection;
import java.util.List;

/**
 * @author lida
 */
public interface GenerateTableColumnService extends SuperService<GenerateTableColumn> {

    int insertBatchSomeColumn(Collection<GenerateTableColumn> entityList);

    List<GenerateTableColumn> listByTableName(String name);

    IPage<GenerateTableColumnPageRep> pageList(GenerateTableColumnPageReq req);

    void create(GenerateTableColumnSaveReq req);

    void modify(Long id, GenerateTableColumnSaveReq req);

    void batchModify(List<GenerateTableColumnSaveReq> req);
}
