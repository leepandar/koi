package com.koi.bpm.util;

import com.alibaba.fastjson2.JSONObject;

/**
 * 提取JSON中关键词的值
 *
 * @author lida
 */
public interface JsonKeywordHandler {


    /**
     * 提取JSON中关键词的值
     *
     * @param content    context是参数名称 ${id} ${username}
     * @param jsonObject jsonObject
     * @return 结果
     */
    String handleKeyword(String content, JSONObject jsonObject);
}

