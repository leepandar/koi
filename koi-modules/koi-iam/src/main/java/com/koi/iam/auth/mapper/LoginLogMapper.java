package com.koi.iam.auth.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.auth.domain.entity.LoginLog;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 系统日志
 * </p>
 *
 * @author lida
 */

@Repository
public interface LoginLogMapper extends SuperMapper<LoginLog> {

    /**
     * 统计 IP 数据
     *
     * @return 统计结果
     */
    @Select("SELECT count(DISTINCT ( ip )) FROM c_login_log")
    long countDistinctLoginIp();

}
