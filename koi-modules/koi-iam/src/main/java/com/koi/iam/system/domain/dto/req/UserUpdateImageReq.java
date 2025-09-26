package com.koi.iam.system.domain.dto.req;

import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户头像更新请求参数
 *
 * @author lida
 */
@Data
@Schema(name = "UserUpdateImageReq", description = "用户头像更新DTO")
public class UserUpdateImageReq {

    @Schema(description = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    @Schema(description = "头像")
    @Length(max = 255, message = "头像长度不能超过 {max}")
    private String image;

}
