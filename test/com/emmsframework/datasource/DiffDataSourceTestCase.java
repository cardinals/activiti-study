/**
 * @Project:emms
 * @Package:com.emmsframework.datasource 
 * @FileName:DiffDataSourceTest.java 
 * @Date:2013-8-23 下午6:23:17 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.datasource;

import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.UserService;

import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.emmsframework.controller.BaseControllerTestCase;
import com.emmsframework.log.EmmsDebugger;

/**
 * @ClassName:DiffDataSourceTest
 * 
 * @Author:joe
 * @Date:2013-8-23 下午6:23:17
 * @Since:V 1.0
 */
public class DiffDataSourceTestCase extends BaseControllerTestCase
{
	/**
	 * @Fileds:日志记录
	 * 
	 */
	private static final EmmsDebugger DEBUGGER = new EmmsDebugger(
			DiffDataSourceTestCase.class);

	@Autowired
	private UserService userService;

	@Test
	public void testDiffDataSource() {

		DataSourceContextHolder.clearDataSourceType();
		DataSourceContextHolder
				.setDataSourceType(DataSourceType.dataSource_jeecg);

		String id = "8a80848340e685120140e6f0007c000f";

		TSUser user = userService.getEntity(TSUser.class, id);

		System.out.println(user.getRealName());
		System.out.println(DataSourceContextHolder.getDataSourceType());

		DataSourceContextHolder
				.setDataSourceType(DataSourceType.dataSource_test);

		id = "8a8080ce40f21c100140f222ed47001a";

		TSUser user2 = userService.getEntity(TSUser.class, id);
		System.out.println(user2.getRealName());
		System.out.println(DataSourceContextHolder.getDataSourceType());
	}

	@Test
	public void testDataSource() throws Exception {

		request.setRequestURI("/testController/test");

		request.setAttribute("msg", "测试action成功");

		final ModelAndView mav = this.excuteAction(request, response);
		DEBUGGER.debug(mav.getViewName());

	}

}
