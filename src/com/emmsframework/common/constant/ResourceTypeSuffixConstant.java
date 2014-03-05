/**
 * @Project:emms
 * @Package:com.emmsframework.common.constant 
 * @FileName:ResourceTypeSuffixConstant.java 
 * @Date:2013-9-9 下午5:34:47 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.common.constant;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.StringUtil;

/** 
 * @ClassName:ResourceTypeSuffixConstant 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-9 下午5:34:47 
 * @Since:V 1.0 
 */
public class ResourceTypeSuffixConstant
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(ResourceTypeSuffixConstant.class);

	public static final String IMAGE = "image";

	public static final String XML = "xml";

	public static boolean isXML(String name) {
		if (StringUtil.isEmpty(name)) {
			return false;
		}
		if (name.trim().contains(XML)) {
			return true;
		}
		else {
			return false;
		}

	}

	public static boolean isImage(String name) {
		if (StringUtil.isEmpty(name)) {
			return false;
		}
		if (name.trim().contains(IMAGE)) {
			return true;
		}
		else {
			return false;
		}

	}

	/** 
	 * @Title:main
	 * @Desc:TODO
	 * @param args  
	 * @return:void 
	 * @exception
	 * @since  1.0.0 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
