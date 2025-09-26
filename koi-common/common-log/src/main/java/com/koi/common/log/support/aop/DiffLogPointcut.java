package com.koi.common.log.support.aop;

import jakarta.annotation.Nonnull;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

public class DiffLogPointcut extends StaticMethodMatcherPointcut implements Serializable {

    private DiffLogOperationSource diffLogOperationSource;

    @Override
    public boolean matches(@Nonnull Method method, @Nonnull Class<?> targetClass) {
        return !CollectionUtils.isEmpty(diffLogOperationSource.computeDiffLogOperations(method, targetClass));
    }

    public void setDiffLogOperationSource(DiffLogOperationSource diffLogOperationSource) {
        this.diffLogOperationSource = diffLogOperationSource;
    }
}