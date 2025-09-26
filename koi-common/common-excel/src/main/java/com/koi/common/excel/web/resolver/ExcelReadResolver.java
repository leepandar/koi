package com.koi.common.excel.web.resolver;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.koi.common.excel.convert.DictConverter;
import com.koi.common.excel.convert.InstantConverter;
import com.koi.common.excel.domain.ExcelReadFile;
import com.koi.common.excel.handler.read.ValidateAnalysisEventListener;


/**
 * Excel读取解析程序
 *
 * @author lida
 */
public class ExcelReadResolver {

    /**
     * 解析 ExcelReadFile 对象,并且返回校验的集合对象
     *
     * @param file file 对象
     * @return 解析结果
     */
    public static ValidateAnalysisEventListener<?> read(ExcelReadFile file) {
        ValidateAnalysisEventListener<?> readListener = file.getReadListener();
        ExcelReaderBuilder readerBuilder = EasyExcel.read(file.getFile(), file.getExcelModelClass(), readListener);
        // 自动适配多类型
        if (file.getInputStream() != null) {
            readerBuilder.file(file.getInputStream());
        }
        if (file.getFile() != null) {
            readerBuilder.file(file.getFile());
        }
        if (file.getPathName() != null) {
            readerBuilder.file(file.getPathName());
        }
        readerBuilder
                .registerConverter(new DictConverter())
                .registerConverter(new InstantConverter())
                .ignoreEmptyRow(file.getIgnoreEmptyRow())
                .headRowNumber(file.getHeadRowNumber())
                .password(file.getPassword())
                .sheet(file.getSheetNo(), file.getSheetName()).doRead();
        return readListener;
    }

}
