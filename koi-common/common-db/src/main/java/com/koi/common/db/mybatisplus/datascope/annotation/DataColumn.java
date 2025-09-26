package com.koi.common.db.mybatisplus.datascope.annotation;

import com.koi.common.core.entity.Entity;
import com.koi.common.core.security.DataResourceType;
import com.koi.common.core.security.DataScopeType;

import java.lang.annotation.*;

/**
 * 数据权限字段注解
 *
 * @author lida
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataColumn {

    /**
     * 表别名
     *
     * @return 别名
     */
    String alias() default "";

    /**
     * 字段名称
     *
     * @return 字段名称
     */
    String name() default Entity.CREATE_USER_COLUMN;

    Class<?> javaClass() default Long.class;

    /**
     * 权限资源范围(默认创建人)
     *
     * @return 资源
     */
    DataResourceType resource() default DataResourceType.USER;

    /**
     * 权限范围（默认跟随系统，如果指定了就跟着指定走）
     */
    DataScopeType scopeType() default DataScopeType.IGNORE;

}
