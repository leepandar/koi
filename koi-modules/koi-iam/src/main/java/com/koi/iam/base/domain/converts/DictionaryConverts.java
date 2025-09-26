package com.koi.iam.base.domain.converts;

import com.koi.common.db.mybatisplus.page.BasePageConverts;
import com.koi.iam.base.domain.entity.SysDictItem;
import com.koi.iam.base.domain.dto.req.DictItemSaveReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 字典转换
 */
@Slf4j
public class DictionaryConverts {

    public static final DictionaryItemDto2ItemPoConverts DICTIONARY_ITEM_DTO_2_ITEM_PO_CONVERTS = new DictionaryItemDto2ItemPoConverts();

    public static class DictionaryItemDto2ItemPoConverts implements BasePageConverts<DictItemSaveReq, SysDictItem> {

        @Override
        public SysDictItem convert(DictItemSaveReq source) {
            if (source == null) {
                return null;
            }
            SysDictItem target = new SysDictItem();
            BeanUtils.copyProperties(source, target);
            return target;
        }
    }

}
