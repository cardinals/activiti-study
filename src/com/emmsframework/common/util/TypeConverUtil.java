/**
 * @Project:emms
 * @Package:com.szewec.emms.common.util 
 * @FileName:TypeConverUtil.java 
 * @Date:2013-9-5 下午3:05:37 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.common.util;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.StringUtil;

/**
 * @ClassName:TypeConverUtil
 * @Desc:数据类型转换工具类
 * @Author:joe
 * @Date:2013-9-5 下午3:05:37
 * @Since:V 1.0
 */
public class TypeConverUtil
{
	/**
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(TypeConverUtil.class);

	public static String objectToString(Object object) {
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	public static Integer objectToInteger(Object object) {
		if (StringUtil.isEmpty(objectToString(object))) {
			return null;
		}
		try {

			return Integer.parseInt(objectToString(object));
		}
		catch (NumberFormatException e) {
			logger.error("convert value(" + object + ") to Integer failed.");
			throw e;
		}
	}

	public static Long objectToLong(Object object) {
		if (StringUtil.isEmpty(objectToString(object))) {
			return null;
		}
		try {

			return Long.parseLong(objectToString(object));
		}
		catch (NumberFormatException e) {
			logger.error("convert value(" + object + ") to Integer failed.");
			throw e;
		}
	}

	public static Float objectToFloat(Object object) {
		if (StringUtil.isEmpty(objectToString(object))) {
			return null;
		}
		try {

			return Float.parseFloat(objectToString(object));
		}
		catch (NumberFormatException e) {
			logger.error("convert value(" + object + ") to parseFloat failed.");
			throw e;
		}
	}

	public static Double objectToDouble(Object object) {
		if (StringUtil.isEmpty(objectToString(object))) {
			return null;
		}
		try {

			return Double.parseDouble(objectToString(object));
		}
		catch (NumberFormatException e) {
			logger.error("convert value(" + object + ") to Double failed.");
			throw e;
		}
	}

	public static BigDecimal objectToBigDecimal(Object object) {
		if (StringUtil.isEmpty(objectToString(object))) {
			return null;
		}
		try{
			return new BigDecimal(objectToString(object));
		}
		catch (NumberFormatException e) {
			logger.error("convert value(" + object + ") to BigDecimal failed.");
			throw e;
		}
	}

	/** 
	 * @Title:main
	 * 
	 * @param args  
	 * @return:void 
	 * @exception
	 * @since  1.0.0 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
