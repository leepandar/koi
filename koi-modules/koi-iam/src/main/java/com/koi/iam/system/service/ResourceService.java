package com.koi.iam.system.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.system.domain.dto.req.ResourceQueryReq;
import com.koi.iam.system.domain.dto.req.ResourceSaveReq;
import com.koi.iam.system.domain.dto.resp.VisibleResourceResp;
import com.koi.iam.system.domain.entity.Resource;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 资源
 * </p>
 *
 * @author lida
 */
public interface ResourceService extends SuperService<Resource> {

    /**
     * 查询 拥有的资源
     *
     * @param req req
     * @return 查询结果
     */
    List<VisibleResourceResp> findVisibleResource(ResourceQueryReq req);

    /**
     * 添加资源
     *
     * @param req 资源
     */
    void create(ResourceSaveReq req);

    /**
     * 修改资源
     *
     * @param id  id
     * @param req 资源
     */
    void modify(Long id, ResourceSaveReq req);

    /**
     * 删除资源
     *
     * @param resourceId resourceId
     */
    void delete(Long resourceId);

}
