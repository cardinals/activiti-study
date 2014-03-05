/**
 * @Project:activiti
 * @Package:com.emmsframework.refect 
 * @FileName:RefectActions.java 
 * @Date:2013-9-18 下午4:12:16 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.refect;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/** 
 * @ClassName:RefectActions 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-18 下午4:12:16 
 * @Since:V 1.0 
 */
public class RefectActions
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(RefectActions.class);

	public void tempMethod(Long userId) {
		logger.debug("我是java反射测试方法，被invoke了" + userId);
	}

	public void temp() throws Exception {

		// 获得类的当前实例
		RefectActions refectActions = this;
		logger.debug(refectActions);

		// 获得指定类的新实例
		RefectActions refectActions2 = RefectActions.class.newInstance();

		logger.debug(refectActions2);

		// 通过类型获得类
		Class boolType = Boolean.class;
		logger.debug(boolType);

		// 通过变量获得类
		String stringExample = "";
		Class stringType = stringExample.getClass();
		logger.debug(stringType);

		// 由名字获得类
		Class<?> c = Class.forName("com.emmsframework.refect.RefectActions");
		logger.debug(c);

		Long userid = 9999L;
		Method method = RefectActions.class.getDeclaredMethod("tempMethod",
				userid.getClass());
		logger.debug(method);

		Long args[] = new Long[1];
		args[0] = userid;
		method.invoke(refectActions, args);
	}

	public static void main(String[] args) throws Exception {
		new RefectActions().temp();
	}
}
