/**
 * @Project:emms
 * @Package:com.emmsframework.activiti 
 * @FileName:ProcessDefControllerTestCase.java 
 * @Date:2013-9-9 下午4:40:55 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.activiti;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emmsframework.activiti.controller.ProcessDefController;
import com.emmsframework.activiti.engine.ProcessEngineTestCase;

/**
 * @ClassName:ProcessDefControllerTestCase
 * @Desc:流程定义控制器测试
 * @Author:joe
 * @Date:2013-9-9 下午4:40:55
 * @Since:V 1.0
 */
public class ProcessDefControllerTestCase extends ProcessEngineTestCase
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(ProcessDefControllerTestCase.class);

	private static final String PROCESS_DEF = "/processDefController";

	static String filePath = "F:\\workspace\\activiti\\resources\\activiti\\deployments\\leave.bar";

	@Test
	public void testDeploy() throws Exception {

		FileInputStream fis = new FileInputStream(filePath);
		multipartRequest.setRequestURI(PROCESS_DEF + "/deploy");
		multipartRequest.setMethod(RequestMethod.POST.toString());
		
		MockMultipartFile multipartFile = new MockMultipartFile("leave","leave.bar","application/octet-stream",fis);
		
		multipartRequest.addFile(multipartFile);
		
		multipartRequest.setMethod("POST");
		multipartRequest.setContentType("multipart/form-data");
		multipartRequest.addHeader("Content-type", "multipart/form-data");

		multipartRequest.setAttribute("msg", "测试action成功");

		// ModelAndView mav = this.excuteAction(multipartRequest, response);

		ProcessDefController processDefController = context
				.getBean(ProcessDefController.class);

		AjaxJson json = processDefController.deployment(multipartRequest,
				response,
				multipartFile);

		Assert.assertEquals(200, response.getStatus());

		logger.debug(json.getMsg());
	}

	@Test
	public void testDelete() {
		request.setRequestURI(PROCESS_DEF + "/deleteDeployment");
		request.addParameter("deploymentId", "610");
		ModelAndView mav = this.excuteAction(request, response);
		logger.debug(mav.getViewName());
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
