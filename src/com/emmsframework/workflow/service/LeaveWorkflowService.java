/**
 * @Project:emms
 * @Package:com.szewec.emms.workflow.service 
 * @FileName:LeaveWorkflowService.java 
 * @Date:2013-9-10 上午11:33:34 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.workflow.service;

import java.util.Map;

import org.activiti.engine.impl.Page;
import org.activiti.engine.runtime.ProcessInstance;

import com.emmsframework.common.page.WorkFlowPage;
import com.emmsframework.workflow.entity.Leave;

/** 
 * @ClassName:LeaveWorkflowService 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-10 上午11:33:34 
 * @Since:V 1.0 
 */
public interface LeaveWorkflowService
{

	/**
	 * @Title:startWorkflow
	 * @Desc:TODO
	 * @param leave
	 * @param variables
	 * @return
	 * @return:ProcessInstance
	 * @exception
	 * @since 1.0.0
	 */
	ProcessInstance startWorkflow(Leave leave, Map<String, Object> variables);

	/**
	 * @Title:findTodoTasks
	 * @Desc:TODO
	 * @param userId
	 * @param workFlowPage
	 * @param page
	 * @return:void
	 * @exception
	 * @since 1.0.0
	 */
	void queryTaskById(String userId, WorkFlowPage<Leave> workFlowPage,
			Page page);

}
