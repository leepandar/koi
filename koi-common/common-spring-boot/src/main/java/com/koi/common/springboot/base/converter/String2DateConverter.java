package com.koi.common.springboot.base.converter;

import com.google.common.collect.Maps;
import com.koi.common.core.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.koi.common.core.times.TimeConstants.*;

/**
 * 解决入参为 Date类型
 *
 * @author lida
 */
@Slf4j
public class String2DateConverter extends BaseDateConverter<Date> implements Converter<String, Date> {

    private static final Map<String, String> FORMAT = Maps.newLinkedHashMap();

    static {
        FORMAT.put(DEFAULT_YEAR_FORMAT, "^\\d{4}");
        FORMAT.put(DEFAULT_MONTH_FORMAT, "^\\d{4}-\\d{1,2}$");
        FORMAT.put(DEFAULT_DATE_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
        FORMAT.put("yyyy-MM-dd HH", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}");
        FORMAT.put("yyyy-MM-dd HH:mm", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$");
        FORMAT.put(DEFAULT_DATE_TIME_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
        FORMAT.put("yyyy/MM", "^\\d{4}/\\d{1,2}$");
        FORMAT.put("yyyy/MM/dd", "^\\d{4}/\\d{1,2}/\\d{1,2}$");
        FORMAT.put("yyyy/MM/dd HH", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}");
        FORMAT.put("yyyy/MM/dd HH:mm", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$");
        FORMAT.put("yyyy/MM/dd HH:mm:ss", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }

    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    protected static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            // 严格模式
            dateFormat.setLenient(false);
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.info("转换日期失败, date={}, format={}", dateStr, format, e);
            throw new ValidException(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
        }
        return date;
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public Date convert(@NonNull String source) {
        return super.convert(source, (key) -> parseDate(source, key));
    }

}
