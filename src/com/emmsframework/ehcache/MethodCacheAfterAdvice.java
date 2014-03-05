/**
 * @Project:emms
 * @Package:com.emmsframework.ehcache 
 * @FileName:MethodCacheAfterAdvice.java 
 * @Date:2013-9-6 上午9:58:55 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.ehcache;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;

/** 
 * @ClassName:MethodCacheAfterAdvice 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-6 上午9:58:55 
 * @Since:V 1.0 
 */
public class MethodCacheAfterAdvice implements AfterReturningAdvice,
		InitializingBean

{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(MethodCacheAfterAdvice.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public MethodCacheAfterAdvice() {
		super();
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
	 * org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang
	 * .Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		String className = target.getClass().getName();
		List<?> list = cache.getKeys();
		for (int i = 0; i < list.size(); i++) {
			String cacheKey = String.valueOf(list.get(i));
			if (cacheKey.startsWith(className)) {
				cache.remove(cacheKey);
				logger.debug("remove cache " + cacheKey);
			}

		}

	}
}
