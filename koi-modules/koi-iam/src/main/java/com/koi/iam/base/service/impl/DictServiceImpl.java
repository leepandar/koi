package com.koi.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.entity.Dict;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.springboot.remote.dict.DictLoadService;
import com.koi.iam.base.domain.entity.SysDict;
import com.koi.iam.base.domain.entity.SysDictItem;
import com.koi.iam.base.mapper.I18nLocaleMessageMapper;
import com.koi.iam.base.mapper.SysDictItemMapper;
import com.koi.iam.base.mapper.SysDictMapper;
import com.koi.iam.base.service.DictService;
import com.koi.iam.base.domain.dto.req.DictSaveReq;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 系统字典服务层
 *
 * @author lida
 * @see DictLoadService 可以理解为字典缓存（同时 @Remote 注解使用）
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends SuperServiceImpl<SysDictMapper, SysDict> implements DictService {

    private final SysDictItemMapper sysDictItemMapper;
    private final DictLoadService dictLoadService;
    private final I18nLocaleMessageMapper i18nLocaleMessageMapper;

    @PostConstruct
    public void init() {
        refresh();
    }

    @Override
    public void create(DictSaveReq req) {
        if (req == null) {
            throw CheckedException.notFound("字典内容不能为空");
        }
        final Long count = this.baseMapper.selectCount(SysDict::getCode, req.getCode());
        if (count != 0 && count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        this.baseMapper.insert(BeanUtil.toBean(req, SysDict.class));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, DictSaveReq req) {
        final SysDict old = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        if (old.getType() == 0) {
            throw CheckedException.notFound("平台字典数据无法修改");
        }
        final Long count = this.baseMapper.selectCount(Wraps.<SysDict>lbQ().ne(SysDict::getId, id).eq(SysDict::getCode, req.getCode()));
        if (count != 0 && count > 0) {
            throw CheckedException.badRequest("字典类型编码重复");
        }
        SysDict bean = BeanUtilPlus.toBean(id, req, SysDict.class);
        this.baseMapper.updateById(bean);
        this.dictLoadService.refreshCache(getPairMap(List.of(req.getCode())));
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        final SysDict dict = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("字典不存在"));
        if (dict.getType() == 0) {
            throw CheckedException.notFound("内置数据无法删除");
        }
        this.baseMapper.deleteById(id);
        this.sysDictItemMapper.delete(Wraps.<SysDictItem>lbQ().eq(SysDictItem::getDictCode, dict.getCode()));
    }

    @Override
    public void refresh() {
        List<SysDict> list = this.baseMapper.selectList(SysDict::getStatus, true);
        if (CollUtil.isEmpty(list)) {
            return;
        }
        List<String> codeList = list.stream().map(SysDict::getCode).distinct().toList();
        this.dictLoadService.refreshCache(getPairMap(codeList));
    }

    private Map<String, List<Pair<String, String>>> getPairMap(List<String> codeList) {
        return this.sysDictItemMapper.selectList(Wraps.<SysDictItem>lbQ()
                        .eq(SysDictItem::getStatus, true))
                .stream()
                .collect(groupingBy(
                        SysDictItem::getDictCode,
                        Collectors.mapping(item -> Pair.of(item.getValue(), item.getLabel()), Collectors.toList())));
    }

    @Override
    public List<Dict<String>> findItemByCode(String code) {
        Map<Object, Object> map = this.dictLoadService.findByIds(code);
        if (CollUtil.isEmpty(map)) {
            return null;
        }
        List<Dict<String>> dictList = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            dictList.add(new Dict<>(entry.getKey() + "", entry.getValue() + ""));
        }
        return dictList;
    }

}
