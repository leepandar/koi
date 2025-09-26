package com.koi.iam.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.koi.common.core.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "b_message_notify")
@Schema(name = "MessageNotify", description = "消息通知")
public class MessageNotify extends SuperEntity<Long> {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "消息类型")
    private String type;

    @Schema(description = "消息模板ID")
    private Long templateId;

    @Schema(description = "消息变量")
    private String variables;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "订阅人 比如 邮箱,手机号,钉钉ID等")
    private String subscribe;

    @Schema(description = "接收用户ID")
    private Long userId;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "租户ID")
    private Long tenantId;

}
