package com.koi.bpm.service;

import java.util.function.Supplier;

/**
 * Camunda流程 身份信息维护业务接口
 *
 * @author lida
 */
public interface ProcessIdentityService {


    <T> T execute(Supplier<T> supplier);

}
