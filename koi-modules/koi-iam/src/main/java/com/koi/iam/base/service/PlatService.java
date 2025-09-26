package com.koi.iam.base.service;

import com.koi.iam.base.domain.dto.resp.SiteSettingDetailResp;
import jakarta.servlet.http.HttpServletRequest;

public interface PlatService {

    /**
     * 站点设置信息
     *
     * @param request http request
     * @return 站点详情
     */
    SiteSettingDetailResp siteSetting(HttpServletRequest request);

}
