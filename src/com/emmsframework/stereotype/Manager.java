/**
 * @Project:emms
 * @Package:com.szewecframework.context.stereotype 
 * @FileName:Manager.java 
 * @Date:2013-8-15 下午3:51:48 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @ClassName:Manager 管理类
 * @Author:joe
 * @Date:2013-8-15 下午3:51:48
 * @Since:V 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Manager
{
	String value() default "";
}
