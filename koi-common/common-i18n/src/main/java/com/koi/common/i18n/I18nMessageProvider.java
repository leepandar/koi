package com.koi.common.i18n;

import com.koi.common.i18n.domain.I18nMessage;

import java.util.List;
import java.util.Locale;

/**
 * @author lida
 */
public interface I18nMessageProvider {


    /**
     * 获取消息
     *
     * @param code   code
     * @param locale 语言
     * @return 国际化消息
     */
    String getI18nMessage(String code, Locale locale);

    /**
     * 加载全部的 i18n 数据
     *
     * @return 国际化消息
     */
    List<I18nMessage> list();


    /**
     * 加载消息到存储列表
     *
     * @param messages 消息
     */
    void loadI18nMessage(List<I18nMessage> messages);

}
