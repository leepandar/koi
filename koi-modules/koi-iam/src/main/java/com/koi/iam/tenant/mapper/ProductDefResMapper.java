package com.koi.iam.tenant.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.tenant.domain.entity.ProductDefinitionRes;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDefResMapper extends SuperMapper<ProductDefinitionRes> {

    List<Long> selectDefRedByTenantId(@Param("tenantId") Long tenantId);

}
