package com.koi.iam.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.system.domain.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author lida
 */
@Repository
public interface ResourceMapper extends SuperMapper<Resource> {

    /**
     * 查询指定用户资源权限
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    @InterceptorIgnore(tenantLine = "true", dataPermission = "false")
    List<String> selectPermissionByUserId(Long userId);
}
