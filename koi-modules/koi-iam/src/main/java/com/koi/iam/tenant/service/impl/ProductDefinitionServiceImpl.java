package com.koi.iam.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.redis.sequence.RedisSequenceHelper;
import com.koi.iam.tenant.domain.entity.ProductDefinition;
import com.koi.iam.tenant.domain.entity.ProductDefinitionRes;
import com.koi.iam.tenant.domain.entity.ProductSubscription;
import com.koi.iam.tenant.domain.enums.TenantSequence;
import com.koi.iam.tenant.mapper.ProductDefResMapper;
import com.koi.iam.tenant.mapper.ProductDefinitionMapper;
import com.koi.iam.tenant.mapper.ProductSubscriptionMapper;
import com.koi.iam.tenant.service.ProductDefinitionService;
import com.koi.iam.system.domain.dto.resp.RolePermissionResp;
import com.koi.iam.system.domain.entity.Resource;
import com.koi.iam.system.domain.enums.ResourceType;
import com.koi.iam.system.mapper.ResourceMapper;
import com.koi.iam.tenant.domain.dto.req.ProductDefPermissionReq;
import com.koi.iam.tenant.domain.dto.req.ProductDefinitionSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductDefinitionServiceImpl extends SuperServiceImpl<ProductDefinitionMapper, ProductDefinition> implements ProductDefinitionService {

    private final ProductDefResMapper productDefResMapper;
    private final ProductSubscriptionMapper productSubscriptionMapper;
    private final ResourceMapper resourceMapper;
    private final RedisSequenceHelper sequenceHelper;

    /**
     * 创建产品定义
     *
     * @param req req
     */
    @Override
    public void create(ProductDefinitionSaveReq req) {
        final long count = count(Wraps.<ProductDefinition>lbQ().eq(ProductDefinition::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("该产品名称已存在");
        }
        var bean = BeanUtil.toBean(req, ProductDefinition.class);
        String code = sequenceHelper.generate(TenantSequence.PRODUCT_DEFINITION_NO);
        bean.setCode(code);
        this.baseMapper.insert(bean);
    }

    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, ProductDefinitionSaveReq req) {
        final ProductDefinition definition = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("产品信息不存在"));
        final long count = count(Wraps.<ProductDefinition>lbQ().ne(ProductDefinition::getId, definition.getId()).eq(ProductDefinition::getName, req.getName()));
        if (count > 0) {
            throw CheckedException.badRequest("该产品名称已存在");
        }
        this.baseMapper.updateById(ProductDefinition.builder()
                .id(id).name(req.getName()).logo(req.getLogo()).description(req.getDescription())
                .build());
    }

    /**
     * 产品授予权限
     *
     * @param productId productId
     * @param req       req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void permissions(Long productId, ProductDefPermissionReq req) {
        // 删除角色和资源的关联
        productDefResMapper.delete(Wraps.<ProductDefinitionRes>lbQ().eq(ProductDefinitionRes::getProductId, productId));
        final Set<Long> resIdList = req.getResIdList();
        if (CollectionUtil.isEmpty(resIdList)) {
            return;
        }
        final List<ProductDefinitionRes> resList = resIdList.stream().filter(Objects::nonNull)
                .map(resId -> ProductDefinitionRes.builder().resId(resId).productId(productId).build())
                .collect(toList());
        productDefResMapper.insertBatch(resList);
    }

    /**
     * 查询套餐权限
     *
     * @param id id
     * @return
     */
    @Override
    public RolePermissionResp findPermissions(Long id) {
        final List<Resource> resourceList = resourceMapper.selectList();
        if (CollUtil.isEmpty(resourceList)) {
            return null;
        }
        List<Long> resIdList = this.productDefResMapper.selectList(ProductDefinitionRes::getProductId, id)
                .stream().map(ProductDefinitionRes::getResId).distinct().toList();
        List<Long> buttonIdList = resourceList.stream()
                .filter(x -> resIdList.contains(x.getId()))
                .filter(x -> x.getType() == ResourceType.BUTTON)
                .map(Resource::getId)
                .toList();
        List<Long> menuIdList = resourceList.stream()
                .filter(x -> resIdList.contains(x.getId()))
                .filter(x -> x.getType() != ResourceType.BUTTON)
                .map(Resource::getId)
                .toList();
        return RolePermissionResp.builder().menuIdList(menuIdList).buttonIdList(buttonIdList).build();
    }

    /**
     * 删除产品
     *
     * @param id id
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ProductDefinition definition = Optional.ofNullable(this.baseMapper.selectById(id))
                .orElseThrow(() -> CheckedException.notFound("产品不存在,删除失败"));
        if (definition.getStatus() != null && definition.getStatus()) {
            throw CheckedException.notFound("产品已启用,删除失败");
        }
        Long count = this.productSubscriptionMapper.selectCount(ProductSubscription::getProductId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("产品已被订阅,删除失败");
        }
    }

}
