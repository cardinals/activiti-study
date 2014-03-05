/**
 * @Project:emms
 * @Package:com.emmsframework.activiti.controller 
 * @FileName:ProcessDefController.java 
 * @Date:2013-9-6 下午4:04:38 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.activiti.controller;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.emmsframework.common.ServletResponseUtil;
import com.emmsframework.common.constant.ModelAndViewConstant;
import com.emmsframework.common.util.TypeConverUtil;
import com.emmsframework.context.FileTypeConstant;

/**
 * @ClassName:ProcessDefController
 * @Desc:流程定义控制器
 * @Author:joe
 * @Date:2013-9-6 下午4:04:38
 * @Since:V 1.0
 */
@Controller
@RequestMapping("/processDefController")
public class ProcessDefController extends ActivitiController
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(ProcessDefController.class);


	/**
	 * 
	 * @Title:toProcessDefList
	 * @Desc:跳转到流程定义列表页面
	 * @param request
	 * @return
	 * @return:ModelAndView
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/toProcessDefList")
	public ModelAndView toProcessDefList(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView(
				ModelAndViewConstant.WORKFLOW + "processDefList");
		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery().orderByDeploymentId().desc();

		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
				.list();

		modelAndView.addObject("processDefList", processDefinitionList);

		return modelAndView;
	}

	/**
	 * 部署新流程
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deploy", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson deployment(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		AjaxJson j = new AjaxJson();

		try {
			String fileName = file.getOriginalFilename();
			if (StringUtil.isEmpty(fileName)) {
				fileName = file.getName();
			}
			InputStream fileInputStream = file.getInputStream();

			Deployment deployment = null;

			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals(FileTypeConstant.ZIP)
					|| extension.equals(FileTypeConstant.BAR)) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment()
						.addZipInputStream(zip).deploy();
			}
			else {

				deployment = repositoryService.createDeployment()
						.addInputStream(fileName, fileInputStream).deploy();
			}

			message = "添加成功,部署的ID是" + deployment.getId();
			logger.debug(message);
		}
		catch (Exception e) {
			logger.error(
					"error on deploy process, because of file input stream", e);
			message = "部署失败";
			j.setSuccess(false);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 读取资源，包括xml或者图片，通过部署ID
	 * 
	 * @param processDefinitionId
	 *            流程定义
	 * @param resourceType
	 *            资源类型(xml|image)
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadResource")
	public void loadByDeployment(
			@RequestParam("processDefinitionId") String processDefinitionId,
			@RequestParam("resourceType") String resourceType,
			HttpServletResponse response) throws Exception {

		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		}
		else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		InputStream resourceAsStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), resourceName);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * 
	 * @param deploymentId
	 *            流程部署ID
	 */
	@RequestMapping(value = "/deleteDeployment")
	public void deleteDeployment(
			@RequestParam("deploymentId") String deploymentId) {
		logger.debug("delete process by deploymentId=" + deploymentId);
		repositoryService.deleteDeployment(deploymentId, true);

	}

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		logger.debug("pageIndex:" + pageIndex + ",pageSize:" + pageSize);

		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery().orderByDeploymentId().desc();

		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
				.listPage(TypeConverUtil.objectToInteger(pageIndex),
						TypeConverUtil.objectToInteger(pageSize));

		int total = Double.valueOf(processDefinitionQuery.count()).intValue();


		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", total);
		jsonObject.put("rows", processDefinitionList);

		ServletResponseUtil.response(jsonObject, response);

	}

}
