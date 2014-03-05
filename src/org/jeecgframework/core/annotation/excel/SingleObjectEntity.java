/**
 * @Project:emms
 * @Package:org.jeecgframework.core.util.excel 
 * @FileName:SingleObjectEntity.java 
 * @Date:2013-9-4 下午2:56:06 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package org.jeecgframework.core.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName:SingleObjectEntity
 * @Description:判断对象中的某个对象属性导出哪些字段
 * @Author:joe
 * @Date:2013-9-4 下午2:56:06
 * @Since:V 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SingleObjectEntity
{
	public String exportField() default "";;
}
