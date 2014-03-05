/**
 * @Project:emms
 * @Package:com.emmsframework.ehcache 
 * @FileName:MethodCacheInterceptor.java 
 * @Date:2013-9-6 上午9:58:07 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.ehcache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/** 
 * @ClassName:MethodCacheInterceptor 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-6 上午9:58:07 
 * @Since:V 1.0 
 */
public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(MethodCacheInterceptor.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * create new instance:MethodCacheInterceptor.
	 * 
	 */
	public MethodCacheInterceptor() {
		super();
	}

	/** 
	 * @Title:main
	 * 
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
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (cache == null) {
			logger.error("Need a cache. Please use setCache(Cache) create it.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept
	 * .MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		Object result;

		logger.info("Find object from cache is " + cache.getName());
		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Element element = cache.get(cacheKey);

		if (element == null) {
			logger.info("Can't find result in Cache , Get method result and create cache........!");
			result = invocation.proceed();
			element = new Element(cacheKey, (Serializable) result);
			cache.put(element);
		}
		else {
			logger.info("Find result in Cache , Get method result and create cache........!");
		}
		return element.getValue();
	}

	/**
	 * @Title:getCacheKey
	 * @Desc:TODO
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 * @return
	 * @return:String
	 * @exception
	 * @since 1.0.0
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sBuffer.append(".").append(arguments[i]);
			}
		}
		logger.debug("cachekey:" + sBuffer.toString());
		return sBuffer.toString();
	}
}
