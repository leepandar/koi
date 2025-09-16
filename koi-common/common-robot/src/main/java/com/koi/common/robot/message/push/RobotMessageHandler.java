package com.koi.common.robot.message.push;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.koi.common.robot.emums.NotifyType;

import java.util.Map;

/**
 * 发送消息接口
 *
 * @author lida
 */
public interface RobotMessageHandler {

    /**
     * 消息通知
     *
     * @param message 消息内容
     */
    String notify(String message);

    /**
     * 格式化文本，使用 {varName} 占位<br>
     * map = {a: "aValue", b: "bValue"} format("{a} and {b}", map) ---=》 aValue and bValue
     *
     * @param message    文本模板，被替换的部分用 {key} 表示
     * @param map        参数值对
     * @param ignoreNull 是否忽略 {@code null} 值，忽略则 {@code null} 值对应的变量不被替换，否则替换为""
     * @return 格式化后的文本
     * @since 5.7.10
     */
    String notify(String message, Map<?, ?> map, boolean ignoreNull);

    String getUrl();

    /**
     * 通知类型
     *
     * @return 通知类型
     */
    NotifyType notifyType();

    default String request(Map<String, Object> body) {
        return HttpUtil.post(this.getUrl(), JSON.toJSONString(body));
    }

}
