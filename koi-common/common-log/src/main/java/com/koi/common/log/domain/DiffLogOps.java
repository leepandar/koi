package com.koi.common.log.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 日志操作
 *
 * @author muzhantong
 */
@Data
@Builder
public class DiffLogOps {

    private String successLogTemplate;
    private String failLogTemplate;
    private String group;
    private String businessKey;
    private String tag;
    private String extra;
    private String condition;
    private String isSuccess;
}
