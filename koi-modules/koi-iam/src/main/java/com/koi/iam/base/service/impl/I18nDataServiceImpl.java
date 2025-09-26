package com.koi.iam.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.i18n.I18nMessageProvider;
import com.koi.common.i18n.domain.I18nMessage;
import com.koi.iam.base.mapper.I18nDataMapper;
import com.koi.iam.base.mapper.I18nLocaleMessageMapper;
import com.koi.iam.base.domain.dto.req.I18nDataSaveReq;
import com.koi.iam.base.domain.dto.req.I18nPageReq;
import com.koi.iam.base.domain.dto.resp.I18nDataPageResp;
import com.koi.iam.base.domain.entity.I18nData;
import com.koi.iam.base.domain.entity.I18nLocaleMessage;
import com.koi.iam.base.service.I18nDataService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class I18nDataServiceImpl extends SuperServiceImpl<I18nDataMapper, I18nData> implements I18nDataService {

    private final I18nDataMapper i18nDataMapper;
    private final I18nLocaleMessageMapper i18nLocaleMessageMapper;
    private final I18nMessageProvider i18nMessageProvider;
    private final AuthenticationContext context;

    @PostConstruct
    public void init() {
        List<I18nMessage> messages = i18nDataMapper.loadI18nMessage();
        log.debug("从数据库加载国际化数据 - {}", JSON.toJSONString(messages));
        i18nMessageProvider.loadI18nMessage(messages);
    }

    /**
     * 分页查询
     *
     * @param req req
     * @return
     */
    @Override
    public IPage<I18nDataPageResp> pageList(I18nPageReq req) {
        final IPage<I18nDataPageResp> page = this.baseMapper.selectPage(req.buildPage(),
                        Wraps.<I18nData>lbQ().like(I18nData::getCode, req.getCode()))
                .convert(x -> BeanUtil.toBean(x, I18nDataPageResp.class));
        final List<Long> i18nIdList = page.getRecords().stream().map(I18nDataPageResp::getId).toList();
        if (CollUtil.isEmpty(i18nIdList)) {
            return page;
        }
        final Map<Long, List<I18nLocaleMessage>> map = this.i18nLocaleMessageMapper.selectList(Wraps.<I18nLocaleMessage>lbQ().in(I18nLocaleMessage::getParentId, i18nIdList))
                .stream().collect(groupingBy(I18nLocaleMessage::getParentId, toList()));
        for (I18nDataPageResp record : page.getRecords()) {
            record.setLanguages(map.get(record.getId()));
        }
        return page;
    }

    /**
     * 添加 i18n 数据
     *
     * @param req req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void add(I18nDataSaveReq req) {
        final I18nData i18nData = I18nData.builder().code(req.getCode()).remark(req.getRemark()).tenantId(context.tenantId()).build();
        this.baseMapper.insert(i18nData);
        final List<?> list = req.getLanguages()
                .stream()
                .map(lang -> I18nLocaleMessage.builder()
                        .parentId(i18nData.getId()).locale(lang.getLocale()).message(lang.getMessage()).build())
                .toList();
        this.i18nLocaleMessageMapper.insertBatchSomeColumn(list);
    }

    /**
     * 编辑I18N数据
     *
     * @param id  id
     * @param req req
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void edit(Long id, I18nDataSaveReq req) {
        this.i18nLocaleMessageMapper.delete(Wraps.<I18nLocaleMessage>lbQ().eq(I18nLocaleMessage::getParentId, id));
        this.baseMapper.updateById(I18nData.builder().id(id).code(req.getCode()).remark(req.getRemark()).build());
        final List<?> list = req.getLanguages()
                .stream()
                .map(lang -> I18nLocaleMessage.builder()
                        .parentId(id).locale(lang.getLocale()).message(lang.getMessage()).build())
                .toList();
        this.i18nLocaleMessageMapper.insertBatchSomeColumn(list);
    }

    /**
     * 删除i18n数据
     *
     * @param id
     * @return
     */
    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        this.i18nLocaleMessageMapper.delete(Wraps.<I18nLocaleMessage>lbQ().eq(I18nLocaleMessage::getParentId, id));
        return this.baseMapper.deleteById(id) > 0;
    }
}
