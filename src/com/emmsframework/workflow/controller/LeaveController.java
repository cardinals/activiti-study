/**
 * @Project:emms
 * @Package:com.emmsframework.business 
 * @FileName:LeaveContoller.java 
 * @Date:2013-9-10 上午11:01:30 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.workflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.Page;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emmsframework.activiti.controller.ActivitiController;
import com.emmsframework.common.constant.ModelAndViewConstant;
import com.emmsframework.common.page.PageUtil;
import com.emmsframework.common.page.WorkFlowPage;
import com.emmsframework.common.util.UserUtil;
import com.emmsframework.workflow.entity.Leave;
import com.emmsframework.workflow.service.LeaveWorkflowService;

/** 
 * @ClassName:LeaveContoller 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-10 上午11:01:30 
 * @Since:V 1.0 
 */
@Controller
@RequestMapping(value = "/leaveController")
public class LeaveController extends ActivitiController
{
	@Autowired
	private LeaveWorkflowService workflowService;
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(LeaveController.class);

	@RequestMapping(value = { "apply", "" })
	public ModelAndView toAddLeave(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView(ModelAndViewConstant.WORKFLOW + "/leaveApply");
	}

	/**
	 * 启动请假流程
	 * 
	 * @param leave
	 */
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String startWorkflow(Leave leave, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			leave.setUserId(UserUtil.getUserId());
			Map<String, Object> variables = new HashMap<String, Object>();
			ProcessInstance processInstance = workflowService.startWorkflow(
					leave, variables);
			message = "流程已启动，流程ID：" + processInstance.getId();
			logger.debug(message);
		}
		catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn("没有部署流程!", e);
				message = "没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>";
			}
			else {
				logger.error("启动请假流程失败：", e);
				message = "系统内部错误！";
			}
		}
		catch (Exception e) {
			logger.error("启动请假流程失败：", e);
			message = "系统内部错误！";
		}
		return "redirect:/leave/apply";
	}

	/**
	 * 查找个人任务列表
	 * 
	 * @param leave
	 */
	@RequestMapping(value = "/queryMyTask")
	public ModelAndView queryMyTask(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(ModelAndViewConstant.WORKFLOW
				+ "taskList");
		WorkFlowPage<Leave> workFlowPage = new WorkFlowPage<Leave>(
				PageUtil.PAGE_SIZE);
		Page page = PageUtil.init(workFlowPage, request);
		workflowService.queryTaskById(UserUtil.getUserId(), workFlowPage, page);
		mav.addObject("page", workFlowPage);
		return mav;
	}

	/**
	 * 查找个人任务列表
	 * 
	 * @param leave
	 */
	@RequestMapping(value = "/queryMyTask")
	public ModelAndView queryUnTask(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(ModelAndViewConstant.WORKFLOW
				+ "taskList");
		WorkFlowPage<Leave> workFlowPage = new WorkFlowPage<Leave>(
				PageUtil.PAGE_SIZE);
		Page page = PageUtil.init(workFlowPage, request);
		workflowService.queryTaskById(UserUtil.getUserId(), workFlowPage, page);
		mav.addObject("page", workFlowPage);
		return mav;
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
