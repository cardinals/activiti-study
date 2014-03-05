/**
 * @Project:activiti
 * @Package:com.emmsframework.test 
 * @FileName:TestMain.java 
 * @Date:2013-9-18 上午11:15:16 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.test;

import org.apache.log4j.Logger;

/** 
 * @ClassName:TestMain 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-18 上午11:15:16 
 * @Since:V 1.0 
 */
public class TestMain
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(TestMain.class);

	private static String a = new String("ab");

	public static void main(String[] args) {
		String s1 = "a";
		String s2 = "b";
		String s = s1 + s2;
		System.out.println(s == a);
		System.out.println(s.intern() == a);
		System.out.println(s.intern() == a.intern());
	}

}