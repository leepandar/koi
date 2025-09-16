package com.koi.iam.system.service;

import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.system.domain.dto.req.OrgSaveReq;
import com.koi.iam.system.domain.entity.Org;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author lida
 */
public interface OrgService extends SuperService<Org> {

    /**
     * 批量删除以及删除其子节点
     *
     * @param id id
     */
    void remove(Long id);

    /**
     * 添加组织
     *
     * @param req req
     */
    void create(OrgSaveReq req);

    /**
     * 获取当前机构本级及子级的ID
     *
     * @param id id
     * @return 本级及子级ID
     */
    List<Long> getFullTreeIdPath(Long id);
}
