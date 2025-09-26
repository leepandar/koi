package com.koi.common.excel.configuration;

import com.koi.common.excel.handler.ISheetWriteHandler;
import com.koi.common.excel.handler.head.I18nHeaderCellWriteHandler;
import com.koi.common.excel.handler.write.SingleSheetWriteHandler;
import com.koi.common.excel.web.handler.ExcelWriteFileReturnValueHandler;
import com.koi.common.excel.web.handler.ResponseExcelReturnValueHandler;
import com.koi.common.excel.handler.ISheetWriteHandler;
import com.koi.common.excel.handler.head.I18nHeaderCellWriteHandler;
import com.koi.common.excel.handler.write.SingleSheetWriteHandler;
import com.koi.common.excel.web.handler.ExcelWriteFileReturnValueHandler;
import com.koi.common.excel.web.handler.ResponseExcelReturnValueHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Excel 特殊处理
 *
 * @author lida
 */
@RequiredArgsConstructor
public class ExcelHandlerConfiguration {


    /**
     * 头部国际化处理
     *
     * @param messageSource messageSource
     * @return I18nHeaderCellWriteHandler
     */
    @Bean
    public I18nHeaderCellWriteHandler i18nHeaderCellWriteHandler(MessageSource messageSource) {
        return new I18nHeaderCellWriteHandler(messageSource);
    }

    @Bean
    @ConditionalOnMissingBean
    public SingleSheetWriteHandler singleSheetWriteHandler(ApplicationContext context) {
        return new SingleSheetWriteHandler(context);
    }

    @Bean
    @ConditionalOnMissingBean
    public ResponseExcelReturnValueHandler responseExcelReturnValueHandler(ApplicationContext context, List<ISheetWriteHandler> sheetWriteHandlers) {
        return new ResponseExcelReturnValueHandler(context, sheetWriteHandlers);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExcelWriteFileReturnValueHandler excelWriteFileReturnValueHandler(ApplicationContext context, List<ISheetWriteHandler> sheetWriteHandlers) {
        return new ExcelWriteFileReturnValueHandler(context, sheetWriteHandlers);
    }

}
