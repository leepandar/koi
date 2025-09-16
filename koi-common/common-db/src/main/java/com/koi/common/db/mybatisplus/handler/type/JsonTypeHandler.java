package com.koi.common.db.mybatisplus.handler.type;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Field;

/**
 * 参考 {@link com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler} 实现
 * 字符串反序列化为 Set<Long> 时，如果元素的数值太小，会被处理成 Integer 类型，导致可能存在隐性的 BUG。
 *
 * @author lida
 */
@MappedTypes(value = {JSONObject.class})
public class JsonTypeHandler extends AbstractJsonTypeHandler<JSONObject> {
    public JsonTypeHandler(Class<?> type, Field field) {
        super(type, field);
    }

    public JsonTypeHandler(Class<?> type) {
        super(type);
    }

    @Override
    public JSONObject parse(String json) {
        return JSON.parseObject(json);
    }

    @Override
    public String toJson(JSONObject obj) {
        return obj == null ? null : obj.toJSONString();
    }

}