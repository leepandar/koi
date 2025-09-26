package com.koi.iam.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.google.common.collect.Maps;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.annotation.log.AccessLog;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.service.OrgService;
import com.koi.iam.system.domain.dto.req.OrgSaveReq;
import com.koi.iam.system.domain.entity.Org;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/org")
@RequiredArgsConstructor
@Tag(name = "组织架构", description = "组织架构")
public class OrgController {

    private final OrgService orgService;

    @GetMapping("/trees")
    @Operation(summary = "查询系统所有的组织树", description = "查询系统所有的组织树")
    public List<Tree<Long>> trees(String name, Boolean status) {
        List<Org> list = this.orgService.list(Wraps.<Org>lbQ().like(Org::getLabel, name).eq(Org::getStatus, status).orderByAsc(Org::getSequence));
        final List<TreeNode<Long>> nodes = list.stream().map(org -> {
            final TreeNode<Long> treeNode = new TreeNode<>(org.getId(), org.getParentId(), org.getLabel(), org.getSequence());
            Map<String, Object> extra = Maps.newLinkedHashMap();
            extra.put("label", org.getLabel());
            extra.put("alias", org.getAlias());
            extra.put("status", org.getStatus());
            extra.put("sequence", org.getSequence());
            extra.put("tel", org.getTel());
            extra.put("description", org.getDescription());
            treeNode.setExtra(extra);
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(nodes, 0L);
    }

    @PostMapping("/create")
    @AccessLog(module = "组织架构", description = "创建组织架构")
    @Operation(summary = "创建组织架构")
    @SaCheckPermission(value = {"sys:org:add"})
    public void create(@Validated @RequestBody OrgSaveReq req) {
        orgService.create(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(module = "组织架构", description = "编辑组织架构")
    @Operation(summary = "编辑组织架构")
    @SaCheckPermission(value = {"sys:org:edit"})
    public void modify(@PathVariable Long id, @Validated @RequestBody OrgSaveReq req) {
        orgService.updateById(BeanUtilPlus.toBean(id, req, Org.class));
    }

    @DeleteMapping("/{id}")
    @AccessLog(module = "组织架构", description = "删除组织架构")
    @Operation(summary = "删除组织架构")
    @SaCheckPermission(value = {"sys:org:remove"})
    public void del(@PathVariable Long id) {
        orgService.remove(id);
    }

}
