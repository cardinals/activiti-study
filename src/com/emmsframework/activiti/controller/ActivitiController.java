/**
 * @Project:emms
 * @Package:com.emmsframework.activiti.controller 
 * @FileName:ActivitiController.java 
 * @Date:2013-8-19 上午10:13:45 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.activiti.controller;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName:ActivitiController
 * 
 * @Author:joe
 * @Date:2013-8-19 上午10:13:45
 * @Since:V 1.0
 */
public abstract class ActivitiController extends BaseController
{

	private static final Logger LOGGER = Logger
			.getLogger(ActivitiController.class);

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected FormService formService;

	@Autowired
	protected IdentityService identityService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	protected HistoryService historyService;

	@Autowired
	protected ManagementService managementService;

	@Autowired
	protected ProcessEngineFactoryBean processEngine;


	/**
	 * @Title:main
	 * 
	 * @param args
	 * @return:void
	 * @exception
	 * @since 1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
