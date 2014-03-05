/**
 * @Project:activiti
 * @Package:com.emmsframework.activiti 
 * @FileName:ProcessInstanceControllerTestCase.java 
 * @Date:2013-9-17 下午5:25:52 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.activiti;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.emmsframework.activiti.engine.ProcessEngineTestCase;

/** 
 * @ClassName:ProcessInstanceControllerTestCase 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-17 下午5:25:52 
 * @Since:V 1.0 
 */
public class ProcessInstanceControllerTestCase extends ProcessEngineTestCase
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(ProcessInstanceControllerTestCase.class);

	@Test
	public void testtoProcessInstanceList() {
		request.setRequestURI("/procInstanceController/toProcessInstanceList");
		this.excuteAction(request, response);
	}
}
