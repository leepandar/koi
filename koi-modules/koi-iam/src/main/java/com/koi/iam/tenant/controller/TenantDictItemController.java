package com.koi.iam.tenant.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.base.domain.dto.req.DictItemPageReq;
import com.koi.iam.base.domain.dto.req.DictItemSaveReq;
import com.koi.iam.tenant.domain.entity.TenantDictItem;
import com.koi.iam.tenant.service.TenantDictItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 字典项
 *
 * @author lida
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "业务字典", description = "业务字典")
@RequestMapping("/tenant-dict-items")
public class TenantDictItemController {

    private final TenantDictItemService tenantDictItemService;

    @GetMapping("/page")
    @Operation(summary = "查询字典子项", description = "查询字典子项 - [DONE] - [lida]")
    @Parameters({
            @Parameter(name = "dict_code", description = "字典编码", in = ParameterIn.PATH),
            @Parameter(name = "label", description = "名称", in = ParameterIn.QUERY)
    })
    public Page<TenantDictItem> pageList(DictItemPageReq req) {
        return this.tenantDictItemService.page(req.buildPage(), Wraps.<TenantDictItem>lbQ()
                .eq(TenantDictItem::getDictCode, req.getDictCode())
                .eq(TenantDictItem::getStatus, req.getStatus())
                .like(TenantDictItem::getLabel, req.getLabel()));
    }

    @PostMapping("/create")
    @Operation(summary = "添加字典子项", description = "添加字典子项 - [DONE] - [lida]")
    @Parameter(name = "dict_code", description = "字典编码", in = ParameterIn.PATH)
    public void create(@Validated @RequestBody DictItemSaveReq req) {
        this.tenantDictItemService.create(req);

    }

    @PutMapping("/{id}/modify")
    @Operation(summary = "编辑字典子项 - [DONE] - [lida]", description = "编辑字典子项 - [DONE] - [lida]")
    @Parameter(name = "dict_code", description = "字典编码", in = ParameterIn.PATH)
    public void modify(@PathVariable("id") Long id, @Validated @RequestBody DictItemSaveReq req) {
        this.tenantDictItemService.modify(id, req);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除字典子项 - [DONE] - [lida]", description = "删除字典子项 - [DONE] - [lida]")
    public void remove(@PathVariable("id") Long id) {
        this.tenantDictItemService.delete(id);
    }

}
