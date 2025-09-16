package com.koi.suite.online.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.online.domain.entity.OnlineFormData;
import com.koi.suite.online.domain.req.OnlineFormDesignerPageReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 表单数据持久层
 *
 * @author lida
 */
@Repository
public interface OnlineFormDataMapper extends SuperMapper<OnlineFormData> {

    /**
     * 分页查询
     *
     * @param page
     * @param req
     * @return
     */
    IPage<OnlineFormData> pageList(@Param("page") Page<?> page, @Param("req") OnlineFormDesignerPageReq req);

}
