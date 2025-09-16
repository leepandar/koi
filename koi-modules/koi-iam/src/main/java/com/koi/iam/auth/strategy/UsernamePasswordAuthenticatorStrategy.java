package com.koi.iam.auth.strategy;

import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.utils.TenantHelper;
import com.koi.common.security.utils.PasswordEncoderHelper;
import com.koi.iam.system.domain.entity.User;
import com.koi.iam.system.mapper.UserMapper;
import com.koi.iam.tenant.domain.entity.Tenant;
import com.koi.iam.tenant.mapper.TenantMapper;
import com.koi.iam.auth.support.AuthenticationPrincipal;
import com.koi.iam.auth.support.AuthenticatorStrategy;
import com.koi.iam.auth.support.domain.UserTenantAuthentication;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 默认登录处理.
 *
 * @author lida
 **/
@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticatorStrategy implements AuthenticatorStrategy {

    @Resource
    private UserMapper userMapper;
    @Resource
    private TenantMapper tenantMapper;

    @Override
    public void prepare(final AuthenticationPrincipal principal) {
    }

    @Override
    public UserTenantAuthentication authenticate(final AuthenticationPrincipal principal) {
        String username = principal.getUsername();
        String password = principal.getPassword();
        String tenantCode = principal.getTenantCode();
        Tenant tenant = Optional.ofNullable(TenantHelper.executeWithMaster(() -> tenantMapper.selectOne(Tenant::getCode, tenantCode)))
                .orElseThrow(() -> CheckedException.notFound("{0}租户不存在", tenantCode));
        if (!tenant.getStatus()) {
            throw CheckedException.badRequest("租户已被禁用,请联系管理员");
        }
        User user = Optional.ofNullable(TenantHelper.executeWithTenantDb(tenantCode, () -> userMapper.selectUserByTenantId(username, tenant.getId())))
                .orElseThrow(() -> CheckedException.notFound("账户不存在"));
        if (!PasswordEncoderHelper.matches(password, user.getPassword())) {
            throw CheckedException.badRequest("用户名或密码错误");
        }
        return UserTenantAuthentication.builder().user(user).tenant(tenant).build();
    }
}
