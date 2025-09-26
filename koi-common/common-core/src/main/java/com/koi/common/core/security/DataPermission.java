package com.koi.common.core.security;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;
import java.util.Map;

/**
 * 数据权限
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataPermission implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 权限范围
     */
    @Builder.Default
    private DataScopeType scopeType = DataScopeType.IGNORE;

    /**
     * 多维度数据权限
     */
    @Builder.Default
    private Map<DataResourceType, List<Object>> dataPermissionMap = Maps.newHashMap();

}
