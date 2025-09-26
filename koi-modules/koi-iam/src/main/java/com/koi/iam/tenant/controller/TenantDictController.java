package com.koi.iam.tenant.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.common.core.entity.Dict;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.tenant.domain.dto.req.TenantDictSaveReq;
import com.koi.iam.tenant.domain.dto.resp.TenantDictResp;
import com.koi.iam.tenant.domain.entity.TenantDict;
import com.koi.iam.tenant.service.TenantDictService;
import com.koi.iam.tenant.domain.dto.req.TenantDictSaveReq;
import com.koi.iam.tenant.domain.dto.resp.TenantDictResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/tenant-dict")
@Tag(name = "业务字典", description = "业务字典")
@RequiredArgsConstructor
public class TenantDictController {

    private final AuthenticationContext context;
    private final TenantDictService tenantDictService;

    @GetMapping("/list")
    @Operation(summary = "字典列表", description = "查询字典列表")
    @SaCheckPermission(value = {"tenant:dict:list"})
    public List<TenantDictResp> list() {
        List<TenantDict> list = this.tenantDictService.list(Wraps.<TenantDict>lbQ().eq(TenantDict::getStatus, true));
        return BeanUtilPlus.toBeans(list, TenantDictResp.class);
    }

    @PostMapping("/refresh")
    @AccessLog(module = "租户字典", description = "刷新字典")
    @Operation(summary = "刷新字典", description = "刷新字典缓存数据")
    @SaCheckPermission(value = {"tenant:dict:refresh"})
    public void refresh() {
        this.tenantDictService.refresh();
    }

    @PostMapping("/incr-sync")
    @AccessLog(module = "租户字典", description = "同步字典")
    @Operation(summary = "同步字典", description = "同步平台字典到租户字典库中")
    // @SaCheckPermission(value = {"tenant:dict:sync-dict"})
    public void incrSyncTenantDict() {
        this.tenantDictService.incrSyncTenantDict(context.tenantId());
    }

    @PostMapping("/create")
    @AccessLog(module = "租户字典", description = "字典新增")
    @Operation(summary = "新增字典", description = "新增字典")
    @SaCheckPermission(value = {"tenant:dict:add"})
    public void create(@Validated @RequestBody TenantDictSaveReq req) {
        this.tenantDictService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "租户字典", description = "字典编辑")
    @Operation(summary = "编辑字典", description = "编辑字典")
    @SaCheckPermission(value = {"tenant:dict:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody TenantDictSaveReq req) {
        this.tenantDictService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "租户字典", description = "删除指定字典项")
    @Operation(summary = "删除字典", description = "删除字典")
    @SaCheckPermission(value = {"tenant:dict:remove"})
    public void remove(@PathVariable Long id) {
        this.tenantDictService.deleteById(id);
    }

    @GetMapping("/{code}/list")
    @Operation(summary = "查询字典子项", description = "查询字典子项")
    @Parameter(name = "code", description = "编码", in = ParameterIn.PATH)
    public List<Dict<String>> list(@PathVariable("code") String code) {
        return tenantDictService.findItemByCode(code);
    }
}
