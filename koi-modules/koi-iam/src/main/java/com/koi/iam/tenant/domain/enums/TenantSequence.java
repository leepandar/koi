package com.koi.iam.tenant.domain.enums;

import com.koi.common.redis.sequence.Sequence;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TenantSequence implements Sequence {

    /**
     * 规费编号
     */
    PRODUCT_DEFINITION_NO("tenant:product-definition", "PD");

    public final String key;
    public final String prefix;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String prefix() {
        return prefix;
    }
}
