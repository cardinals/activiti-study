/**
 * @Project:activiti
 * @Package:PageUtil 
 * @FileName:PageUtil.java 
 * @Date:2013-9-17 下午4:56:43 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.common.page;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/** 
 * @ClassName:PageUtil 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-17 下午4:56:43 
 * @Since:V 1.0 
 */
public class PageUtil
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(PageUtil.class);

	public static int PAGE_SIZE = 15;

	public static Page init(WorkFlowPage<?> page, HttpServletRequest request) {
		int pageNumber = Integer.parseInt(StringUtils.defaultIfBlank(
				request.getParameter("p"), "1"));
		page.setPageNo(pageNumber);
		int pageSize = Integer.parseInt(StringUtils.defaultIfBlank(
				request.getParameter("ps"), String.valueOf(PAGE_SIZE)));
		page.setPageSize(pageSize);
		int firstResult = page.getFirst() - 1;
		int maxResults = page.getPageSize();
		Page activitPage = new Page(firstResult, maxResults);
		return activitPage;
	}
}
