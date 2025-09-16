package com.koi.iam.system.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.iam.auth.domain.entity.RegisteredClient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lida
 */
@Repository
public interface RegisteredClientMapper extends SuperMapper<RegisteredClient> {

    /**
     * 根据ID 删除
     *
     * @param id id
     */
    @Delete("delete from sys_registered_client where id = #{id}")
    void removeById(@Param("id") String id);
}
