/**
 * @Project:emms
 * @Package:com.szewec.emms.workflow.service.impl 
 * @FileName:LeaveWorkflowServiceImpl.java 
 * @Date:2013-9-10 下午2:17:51 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.workflow.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.Page;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emmsframework.common.page.WorkFlowPage;
import com.emmsframework.workflow.entity.Leave;
import com.emmsframework.workflow.service.LeaveWorkflowService;

/** 
 * @ClassName:LeaveWorkflowServiceImpl 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-10 下午2:17:51 
 * @Since:V 1.0 
 */
@Service("leaveWorkflowService")
@Transactional
public class LeaveWorkflowServiceImpl implements
		LeaveWorkflowService
{

	@Autowired
	public ICommonDao commonDao;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(LeaveWorkflowServiceImpl.class);

	/* (non-Javadoc) 
	 * @see com.szewec.emms.workflow.service.LeaveWorkflowService#startWorkflow(com.szewec.emms.workflow.entity.Leave, java.util.Map) 
	 */
	@Override
	public ProcessInstance startWorkflow(Leave leave,
			Map<String, Object> variables) {
		if (StringUtil.isEmpty(leave.getId())) {
			leave.setId(com.emmsframework.common.id.UUIDGenerator.getUUID());
		}

		String businessKey = leave.getId();
		logger.debug("start workflow,businessKey=" + businessKey);

		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(leave.getUserId());

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("leave", businessKey, variables);
		String processInstanceId = processInstance.getId();

		leave.setProcessInstanceId(processInstanceId);
		logger.debug("save leave:" + leave);
		commonDao.save(leave);

		return processInstance;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.emmsframework.workflow.service.LeaveWorkflowService#findTodoTasks
	 * (java.lang.String, com.emmsframework.common.page.WorkFlowPage,
	 * org.activiti.engine.impl.Page)
	 */
	@Override
	public void queryTaskById(String userId, WorkFlowPage<Leave> workFlowPage,
			Page page) {
		TaskQuery taskQuery = taskService.createTaskQuery()
				.taskAssignee(userId);
		List<Task> list = taskQuery.list();
		for (Task task : list) {
			logger.debug(ToStringBuilder.reflectionToString(task));
		}

	}
}
