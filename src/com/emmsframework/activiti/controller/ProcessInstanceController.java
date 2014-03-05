/**
 * @Project:activiti
 * @Package:com.emmsframework.activiti.controller 
 * @FileName:ProcessInstanceController.java 
 * @Date:2013-9-17 下午5:22:30 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.activiti.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emmsframework.common.constant.ModelAndViewConstant;

/** 
 * @ClassName:ProcessInstanceController 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-17 下午5:22:30 
 * @Since:V 1.0 
 */
@Controller
@RequestMapping(value = "/procInstanceController")
public class ProcessInstanceController extends ActivitiController
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(ProcessInstanceController.class);

	@RequestMapping(value = "/toProcessInstanceList")
	public ModelAndView toProcessInstanceList(HttpServletRequest request) {

		List<ProcessInstance> processInstanceList = runtimeService
				.createProcessInstanceQuery().list();

		for (ProcessInstance processInstance : processInstanceList) {
			logger.debug(ToStringBuilder.reflectionToString(processInstance));
		}

		return new ModelAndView(ModelAndViewConstant.WORKFLOW
				+ "procInstanceList");
	}
}
