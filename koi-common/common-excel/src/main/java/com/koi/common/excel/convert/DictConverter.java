package com.koi.common.excel.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.koi.common.core.entity.DictEnum;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 枚举字典转换器
 *
 * @author lida
 */
@SuppressWarnings("unchecked")
public class DictConverter implements Converter<DictEnum<?>> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return DictEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override

    public DictEnum<?> convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                         GlobalConfiguration globalConfiguration) {
        Field field = contentProperty.getField();
        Class<DictEnum<?>> type = (Class<DictEnum<?>>) field.getType();
        return DictEnum.of(type, cellData.getStringValue());
    }

    @Override
    public WriteCellData<String> convertToExcelData(DictEnum<?> value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(Optional.ofNullable(value).map(DictEnum::getDesc).orElse(""));
    }

}