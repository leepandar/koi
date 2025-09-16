package com.koi.suite.gen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.suite.gen.domain.entity.GenerateTable;
import com.koi.suite.gen.service.GenerateTableService;
import com.koi.suite.gen.domain.dto.rep.GenerateTableDetailRep;
import com.koi.suite.gen.domain.dto.rep.GenerateTablePageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableSaveReq;
import com.koi.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableSaveReq;
import com.koi.suite.gen.domain.entity.GenerateTable;
import com.koi.suite.gen.service.GenerateTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成表配置
 *
 * @author lida
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/gennerate-table")
@Tag(name = "代码生成表配置", description = "代码生成表配置")
public class GenerateTableController {

    private final GenerateTableService generateTableService;

    /**
     * 获取当前数据源的表信息
     */
    @Operation(summary = "获取当前未导入的数据源的表信息")
    @GetMapping("/ds/list")
    public List<GenerateTable> getGenTableListFromDs(String tableName) throws Exception {
        return generateTableService.getGenTableListFromDs(tableName);
    }

    /**
     * 数据源表信息 导入到 生成表配置信息
     */
    @Operation(summary = "数据源表信息导入到生成表配置信息")
    @PostMapping("/ds/import")
    public void importToGenTable(@RequestBody List<String> tableNames) {
        generateTableService.importToGenTable(tableNames);
    }

    /**
     * 分页查询表配置信息
     */
    @Operation(summary = "分页查询表配置信息")
    @GetMapping("/page")
    public IPage<GenerateTablePageRep> pageList(GenerateTablePageReq req) {
        return generateTableService.pageList(req);
    }

    /**
     * 获取表配置信息详情
     */
    @Operation(summary = "获取表配置信息详情")
    @GetMapping("/{id}/detail")
    public GenerateTableDetailRep detail(@PathVariable Long id) {
        return generateTableService.detail(id);
    }

    /**
     * 更新表配置信息
     */
    @Operation(summary = "更新表配置信息")
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable Long id, @Validated @RequestBody GenerateTableSaveReq req) {
        generateTableService.modify(id, req);
    }

    /**
     * 刪除表配置信息
     */
    @Operation(summary = "刪除表配置信息")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        generateTableService.removeGen(id);
    }

    @Operation(summary = "代码生成")
    @PostMapping("/{id}/generate")
    public void generate(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        generateTableService.generate(id, request, response);
    }

    //TODO 批量代码生成

    @Operation(summary = "代码生成预览")
    @GetMapping("/{id}/preview")
    public Map<String, String> previewCode(@PathVariable Long id) {
        return generateTableService.previewCode(id);
    }


}
