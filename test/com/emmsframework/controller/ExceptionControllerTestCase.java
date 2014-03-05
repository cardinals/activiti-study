/**
 * @Project:emms
 * @Package:com.emmsframework.controller 
 * @FileName:ExceptionControllerTestCase.java 
 * @Date:2013-8-28 下午3:01:54 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.emmsframework.demo.ExceptionController;

/**
 * @ClassName:ExceptionControllerTestCase
 * 
 * @Author:joe
 * @Date:2013-8-28 下午3:01:54
 * @Since:V 1.0
 */
public class ExceptionControllerTestCase extends BaseControllerTestCase
{
	@Autowired
	private ExceptionController controller;

	@Test
	public void testNumberException() throws Exception {
		request.setRequestURI("/exceptionController/number.do");

		final ModelAndView mav = this.excuteAction(request, response);
	}
}
