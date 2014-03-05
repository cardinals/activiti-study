/**
 * @Project:emms
 * @Package:com.szewec.emms.common.util 
 * @FileName:SessionUtil.java 
 * @Date:2013-9-10 上午11:22:54 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.common.util;

import jeecg.system.pojo.base.TSUser;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;

/** 
 * @ClassName:SessionUtil 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-10 上午11:22:54 
 * @Since:V 1.0 
 */
public class UserUtil
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(UserUtil.class);

	/**
	 * 
	 * @Title:getUserId
	 * @Desc:从request中的session获取当前用户的ID
	 * @param request
	 * @return:String
	 * @exception
	 * @since 1.0.0
	 */
	public static String getUserId() {
		TSUser user = ResourceUtil.getSessionUserName();
		return user == null ? null : user.getId();

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
