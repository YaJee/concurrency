package com.mmall.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记【线程不安全】的类或写法
 */
@Target(ElementType.TYPE)               //注解作用的目标，ElementType.TYPE：对类做注解
@Retention(RetentionPolicy.SOURCE)      //注解的范围，SOURCE：编译时被忽略，仅起代码提醒功能
public @interface NotThreadSafe {
    String value()default "";
}
