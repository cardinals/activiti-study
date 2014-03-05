/**
 * @Project:emms
 * @Package:com.emmsframework.ecache 
 * @FileName:TestEhcache.java 
 * @Date:2013-9-6 上午10:30:14 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.cache;

import jeecg.system.pojo.base.TSUser;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import spring.SpringTxTestCase;

import com.emmsframework.common.cache.EHCacheComponent;

/** 
 * @ClassName:TestEhcache 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-6 上午10:30:14 
 * @Since:V 1.0 
 */
public class TestEhcache extends SpringTxTestCase
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(TestEhcache.class);

	@Autowired
	private EHCacheComponent component;

	@Test
	public void test() throws Exception {
		java.util.List<TSUser> list = component.cacheFindUser();
		for (TSUser tsUser : list) {
			logger.debug(tsUser);
			logger.debug(component.cacheGetUserById(tsUser.getId()));
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
