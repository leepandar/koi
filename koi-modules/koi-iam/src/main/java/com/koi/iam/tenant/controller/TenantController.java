package com.koi.iam.tenant.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.redis.anontation.RedisLock;
import com.koi.common.redis.anontation.RedisParam;
import com.koi.iam.tenant.domain.dto.req.TenantPageReq;
import com.koi.iam.tenant.domain.dto.req.TenantSaveReq;
import com.koi.iam.tenant.domain.dto.req.TenantSettingReq;
import com.koi.iam.tenant.domain.dto.resp.DbSettingPageResp;
import com.koi.iam.tenant.domain.dto.resp.TenantPageResp;
import com.koi.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.tenant.service.DbSettingService;
import com.koi.iam.tenant.service.TenantService;
import com.koi.iam.tenant.domain.dto.req.TenantPageReq;
import com.koi.iam.tenant.domain.dto.req.TenantSaveReq;
import com.koi.iam.tenant.domain.dto.req.TenantSettingReq;
import com.koi.iam.tenant.domain.dto.resp.DbSettingPageResp;
import com.koi.iam.tenant.domain.dto.resp.TenantPageResp;
import com.koi.iam.tenant.domain.dto.resp.TenantSettingResp;
import com.koi.iam.tenant.domain.entity.Tenant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tenants")
@Tag(name = "租户管理", description = "租户管理")
public class TenantController {

    private final TenantService tenantService;
    private final DbSettingService dynamicDatasourceService;

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @PostMapping("/ids")
    public List<Tenant> list(@RequestBody List<Long> ids) {
        return this.tenantService.list(Wraps.<Tenant>lbQ().in(Tenant::getId, ids));
    }

    @PostMapping("/page")
    @Operation(summary = "租户列表")
    @SaCheckPermission(value = {"tenant:list"})
    public IPage<TenantPageResp> pageList(@RequestBody TenantPageReq req) {
        return tenantService.page(req.buildPage(), Wraps.<Tenant>lbQ()
                .like(Tenant::getName, req.getName())
                .eq(Tenant::getCode, req.getCode())
                .eq(Tenant::getProvinceId, req.getProvinceId())
                .eq(Tenant::getCityId, req.getCityId())
                .eq(Tenant::getDistrictId, req.getDistrictId())
                .eq(Tenant::getIndustry, req.getIndustry()).eq(Tenant::getStatus, req.getStatus())
                .eq(Tenant::getType, req.getType())).convert(x -> BeanUtil.toBean(x, TenantPageResp.class));
    }

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/databases/active")
    public List<DbSettingPageResp> queryActive() {
        return this.dynamicDatasourceService.selectTenantDynamicDatasource();
    }

    @PostMapping("/create")
    @AccessLog(module = "租户管理", description = "添加租户")
    @Operation(summary = "添加租户")
    @SaCheckPermission(value = {"tenant:add"})
    public void create(@Validated @RequestBody TenantSaveReq req) {
        tenantService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "租户管理", description = "编辑租户")
    @Operation(summary = "编辑租户")
    @SaCheckPermission(value = {"tenant:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody TenantSaveReq req) {
        tenantService.modify(id, req);
    }

    @GetMapping("/{id}/setting")
    @AccessLog(module = "租户管理", description = "配置租户")
    @Operation(summary = "配置租户")
    // @SaCheckPermission(value = {"tenant:setting"})
    public TenantSettingResp setting(@PathVariable Long id) {
        return tenantService.settingInfo(id);
    }

    @PutMapping("/{id}/setting")
    @AccessLog(module = "租户管理", description = "配置租户")
    @Operation(summary = "配置租户")
    // @SaCheckPermission(value = {"tenant:setting"})
    public void setting(@PathVariable Long id, @Validated @RequestBody TenantSettingReq req) {
        tenantService.saveSetting(id, req);
    }

    // @PutMapping("/{id}/config")
    // @AccessLog(module = "", description = "配置租户")
    // @Operation(summary = "配置租户")
    // @SaCheckPermission(value = {"tenant:db-config"})
    // public void config(@PathVariable Long id, @Validated @RequestBody TenantConfigReq req) {
    // tenantService.tenantConfig(id, req);
    // }

    @PutMapping("/{id}/init_sql_script")
    @AccessLog(module = "", description = "加载初始数据")
    @Operation(summary = "加载初始数据")
    @RedisLock(prefix = "tenant:init-script")
    public void initSqlScript(@RedisParam(name = "id") @PathVariable Long id) {
        tenantService.initSqlScript(id);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "租户管理", description = "删除租户")
    @Operation(summary = "删除租户")
    @SaCheckPermission(value = {"tenant:remove"})
    public void remove(@PathVariable Long id) {
        tenantService.removeById(id);
    }

    @PutMapping("/{id}/refresh-dict")
    @AccessLog(module = "租户管理", description = "字典刷新")
    @Operation(summary = "字典刷新")
    @SaCheckPermission(value = {"tenant:refresh-dict"})
    public void refreshTenantDict(@PathVariable Long id) {
        tenantService.refreshTenantDict(id);
    }

}
