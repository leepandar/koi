package com.koi.common.excel.convert;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.koi.common.core.threadlocal.ThreadLocalHolder;
import com.koi.common.core.times.TimeZoneUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * Instant 类型转换
 * 支持 JsonFormat 注解
 *
 * @author lida
 */
@Slf4j
public class InstantConverter implements Converter<Instant> {

    @Override
    public Class<Instant> supportJavaTypeKey() {
        return Instant.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Instant convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String value = cellData.getStringValue();
        if (StrUtil.isBlank(value)) {
            return null;
        }
        return TimeZoneUtil.toOffsetUtcTime(value, ThreadLocalHolder.getLocal());
    }

    @Override
    public WriteCellData<String> convertToExcelData(Instant value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (Objects.isNull(value)) {
            return new WriteCellData<>();
        }
        Field field = contentProperty.getField();
        // 暂时只读取 JsonFormat.format
        JsonFormat format = field.getAnnotation(JsonFormat.class);
        String pattern = Optional.ofNullable(format).map(JsonFormat::pattern).orElse("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = TimeZoneUtil.toZoneId(ThreadLocalHolder.getLocal());
        log.debug("Instant 日期时区 - {}", zoneId);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(value, zoneId);
        return new WriteCellData<>(localDateTime.format(DateTimeFormatter.ofPattern(pattern)));
    }
}
