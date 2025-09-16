package com.koi.iam.auth.support.domain;

import com.koi.iam.system.domain.entity.User;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.system.domain.entity.User;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.system.domain.entity.User;
import com.koi.iam.tenant.domain.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTenantAuthentication {

    /**
     * 用户信息
     */
    private User user;

    /**
     * 租户信息
     */
    private Tenant tenant;
}
