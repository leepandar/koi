package com.koi.common.log.service.impl;

import com.koi.common.log.service.IParseFunction;

/**
 * 默认的解析器
 *
 * @author lida
 */
public class DefaultParseFunction implements IParseFunction {

    @Override
    public boolean executeBefore() {
        return true;
    }

    @Override
    public String functionName() {
        return null;
    }

    @Override
    public String apply(Object value) {
        return null;
    }
}
