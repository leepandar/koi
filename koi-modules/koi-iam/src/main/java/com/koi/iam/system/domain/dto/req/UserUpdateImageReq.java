package com.koi.iam.system.domain.dto.req;

import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author lida
 * @since 2019-11-04
 */
@Data
@Schema(name = "UserUpdateImageReq", description = "用户")
public class UserUpdateImageReq {

    @Schema(description = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 头像
     */
    @Schema(description = "头像")
//    @Length(max = 255, message = "头像长度不能超过 {max}")
    private String image;

}
