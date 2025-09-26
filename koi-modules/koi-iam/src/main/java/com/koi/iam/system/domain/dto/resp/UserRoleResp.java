package com.koi.iam.system.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户角色返回
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RoleResResp", description = "用户角色返回")
public class UserRoleResp {

    private List<UserRoleDetail> userRoleDetails;
    private List<Long> originTargetKeys;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRoleDetail {

        @Schema(description = "ID")
        private Long id;

        @Schema(description = "昵称")
        private String nickName;

        @Schema(description = "用户名")
        private String username;

    }

}
