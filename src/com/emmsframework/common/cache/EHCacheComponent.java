/**
 * @Project:emms
 * @Package:com.emmsframework.common.cache 
 * @FileName:EHCacheComponent.java 
 * @Date:2013-9-6 上午10:00:10 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.common.cache;

import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 
 * @ClassName:EHCacheComponent 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-6 上午10:00:10 
 * @Since:V 1.0 
 */
@Component
public class EHCacheComponent
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(EHCacheComponent.class);

	// @Autowired
	// private RoadLineServiceI roadLineService;
	//
	// public List<RoadLineEntity> cacheFindRoadLine() throws Exception {
	// return roadLineService.getList(RoadLineEntity.class);
	// }

	@Autowired
	private SystemService service;

	public java.util.List<TSUser> cacheFindUser() {
		return service.getList(TSUser.class);
	}

	public TSUser cacheGetUserById(String id) {
		return service.getEntity(TSUser.class, id);
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
