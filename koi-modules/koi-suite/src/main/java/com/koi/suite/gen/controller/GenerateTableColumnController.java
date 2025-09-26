package com.koi.suite.gen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.suite.gen.domain.dto.resp.GenerateTableColumnPageRep;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnPageReq;
import com.koi.suite.gen.domain.dto.req.GenerateTableColumnSaveReq;
import com.koi.suite.gen.service.GenerateTableColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/gennerate-table-column")
@Tag(name = "代码生成列配置", description = "代码生成列配置")
public class GenerateTableColumnController {

    private final GenerateTableColumnService generateTableColumnService;

    @Operation(summary = "分页查询字段")
    @GetMapping("/page")
    public IPage<GenerateTableColumnPageRep> pageList(GenerateTableColumnPageReq req) {
        return generateTableColumnService.pageList(req);
    }

    @Operation(summary = "新增字段")
    @PostMapping("/create")
    public void create(@Validated @RequestBody GenerateTableColumnSaveReq req) {
        //暂不支持
        generateTableColumnService.create(req);
    }

    @Operation(summary = "编辑字段")
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody GenerateTableColumnSaveReq req) {
        generateTableColumnService.modify(id, req);
    }

    @Operation(summary = "批量更新")
    @PutMapping("/batch/modify")
    public void batchModify(@Validated @RequestBody List<GenerateTableColumnSaveReq> req) {
        //暂不支持
        generateTableColumnService.batchModify(req);
    }

    @Operation(summary = "字段详情")
    @GetMapping("/{id}/detail")
    public void detail(@PathVariable("id") Long id) {
        //暂不支持
        //return generateTableColumnService.detail(id);
    }

    /**
     * 删除字段
     */
    @Operation(summary = "删除字段")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        //暂不支持
        // generateTableColumnService.removeTemplate(id);
    }
}
