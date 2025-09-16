package com.koi.bpm.api;

import com.koi.common.core.FeignConstants;
import com.koi.bpm.api.domain.req.StartInstanceReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lida
 */
@FeignClient(name = FeignConstants.BPM_FEIGN_NAME, dismiss404 = true)
public interface RemoteProcessService {


    @PostMapping("/process-models/start-instance")
    void startProcess(@RequestBody StartInstanceReq req);


}
