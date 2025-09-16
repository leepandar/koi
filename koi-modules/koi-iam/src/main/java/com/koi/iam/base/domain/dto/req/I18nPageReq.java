package com.koi.iam.base.domain.dto.req;

import com.koi.common.db.mybatisplus.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lida
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class I18nPageReq extends PageRequest {

    private String code;

}
