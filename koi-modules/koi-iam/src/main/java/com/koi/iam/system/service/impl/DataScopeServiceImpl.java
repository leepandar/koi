package com.koi.iam.system.service.impl;

import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.DataPermission;
import com.koi.common.core.security.DataResourceType;
import com.koi.common.core.entity.Entity;
import com.koi.common.db.mybatisplus.datascope.service.DataScopeService;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.system.mapper.DataPermissionResourceMapper;
import com.koi.iam.system.mapper.RoleMapper;
import com.koi.iam.system.mapper.UserMapper;
import com.koi.iam.system.domain.entity.DataPermissionResource;
import com.koi.iam.system.domain.entity.Role;
import com.koi.iam.system.domain.entity.User;
import com.koi.iam.system.service.OrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.koi.common.core.security.DataScopeType.*;


/**
 * @author lida
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeServiceImpl implements DataScopeService {

    private final RoleMapper roleMapper;
    private final DataPermissionResourceMapper dataPermissionResourceMapper;
    private final UserMapper userMapper;
    private final OrgService orgService;

    @Override
    public DataPermission getDataScopeById(Long userId) {
        final User user = Optional.ofNullable(this.userMapper.selectById(userId)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        return getDataScopeById(userId, user.getOrgId());
    }

    /**
     * 开发者可以根据自己企业需求动态扩展数据权限（默认就支撑多维度数据权限，此处以用户维护演示）
     *
     * @param userId 用户ID
     * @param orgId  用户当前机构
     * @return 数据权限
     */
    @Override
    public DataPermission getDataScopeById(Long userId, Long orgId) {
        List<Role> list = roleMapper.findRoleByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            return DataPermission.builder().build();
        }
        // 找到 dsType 最大的角色， dsType越大，角色拥有的权限最大
        Role role = list.stream().max(Comparator.comparingInt(item -> item.getScopeType().getType())).get();
        DataPermission permission = DataPermission.builder().scopeType(role.getScopeType()).build();
        List<Long> userIdList = null;
        if (role.getScopeType() == CUSTOMIZE) {
            List<Long> orgIdList = dataPermissionResourceMapper.selectList(Wraps.<DataPermissionResource>lbQ().select(DataPermissionResource::getDataId)
                            .eq(DataPermissionResource::getOwnerId, role.getId())
                            .eq(DataPermissionResource::getOwnerType, DataResourceType.ROLE)
                            .eq(DataPermissionResource::getDataType, DataResourceType.ORG))
                    .stream().map(DataPermissionResource::getDataId).distinct().toList();
            userIdList = this.userMapper.selectList(Wraps.<User>lbQ().select(User::getId).in(User::getOrgId, orgIdList))
                    .stream().map(Entity::getId).toList();
        } else if (role.getScopeType() == THIS_LEVEL) {
            userIdList = this.userMapper.selectList(Wraps.<User>lbQ().select(User::getId).eq(User::getOrgId, orgId))
                    .stream().map(Entity::getId).toList();
        } else if (role.getScopeType() == THIS_LEVEL_CHILDREN) {
            final List<Long> orgIdList = orgService.getFullTreeIdPath(orgId);
            userIdList = this.userMapper.selectList(Wraps.<User>lbQ().select(User::getId).in(User::getOrgId, orgIdList))
                    .stream().map(Entity::getId).toList();
        }
        if (userIdList != null) {
            permission.getDataPermissionMap().put(DataResourceType.USER, userIdList.stream().map(x -> (Object) x).toList());
        }
        // 如果你还有其他维度可以自行扩展
        return permission;
    }
}
