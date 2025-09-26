package com.koi.common.feign.decoder;

import com.koi.common.core.entity.Result;
import com.koi.common.core.exception.CheckedException;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Feign 响应解码器
 *
 * @author lida
 */
@Slf4j
@RequiredArgsConstructor
public class FeignResponseDecoder implements Decoder {

    private final SpringDecoder decoder;

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        log.debug("feign decode response - {}", response);
        // 如果Feign接口的返回值不是 Response{code:0,...} 结构类型，并且远程响应又是这个结构
        boolean notTheSame = method.getReturnType() != Result.class;
        if (response.status() != 200 && notTheSame) {
            throw new CheckedException(response.status(), response.reason());
        }
        if (response.status() != 200) {
            return this.decoder.decode(response, type);
        }

        if (notTheSame) {
            // 构造一个这个结构类型
            Type newType = new ParameterizedType() {

                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return Result.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };
            Result<?> result = (Result<?>) this.decoder.decode(response, newType);
            // 只返回data
            return result.getData();
        }
        return this.decoder.decode(response, type);
    }
}