package com.koi.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.suite.gen.domain.entity.GenerateTable;
import com.koi.suite.gen.domain.dto.rep.GenerateTableDetailRep;
import com.koi.suite.gen.domain.dto.rep.GenerateTablePageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableSaveReq;
import com.koi.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableSaveReq;
import com.koi.suite.gen.domain.entity.GenerateTable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author lida
 */
public interface GenerateTableService extends SuperService<GenerateTable> {

    List<GenerateTable> getGenTableListFromDs(String tableName) throws Exception;

    void importToGenTable(List<String> tableNames);

    IPage<GenerateTablePageRep> pageList(GenerateTablePageReq req);

    GenerateTableDetailRep detail(Long id);

    void modify(Long id, GenerateTableSaveReq req);

    void removeGen(Long id);

    Map<String, String> previewCode(Long id);

    void generate(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
