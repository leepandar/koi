package com.koi.suite.online.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.online.domain.entity.OnlineModel;
import org.springframework.stereotype.Repository;

/**
 * 表单模型持久层
 *
 * @author lida
 */
@Repository
public interface OnlineModelMapper extends SuperMapper<OnlineModel> {
}
