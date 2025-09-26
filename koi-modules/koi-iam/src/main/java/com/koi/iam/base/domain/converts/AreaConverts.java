package com.koi.iam.base.domain.converts;

import com.koi.common.core.entity.BaseConverts;
import com.koi.iam.base.domain.dto.resp.AreaNodeResp;
import com.koi.iam.base.domain.entity.AreaEntity;
import com.koi.iam.base.domain.dto.req.AreaReq;
import org.springframework.beans.BeanUtils;

/**
 * 区域转换
 */
public class AreaConverts {

    public static final AreaDto2PoConverts AREA_DTO_2_PO_CONVERTS = new AreaDto2PoConverts();
    public static final AreaEntity2NodeRespConverts AREA_ENTITY_2_NODE_RESP_CONVERTS = new AreaEntity2NodeRespConverts();

    public static class AreaDto2PoConverts implements BaseConverts<AreaReq, AreaEntity> {

        @Override
        public AreaEntity convert(AreaReq source) {
            if (source == null) {
                return null;
            }
            AreaEntity target = new AreaEntity();
            BeanUtils.copyProperties(source, target);
            target.setId(source.getId());
            return target;
        }
    }

    public static class AreaEntity2NodeRespConverts implements BaseConverts<AreaEntity, AreaNodeResp> {

        @Override
        public AreaNodeResp convert(AreaEntity source) {
            if (source == null) {
                return null;
            }
            return AreaNodeResp.builder().value(source.getId()).label(source.getName()).parentId(source.getParentId())
                    .level(source.getLevel()).latitude(source.getLatitude()).longitude(source.getLongitude())
                    .isLeaf(source.getLevel() != null && source.getLevel() > 2).build();
        }
    }

}
