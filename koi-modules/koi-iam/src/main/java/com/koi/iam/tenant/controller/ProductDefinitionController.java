package com.koi.iam.tenant.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.common.core.entity.Dict;
import com.koi.common.db.mybatisplus.page.PageRequest;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.domain.dto.resp.RolePermissionResp;
import com.koi.iam.tenant.domain.dto.req.ProductDefPermissionReq;
import com.koi.iam.tenant.domain.dto.req.ProductDefinitionSaveReq;
import com.koi.iam.tenant.domain.dto.resp.ProductDefinitionPageResp;
import com.koi.iam.tenant.domain.entity.ProductDefinition;
import com.koi.iam.tenant.service.ProductDefinitionService;
import com.koi.iam.tenant.domain.dto.req.ProductDefPermissionReq;
import com.koi.iam.tenant.domain.dto.req.ProductDefinitionSaveReq;
import com.koi.iam.tenant.domain.dto.resp.ProductDefinitionPageResp;
import com.koi.iam.tenant.domain.entity.ProductDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product-definitions")
@Tag(name = "产品定义", description = "产品定义")
public class ProductDefinitionController {

    private final ProductDefinitionService productDefinitionService;

    @GetMapping("/list")
    @Operation(summary = "产品列表", description = "产品列表")
    public List<Dict<?>> list(String name, Boolean status) {
        return productDefinitionService.list(Wraps.<ProductDefinition>lbQ().likeRight(ProductDefinition::getName, name)
                        .eq(ProductDefinition::getStatus, status))
                .stream()
                .map(x -> Dict.builder().label(x.getName()).value(x.getId()).build())
                .collect(Collectors.toList());

    }

    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public IPage<ProductDefinitionPageResp> pageList(PageRequest req, String code, String name, Boolean status) {
        return productDefinitionService.page(req.buildPage(), Wraps.<ProductDefinition>lbQ().eq(ProductDefinition::getCode, code)
                .likeRight(ProductDefinition::getName, name)
                .eq(ProductDefinition::getStatus, status)).convert(x -> BeanUtil.toBean(x, ProductDefinitionPageResp.class));
    }

    @PostMapping
    @Operation(summary = "添加产品", description = "添加产品")
    public void create(@RequestBody ProductDefinitionSaveReq req) {
        productDefinitionService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑产品", description = "编辑产品")
    public void modify(@PathVariable Long id, @RequestBody ProductDefinitionSaveReq req) {
        productDefinitionService.modify(id, req);
    }

    @GetMapping("/{id}/permissions")
    @Operation(summary = "编辑产品", description = "编辑产品")
    public RolePermissionResp permissions(@PathVariable Long id) {
        return productDefinitionService.findPermissions(id);
    }

    @PutMapping("/{id}/permissions")
    @AccessLog(module = "产品定义", description = "产品授权", response = false)
    @Operation(summary = "产品授权", description = "产品授权")
    public void permissions(@PathVariable Long id, @Validated @RequestBody ProductDefPermissionReq req) {
        productDefinitionService.permissions(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除产品", description = "删除产品")
    public void delete(@PathVariable Long id) {
        productDefinitionService.delete(id);
    }
}
