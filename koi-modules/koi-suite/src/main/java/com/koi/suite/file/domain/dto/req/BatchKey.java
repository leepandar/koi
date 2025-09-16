package com.koi.suite.file.domain.dto.req;

import lombok.Data;

import java.util.List;

/**
 * 泛型
 *
 * @author lida
 */
@Data
public class BatchKey<T> {

    private List<T> ids;

}
