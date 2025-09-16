package com.koi.common.redis.sequence;

import java.time.format.DateTimeFormatter;

/**
 * @author lida
 */
public interface Sequence {

    /**
     * Redis Key
     *
     * @return String
     */
    String key();

    /**
     * Redis Prefix
     *
     * @return UN => UN20210909000001
     */
    String prefix();


    /**
     * 流水号的位数，如10 表示 0001
     */
    default int size() {
        return 4;
    }


    /**
     * 分隔符
     *
     * @return 分隔符
     */
    default String delimiter() {
        return "";
    }

    /**
     * 流水号中日期的格式， null 表示流水号中没有 日期
     */
    default DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("yyyyMMdd");
    }

}
