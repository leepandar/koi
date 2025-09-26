package com.koi.iam.tenant.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.tenant.domain.entity.TenantDict;
import com.koi.iam.tenant.domain.entity.TenantDictItem;
import com.koi.iam.tenant.mapper.TenantDictItemMapper;
import com.koi.iam.tenant.mapper.TenantDictMapper;
import com.koi.iam.base.domain.dto.req.DictItemSaveReq;
import com.koi.iam.tenant.service.TenantDictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantDictItemServiceImpl extends SuperServiceImpl<TenantDictItemMapper, TenantDictItem> implements TenantDictItemService {

    private final TenantDictMapper tenantDictMapper;
    private final AuthenticationContext context;

    /**
     * 添加字典项
     *
     * @param req req
     */
    @Override
    public void create(DictItemSaveReq req) {
        final TenantDict dict = Optional.ofNullable(this.tenantDictMapper.selectOne(TenantDict::getCode, req.getDictCode()))
                .orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final long count = this.baseMapper.selectCount(Wraps.<TenantDictItem>lbQ().eq(TenantDictItem::getValue, req.getValue())
                .eq(TenantDictItem::getDictCode, dict.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("子项编码已存在");
        }
        TenantDictItem item = BeanUtil.toBean(req, TenantDictItem.class);
        item.setDictId(dict.getId());
        item.setDictCode(dict.getCode());
        item.setTenantId(context.tenantId());
        item.setReadonly(false);
        this.baseMapper.insert(item);
    }

    /**
     * 修改字典项
     *
     * @param id  字典项ID
     * @param req req
     */
    @Override
    public void modify(Long id, DictItemSaveReq req) {
        TenantDictItem dictItem = this.baseMapper.selectById(id);
        if (dictItem == null) {
            throw CheckedException.notFound("字典信息不存在");
        }
        if (dictItem.getReadonly()) {
            throw CheckedException.notFound("禁止修改只读数据");
        }
        Long count = this.baseMapper.selectCount(Wraps.<TenantDictItem>lbQ()
                .ne(TenantDictItem::getId, id)
                .eq(TenantDictItem::getValue, req.getValue())
                .eq(TenantDictItem::getDictCode, req.getDictCode()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("子项编码已存在");
        }
        TenantDictItem item = BeanUtilPlus.toBean(id, req, TenantDictItem.class);
        this.baseMapper.updateById(item);
    }

    /**
     * 删除字典
     *
     * @param id id
     */
    @Override
    public void delete(Long id) {
        TenantDictItem dictItem = this.baseMapper.selectById(id);
        if (dictItem == null) {
            throw CheckedException.notFound("字典信息不存在");
        }
        if (dictItem.getReadonly()) {
            throw CheckedException.notFound("禁止删除只读数据");
        }
        this.baseMapper.deleteById(id);
    }
}
