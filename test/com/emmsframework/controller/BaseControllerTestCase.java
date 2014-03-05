/**
 * @Project:emms
 * @Package:com.emmsframework.controller 
 * @FileName:JobControllerTest.java 
 * @Date:2013-8-19 下午3:27:48 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jeecg.system.pojo.base.TSUser;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.common.SessionInfo;
import org.jeecgframework.core.constant.Globals;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import spring.SpringTxTestCase;

import com.emmsframework.demo.MyDemoController;

/**
 * @ClassName:JobControllerTest
 * 
 * @Author:joe
 * @Date:2013-8-19 下午3:27:48
 * @Since:V 1.0
 */
public class BaseControllerTestCase extends SpringTxTestCase
{
	private static final Logger logger = Logger
			.getLogger(BaseControllerTestCase.class);

	protected static MockHttpServletRequest request;

	protected static MockMultipartHttpServletRequest multipartRequest;

	protected static MockHttpServletResponse response;

	private HandlerMapping handlerMapping;

	private HandlerAdapter handlerAdapter;

	@Autowired
	protected ApplicationContext context;

	@Autowired
	private MyDemoController demoController;

	static final String UTF_8 = "UTF-8";

	@BeforeClass
	public static void setUp() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding(UTF_8);

		multipartRequest = new MockMultipartHttpServletRequest();
		multipartRequest.setCharacterEncoding(UTF_8);

		response = new MockHttpServletResponse();


	}

	@Before
	public void before() {

		handlerMapping = context.getBean(DefaultAnnotationHandlerMapping.class);

		handlerAdapter = (HandlerAdapter) context.getBean(context
				.getBeanNamesForType(AnnotationMethodHandlerAdapter.class)[0]);
		intiLogin();
	}

	/**
	 * 执行request请求的action
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ModelAndView excuteAction(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 这里需要声明request的实际类型，否则会报错
			request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING,
					true);
			HandlerExecutionChain chain = handlerMapping.getHandler(request);
			Assert.assertNotNull(demoController);

			ModelAndView model = null;

			model = handlerAdapter
					.handle(request, response, chain.getHandler());
			logger.debug(model.getViewName());
			return model;
		}
		catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void testWithValue() throws Exception {

		request.setRequestURI("/myDemoController/testValue");

		request.setAttribute("msg", "测试action成功");

		final ModelAndView mav = this.excuteAction(request, response);

		Assert.assertEquals("testValue", mav.getViewName());

		String msg = (String) request.getAttribute("msg");
		System.out.println(msg);
	}

	/**
	 * 
	 * 初始化登录
	 */
	@Test
	public void intiLogin() {
		SessionInfo sessionInfo = new SessionInfo();

		String userId = "8a8080ce41156e59014115726b790013";

		TSUser user = systemService.get(TSUser.class, userId);
		sessionInfo.setUser(user);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 30);
		session.setAttribute(Globals.USER_SESSION, sessionInfo);

		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(
				request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

	}

}
