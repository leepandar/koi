package com.koi.iam.base.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.base.domain.entity.MessageChannel;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageChannelMapper extends SuperMapper<MessageChannel> {
}
