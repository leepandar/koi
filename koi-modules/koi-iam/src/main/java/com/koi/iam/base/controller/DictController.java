package com.koi.iam.base.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.common.core.entity.Dict;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.base.service.DictService;
import com.koi.iam.base.domain.dto.req.DictSaveReq;
import com.koi.iam.base.domain.dto.resp.SysDictResp;
import com.koi.iam.base.domain.entity.SysDict;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/dict")
@Tag(name = "系统字典", description = "系统字典")
@RequiredArgsConstructor
public class DictController {

    private final DictService dictService;

    @GetMapping("/list")
    @Operation(summary = "字典列表", description = "查询字典列表")
    @SaCheckPermission(value = {"dict:list"})
    public List<SysDictResp> list() {
        List<SysDict> list = this.dictService.list(Wraps.<SysDict>lbQ().eq(SysDict::getStatus, true).orderByAsc(SysDict::getSequence));
        return BeanUtilPlus.toBeans(list, SysDictResp.class);
    }

    @PostMapping("/refresh")
    @AccessLog(module = "系统字典", description = "刷新字典")
    @Operation(summary = "刷新字典", description = "刷新字典缓存数据")
    @SaCheckPermission(value = {"dict:refresh"})
    public void refresh() {
        this.dictService.refresh();
    }

    @PostMapping("/create")
    @AccessLog(module = "系统字典", description = "字典新增")
    @Operation(summary = "新增字典", description = "新增字典")
    @SaCheckPermission(value = {"dict:add"})
    public void create(@Validated @RequestBody DictSaveReq req) {
        this.dictService.create(req);
    }

    @PutMapping("/{id}")
    @AccessLog(module = "系统字典", description = "字典编辑")
    @Operation(summary = "编辑字典", description = "编辑字典")
    @SaCheckPermission(value = {"dict:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody DictSaveReq req) {
        this.dictService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "系统字典", description = "删除指定字典项")
    @Operation(summary = "删除字典", description = "删除字典")
    @SaCheckPermission(value = {"dict:remove"})
    public void remove(@PathVariable Long id) {
        this.dictService.deleteById(id);
    }

    @GetMapping("/{code}/list")
    @Operation(summary = "查询字典子项", description = "查询字典子项")
    @Parameters({
            @Parameter(name = "Accept-Language", description = "语言", example = "zh-CN,zh;q=0.9", in = ParameterIn.HEADER),
            @Parameter(name = "code", description = "编码", in = ParameterIn.PATH),
    })
    public List<Dict<String>> list(@PathVariable("code") String code) {
        return dictService.findItemByCode(code);
    }
}
