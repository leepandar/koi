package com.koi.iam.base.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.common.i18n.domain.I18nMessage;
import com.koi.iam.base.domain.entity.I18nData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface I18nDataMapper extends SuperMapper<I18nData> {

    /**
     * 加载 i18n 数据
     *
     * @return 查询结果
     */
    List<I18nMessage> loadI18nMessage();

}
