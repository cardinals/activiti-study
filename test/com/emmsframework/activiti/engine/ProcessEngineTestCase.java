/**
 * @Project:emms
 * @Package:com.emmsframework.activiti.engine 
 * @FileName:ProcessEngineTest.java 
 * @Date:2013-8-16 下午3:34:47 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.activiti.engine;

import static org.junit.Assert.assertNotNull;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.emmsframework.controller.BaseControllerTestCase;

/**
 * @ClassName:ProcessEngineTest
 * 
 * @Author:joe
 * @Date:2013-8-16 下午3:34:47
 * @Since:V 1.0
 */
public class ProcessEngineTestCase extends BaseControllerTestCase
{
	@Autowired
	private ProcessEngineFactoryBean engine;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private FormService formService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ManagementService managementService;

	/**
	 * @Fileds:日志记录
	 * 
	 */
	/**
	 * 检测引擎是否能正常工作
	 */
	@Test
	public void testProcessEngines() {
		assertNotNull(engine);
		assertNotNull(repositoryService);
		assertNotNull(runtimeService);
		assertNotNull(formService);
		assertNotNull(identityService);
		assertNotNull(taskService);
		assertNotNull(historyService);
		assertNotNull(managementService);
	}

	public void test() {
	}
}
