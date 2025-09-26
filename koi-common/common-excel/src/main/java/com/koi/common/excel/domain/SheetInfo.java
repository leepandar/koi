package com.koi.common.excel.domain;

import com.koi.common.excel.handler.head.HeadGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * sheet 信息
 *
 * @author lida
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SheetInfo {


    @Builder.Default
    private int sheetNo = -1;
    /**
     * 名字
     */
    @Builder.Default
    private String name = "sheet1";

    /**
     * 包含字段
     */
    private List<String> includes;

    /**
     * 排除字段
     */
    private List<String> excludes;

    /**
     * 头生成器
     */
    @Builder.Default
    private Class<? extends HeadGenerator> headGenerateClass = HeadGenerator.class;

}
