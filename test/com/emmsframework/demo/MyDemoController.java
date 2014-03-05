/**
 * @Project:emms
 * @Package:com.emmsframework.controller 
 * @FileName:DemoController.java 
 * @Date:2013-8-23 上午10:21:10 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.demo;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.system.pojo.base.TSUser;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:DemoController
 * 
 * @Author:joe
 * @Date:2013-8-23 上午10:21:10
 * @Since:V 1.0
 */
@Controller
@RequestMapping("/myDemoController")
public class MyDemoController
{

	@RequestMapping("/testValue")
	public ModelAndView testWithValue(HttpServletRequest request) {
		return new ModelAndView("testValue");
	}

	@RequestMapping(params = "params")
	public ModelAndView testWithParams(HttpServletRequest request) {
		return new ModelAndView("testparams");
	}

	@RequestMapping(value = "str")
	public String testWithString(HttpServletRequest request) {
		return "testString";
	}

	@RequestMapping(value = "/bb/aa/{dataSource}", method = RequestMethod.GET)
	public void datasource(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("dataSource") String dataSource) throws Exception {
		String result = "结果为";
		System.out.println("dataSource:" + dataSource);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.write(result + dataSource);
		out.close();

	}

	@RequestMapping(value = "ajax", params = "ajax")
	@ResponseBody
	public AjaxJson del(TSUser user, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		j.setMsg("message");
		return j;
	}

}
