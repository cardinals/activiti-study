/**
 * @Project:emms
 * @Package:org.jeecgframework.core.annotation.excel 
 * @FileName:Dictionary.java 
 * @Date:2013-9-4 下午4:50:10 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package org.jeecgframework.core.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName:Dictionary
 * @Description:字段是使用字典的标识
 * @Author:joe
 * @Date:2013-9-4 下午4:50:10
 * @Since:V 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dictionary
{
	public String exportDictionary();
}
