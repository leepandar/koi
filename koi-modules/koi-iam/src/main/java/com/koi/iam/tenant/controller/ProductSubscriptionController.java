package com.koi.iam.tenant.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.db.mybatisplus.page.PageRequest;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.tenant.domain.dto.req.ProductSubscriptionSaveReq;
import com.koi.iam.tenant.domain.dto.resp.ProductSubscriptionPageResp;
import com.koi.iam.tenant.domain.entity.ProductSubscription;
import com.koi.iam.tenant.service.ProductSubscriptionService;
import com.koi.iam.tenant.domain.dto.req.ProductSubscriptionSaveReq;
import com.koi.iam.tenant.domain.dto.resp.ProductSubscriptionPageResp;
import com.koi.iam.tenant.domain.entity.ProductSubscription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lida
 */
@Slf4j
@RequestMapping("/product_subscriptions")
@RestController
@RequiredArgsConstructor
@Tag(name = "产品订阅", description = "产品订阅")
public class ProductSubscriptionController {

    private final ProductSubscriptionService productSubscriptionService;

    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public IPage<ProductSubscriptionPageResp> pageList(PageRequest req, Long tenantId, Long productId, Boolean status) {
        return productSubscriptionService.page(req.buildPage(), Wraps.<ProductSubscription>lbQ()
                        .eq(ProductSubscription::getProductId, productId)
                        .eq(ProductSubscription::getTenantId, tenantId)
                        .eq(ProductSubscription::getPaymentStatus, status))
                .convert(x -> BeanUtil.toBean(x, ProductSubscriptionPageResp.class));
    }

    @PostMapping
    @Operation(summary = "添加订阅", description = "添加订阅")
    public void create(@Validated @RequestBody ProductSubscriptionSaveReq req) {
        productSubscriptionService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑订阅", description = "编辑订阅")
    public void modify(@PathVariable Long id, @Validated @RequestBody ProductSubscriptionSaveReq req) {
        productSubscriptionService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除订阅", description = "删除订阅")
    public void delete(@PathVariable Long id) {
        productSubscriptionService.removeById(id);
    }

}
