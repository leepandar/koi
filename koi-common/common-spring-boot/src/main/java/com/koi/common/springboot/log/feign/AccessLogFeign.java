package com.koi.common.springboot.log.feign;

import com.koi.common.springboot.log.AccessLogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.koi.common.springboot.log.feign.AccessLogFeign.FEIGN_CLIENT_NAME;

/**
 * 访问日志的 Feign
 *
 * @author lida
 */
@FeignClient(name = FEIGN_CLIENT_NAME, dismiss404 = true)
public interface AccessLogFeign {

    String FEIGN_CLIENT_NAME = "koi-platform-iam";

    /**
     * 日志监听
     *
     * @param info 日志信息
     */
    @PostMapping("/opt_logs/listener")
    void listener(@RequestBody AccessLogInfo info);

}
