/**
 * @Project:emms
 * @Package:com.szewec.emms.workflow.controller 
 * @FileName:LeaveControllerTestCase.java 
 * @Date:2013-9-10 下午2:32:05 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.workflow.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.emmsframework.controller.BaseControllerTestCase;
import com.emmsframework.workflow.entity.Leave;

/** 
 * @ClassName:LeaveControllerTestCase 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-10 下午2:32:05 
 * @Since:V 1.0 
 */
public class LeaveControllerTestCase extends BaseControllerTestCase
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(LeaveControllerTestCase.class);

	static String LEAVE_CONTROLLER = "/leaveController";

	@Autowired
	private LeaveController leaveController;

	@Test
	public void testStartLeave() {

		Leave leave = new Leave();
		leave.setApplyTime(new Date());
		leave.setStartTime(new jodd.datetime.JDateTime("2012-05-22")
				.convertToSqlDate());
		leave.setEndTime(new jodd.datetime.JDateTime("2012-05-23")
				.convertToSqlDate());
		leave.setLeaveType("公休");
		leave.setReason("no reason1");

		leaveController.startWorkflow(leave, request, response);

	}
	
	@Test
	public void testMyTask(){
		
		request.setRequestURI("/leaveController/queryMyTask");
		this.excuteAction(request, response);
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
