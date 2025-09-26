package com.koi.bpm.service;

import java.util.function.Supplier;

public interface ProcessIdentityService {


    <T> T execute(Supplier<T> supplier);

}
