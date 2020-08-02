package cn.luo.android.quick.library.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author      Hurston
 * @version     1.0.0
 * @description
 * @createdTime 2018/11/28 16:15
 * @note
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Content {
    Class<?> fragment();
}
