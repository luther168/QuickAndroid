package cn.luo.android.quick.library.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: Luther
 * created on: 2017/6/4 13:43
 * description:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Content {
    Class fragment();
}
