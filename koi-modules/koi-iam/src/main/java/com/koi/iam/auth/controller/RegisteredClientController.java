package com.koi.iam.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.domain.converts.RegisteredClientConverts;
import com.koi.iam.system.domain.dto.req.RegisteredClientReq;
import com.koi.iam.system.domain.dto.resp.RegisteredClientResp;
import com.koi.iam.auth.domain.entity.RegisteredClient;
import com.koi.iam.auth.service.RegisteredClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/registered-client")
@Tag(name = "终端管理", description = "终端管理")
public class RegisteredClientController {

    private final RegisteredClientService registeredClientService;

    @GetMapping
    @Parameters({
            @Parameter(description = "clientId", name = "clientId", in = ParameterIn.QUERY),
            @Parameter(description = "clientName", name = "clientName", in = ParameterIn.QUERY)
    })
    @Operation(summary = "应用列表")
    public IPage<RegisteredClientResp> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                                             @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                                             String clientId, String clientName) {
        return this.registeredClientService.page(new Page<>(current, size),
                        Wraps.<RegisteredClient>lbQ().like(RegisteredClient::getClientId, clientId)
                                .like(RegisteredClient::getClientName, clientName))
                .convert(RegisteredClientConverts.REGISTERED_CLIENT_REF_2_RESP_CONVERTS::convert);
    }

    @PostMapping
    @AccessLog(module = "终端管理", description = "保存应用")
    @Operation(summary = "保存应用")
    public void create(@Validated @RequestBody RegisteredClientReq req) {
        this.registeredClientService.create(req);
    }

    @PutMapping("/{id}")
    @AccessLog(module = "终端管理", description = "修改应用")
    @Operation(summary = "修改应用")
    public void modify(@PathVariable Long id, @Validated @RequestBody RegisteredClientReq req) {
        this.registeredClientService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "终端管理", description = "删除应用")
    @Operation(summary = "删除应用")
    public void del(@PathVariable String id) {
        this.registeredClientService.deleteById(id);
    }

}
