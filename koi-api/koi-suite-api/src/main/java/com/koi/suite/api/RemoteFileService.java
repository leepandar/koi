package com.koi.suite.api;

import com.koi.common.core.FeignConstants;
import com.koi.common.core.entity.Result;
import com.koi.common.feign.token.AutoRefreshTokenProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 开发组件接口
 *
 * @author lida
 */
@FeignClient(name = FeignConstants.SUITE_FEIGN_NAME, path = "/files", dismiss404 = true)
public interface RemoteFileService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件信息
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, headers = {AutoRefreshTokenProperties.X_AUTO_TOKEN, "ignore-header=Content-Type"})
    Result<?> upload(@RequestPart("file") MultipartFile file);

    /**
     * 获取token
     *
     * @param key    key
     * @param random 是否随机
     * @return token
     */
    @GetMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, headers = {AutoRefreshTokenProperties.X_AUTO_TOKEN, "ignore-header=Content-Type"})
    String getToken(@RequestParam("key") String key, @RequestParam(defaultValue = "true", name = "random") Boolean random);

}
