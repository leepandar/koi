package com.koi.iam.tenant.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.db.mybatisplus.page.PageRequest;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.tenant.domain.dto.req.DbSettingSaveReq;
import com.koi.iam.tenant.domain.entity.DbSetting;
import com.koi.iam.tenant.service.DbSettingService;
import com.koi.iam.tenant.domain.dto.req.DbSettingSaveReq;
import com.koi.iam.tenant.domain.entity.DbSetting;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lida
 */
@Slf4j
@RestController
@RequestMapping("/db-setting")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
@Validated
public class DbSettingController {

    private final DbSettingService dbSettingService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public Page<DbSetting> page(PageRequest pageRequest, String dbType, Boolean locked) {
        return dbSettingService.page(pageRequest.buildPage(),
                Wraps.<DbSetting>lbQ().eq(DbSetting::getDbType, dbType).eq(DbSetting::getLocked, locked));
    }

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/active")
    public List<DbSetting> queryActive() {
        return this.dbSettingService.list(Wraps.<DbSetting>lbQ().eq(DbSetting::getLocked, false));
    }

    @Operation(summary = "Ping数据库")
    @GetMapping("/{id}/ping")
    public void ping(@PathVariable Long id) {
        this.dbSettingService.ping(id);

    }

    @Operation(summary = "添加数据源")
    @PostMapping("/create")
    public void create(@Validated @RequestBody DbSettingSaveReq req) {
        dbSettingService.created(req);

    }

    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}/modify")
    public void modify(@PathVariable Long id, @Validated @RequestBody DbSettingSaveReq req) {
        dbSettingService.edit(id, req);

    }

    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        dbSettingService.delete(id);

    }
}
