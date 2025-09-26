package com.koi.iam.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.db.utils.TenantHelper;
import com.koi.iam.system.mapper.ResourceMapper;
import com.koi.iam.system.mapper.RoleMapper;
import com.koi.iam.system.mapper.RoleResMapper;
import com.koi.iam.system.mapper.UserMapper;
import com.koi.iam.tenant.mapper.ProductDefResMapper;
import com.koi.iam.system.domain.dto.req.ResourceQueryReq;
import com.koi.iam.system.domain.dto.req.ResourceSaveReq;
import com.koi.iam.system.domain.dto.resp.VisibleResourceResp;
import com.koi.iam.system.domain.entity.Resource;
import com.koi.iam.system.domain.entity.Role;
import com.koi.iam.system.domain.entity.RoleRes;
import com.koi.iam.system.domain.enums.ResourceType;
import com.koi.iam.system.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends SuperServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private static final String SPEL = "/";
    private final AuthenticationContext context;
    private final ProductDefResMapper productDefResMapper;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final RoleResMapper roleResMapper;

    /**
     * 查询 拥有的资源
     *
     * @param req req
     * @return
     */
    @Override
    public List<VisibleResourceResp> findVisibleResource(ResourceQueryReq req) {
        var resIdList = TenantHelper.executeWithIsolationType(() -> {
            var list = TenantHelper.executeWithMaster(() -> {
                var roleResIdList = this.roleResMapper.selectTenantAdminResIdList();
                var productResIdList = this.productDefResMapper.selectDefRedByTenantId(context.tenantId());
                return CollUtil.addAll(roleResIdList, productResIdList).stream().distinct().toList();
            });
            if (CollUtil.isEmpty(list)) {
                return null;
            }
            var roleResIdList = this.userMapper.selectResByUserId(req.getUserId());
            return CollUtil.intersection(list, roleResIdList);
        }, () -> {
            var roleResIdList = this.userMapper.selectResByUserId(req.getUserId());
            var productResIdList = this.productDefResMapper.selectDefRedByTenantId(context.tenantId());
            return CollUtil.addAll(roleResIdList, productResIdList).stream().distinct().toList();
        });
        // 解决租户越权行为,菜单数据直接从主库查询,减少数据分发次数
        List<Resource> list = TenantHelper.executeWithMaster(() -> this.baseMapper.selectList(Wraps.<Resource>lbQ()
                .eq(Resource::getStatus, req.getStatus())
                .and(lb -> lb.eq(Resource::getGlobal, true)
                        .or(CollUtil.isNotEmpty(resIdList), xx -> xx.in(Resource::getId, resIdList)))
                .eq(Resource::getParentId, req.getParentId()).eq(Resource::getType, req.getType())));
        return BeanUtilPlus.toBeans(list, VisibleResourceResp.class);
    }

    /**
     * 添加资源
     *
     * @param req 资源
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void create(ResourceSaveReq req) {
        final Resource resource = BeanUtil.toBean(req, Resource.class);
        if (ResourceType.MENU == resource.getType()) {
            if (!StringUtils.startsWith(resource.getPath(), SPEL)) {
                resource.setPath(SPEL + resource.getPath());
            }
        }
        this.baseMapper.insert(resource);
        final List<Role> roles = this.roleMapper.selectList(Wraps.<Role>lbQ().eq(Role::getSuperRole, true)
                .eq(Role::getStatus, true));
        if (CollUtil.isEmpty(roles)) {
            return;
        }
        // 给管理员角色挂载权限
        final List<RoleRes> roleResList = roles.stream()
                .map(role -> RoleRes.builder().roleId(role.getId()).resId(resource.getId()).build())
                .toList();
        roleResMapper.insertBatchSomeColumn(roleResList);
    }

    /**
     * 修改资源
     *
     * @param id  id
     * @param req 资源
     */
    @Override
    public void modify(Long id, ResourceSaveReq req) {
        final Resource resource = BeanUtilPlus.toBean(id, req, Resource.class);
        this.baseMapper.updateById(resource);
    }

    /**
     * 删除资源
     *
     * @param id resourceId
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final Long count = this.baseMapper.selectCount(Resource::getParentId, id);
        if (count != null && count > 0) {
            throw CheckedException.badRequest("当前节点存在子节点,请先移除子节点");
        }
        // 删除菜单和按钮
        this.baseMapper.deleteById(id);
        // 删除对应的资源权限
        this.roleResMapper.delete(Wraps.<RoleRes>lbQ().eq(RoleRes::getResId, id));
    }
}
