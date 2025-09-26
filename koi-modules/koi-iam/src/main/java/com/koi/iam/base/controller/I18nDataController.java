package com.koi.iam.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.iam.base.service.I18nDataService;
import com.koi.iam.base.domain.dto.req.I18nDataSaveReq;
import com.koi.iam.base.domain.dto.req.I18nPageReq;
import com.koi.iam.base.domain.dto.resp.I18nDataPageResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/i18n")
@Tag(name = "I18N国际化", description = "I18N国际化维护")
@RequiredArgsConstructor
public class I18nDataController {

    private final I18nDataService i18nDataService;

    @GetMapping
    @Operation(summary = "查询国际化", description = "查询国际化")
    // @SaCheckPermission(value = {"i18n:page"})
    public IPage<I18nDataPageResp> page(I18nPageReq req) {
        return this.i18nDataService.pageList(req);
    }

    @PostMapping
    @AccessLog(module = "I18N国际化", description = "国际化新增")
    @Operation(summary = "新增国际化", description = "新增国际化")
    // @SaCheckPermission(value = {"i18n:add"})
    public void save(@Validated @RequestBody I18nDataSaveReq req) {
        this.i18nDataService.add(req);
    }

    @PutMapping("/{id}")
    @AccessLog(module = "I18N国际化", description = "国际化编辑")
    @Operation(summary = "编辑国际化", description = "编辑国际化")
    // @SaCheckPermission(value = {"i18n:edit"})
    public void edit(@PathVariable Long id, @Validated @RequestBody I18nDataSaveReq req) {
        this.i18nDataService.edit(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "I18N国际化", description = "删除指定国际化项")
    @Operation(summary = "删除国际化", description = "删除国际化")
    // @SaCheckPermission(value = {"i18n:remove"})
    public void del(@PathVariable Long id) {
        this.i18nDataService.removeById(id);
    }

}
