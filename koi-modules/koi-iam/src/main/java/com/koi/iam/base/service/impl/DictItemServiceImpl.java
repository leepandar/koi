package com.koi.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.iam.base.mapper.SysDictItemMapper;
import com.koi.iam.base.mapper.SysDictMapper;
import com.koi.iam.base.domain.dto.req.DictItemSaveReq;
import com.koi.iam.base.domain.entity.SysDict;
import com.koi.iam.base.domain.entity.SysDictItem;
import com.koi.iam.base.service.DictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictItemServiceImpl extends SuperServiceImpl<SysDictItemMapper, SysDictItem> implements DictItemService {

    private final SysDictMapper sysDictMapper;

    /**
     * 添加字典项
     *
     * @param dictId 字典ID
     * @param req    req
     */
    @Override
    public void create(Long dictId, DictItemSaveReq req) {
        final SysDict dict = Optional.ofNullable(this.sysDictMapper.selectById(dictId)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final long count = this.baseMapper.selectCount(Wraps.<SysDictItem>lbQ().eq(SysDictItem::getValue, req.getValue())
                .eq(SysDictItem::getDictCode, dict.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("子项编码已存在");
        }
        SysDictItem item = BeanUtil.toBean(req, SysDictItem.class);
        item.setDictId(dict.getId());
        item.setDictCode(dict.getCode());
        this.baseMapper.insert(item);
    }

    /**
     * 修改字典项
     *
     * @param dictId 字典ID
     * @param itemId 字典项ID
     * @param req    req
     */
    @Override
    public void modify(Long dictId, Long itemId, DictItemSaveReq req) {
        final SysDict dict = Optional.ofNullable(this.sysDictMapper.selectById(dictId)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        final long count = this.baseMapper.selectCount(Wraps.<SysDictItem>lbQ().ne(SysDictItem::getId, itemId)
                .eq(SysDictItem::getValue, req.getValue()).eq(SysDictItem::getDictCode, dict.getCode()));
        if (count > 0) {
            throw CheckedException.badRequest("编码已存在");
        }
        SysDictItem item = BeanUtilPlus.toBean(itemId, req, SysDictItem.class);
        this.baseMapper.updateById(item);
    }
}
