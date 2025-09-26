package com.koi.common.core.annotation.remote;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static cn.hutool.core.text.CharSequenceUtil.EMPTY;

/**
 * <p>
 * 在某个对象的字段上标记该注解，该字段的值将被主动注入
 * </p>
 * 如：@Remote(bean = "userApiImpl")
 * private String userId;
 *
 * <p>
 * 强烈建议：不要对象之间互相依赖
 * 如： User 想要注入 File， File也想注入User
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
public @interface Remote {

    /**
     * @return 查询类的Spring Bean Class
     */
    Class<?> beanClass();

    /**
     * 规则
     *
     * @return 分隔符
     */
    String rule() default EMPTY;

    /**
     * 分组 Key (如果有group 则说明查询需要带上 group 并且需要根据其内容分组)
     *
     * @return 分组 Key
     */
    String tag() default EMPTY;

    /**
     * 回显到那个字段
     *
     * @return 回显到那个字段
     */
    FieldRef[] fields() default {};

    /**
     * 自动注入值的类型， 用于强制转换（配置 ref 可以忽略）
     *
     * @return 待强转的类
     */
    Class<?> objectClass() default Object.class;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @interface FieldRef {

        String source() default EMPTY;

        String target();
    }

}
