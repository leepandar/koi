package com.koi.gateway.rest.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lida
 */
@Data
public class CommonRule {

    private String id;
    private String method;
    private String path;
    private Boolean status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
