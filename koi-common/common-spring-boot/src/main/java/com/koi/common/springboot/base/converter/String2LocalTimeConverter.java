package com.koi.common.springboot.base.converter;

import com.google.common.collect.Maps;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.koi.common.core.times.TimeConstants.DEFAULT_TIME_FORMAT;

/**
 * 解决入参为 Date类型
 *
 * @author lida
 */
public class String2LocalTimeConverter extends BaseDateConverter<LocalTime> implements Converter<String, LocalTime> {

    private static final Map<String, String> FORMAT = Maps.newLinkedHashMap();

    static {
        FORMAT.put(DEFAULT_TIME_FORMAT, "^\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public LocalTime convert(String source) {
        return super.convert(source, (key) -> LocalTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
