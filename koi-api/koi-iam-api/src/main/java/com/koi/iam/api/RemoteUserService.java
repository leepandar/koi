package com.koi.iam.api;

import com.koi.common.core.FeignConstants;
import com.koi.common.core.remote.LoadService;
import com.koi.iam.api.domain.resp.UserInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

@FeignClient(name = FeignConstants.AUTH_FEIGN_NAME, dismiss404 = true)
public interface RemoteUserService extends LoadService<UserInfoResp> {

    /**
     * 根据 ID 批量查询
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return 查询结果
     */
    @Override
    @PostMapping("/users/batch_ids")
    Map<Object, UserInfoResp> findByIds(@RequestBody Set<Object> ids);

}
