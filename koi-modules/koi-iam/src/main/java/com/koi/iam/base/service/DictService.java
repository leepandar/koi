package com.koi.iam.base.service;

import com.koi.common.core.entity.Dict;
import com.koi.common.db.mybatisplus.ext.SuperService;
import com.koi.iam.base.domain.dto.req.DictSaveReq;
import com.koi.iam.base.domain.entity.SysDict;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 字典类型
 * </p>
 *
 * @author lida
 * @since 2019-07-02
 */
public interface DictService extends SuperService<SysDict> {

    /**
     * 添加字典
     *
     * @param req 字典信息
     */
    void create(DictSaveReq req);

    /**
     * 删除字典
     *
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 编辑字典
     *
     * @param id  id
     * @param req 字典信息
     */
    void modify(Long id, DictSaveReq req);

    /**
     * 刷新缓存
     */
    void refresh();

    /**
     * 根据 code 查询
     *
     * @param code code
     * @return 查询结果
     */
    List<Dict<String>> findItemByCode(String code);

}
