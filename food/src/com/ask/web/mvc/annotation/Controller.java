package com.ask.web.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 所有的控制器类需此注解标注，在系统启动具有此注解的类将会被扫描并实例化
 *
 * @author 张建平 on 2018/8/29 10:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
