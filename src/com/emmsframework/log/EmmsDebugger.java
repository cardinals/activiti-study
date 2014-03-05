/** 
 * @Project:emms
 * @Package:com.szewec.emms.common 
 * @FileName:EmmsDebugger.java 
 * @Author:caiwu
 * @Date:2013-7-22 下午2:23:34 
 * @Version V1.0
 *  
 */
package com.emmsframework.log;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName:EmmsDebugger
 * @Description:TODO
 * 
 * @Author:caiwu
 * @Date:2013-7-22 下午2:23:34
 * @Since:V 1.0
 */
public class EmmsDebugger
{

	private static String module = "[EMMS]";

	private final Logger logger;

	private static final char VERTICAL = '|';

	public EmmsDebugger(Class<?> classz) {
		logger = LoggerFactory.getLogger(classz);

	}

	public EmmsDebugger(Class<?> classz, String mod) {
		module = '[' + mod + ']';
		logger = LoggerFactory.getLogger(classz);
	}

	// -------------------
	public void enterFunction(Object... objs) {
		if (isDebugger()) {
			debug(objs);
		}
	}

	// int,List<String>
	public void debug(Object... objs) {
		if (logger.isDebugEnabled()) {
			if (objs == null) {
				debug("object is null!");
			}
			else {
				StringBuffer sb = new StringBuffer(objs.length);
				sb.append('{');
				for (Object object : objs) {
					sb.append('[').append(parser(object)).append(']');
				}
			}
		}

	}

	public void debug() {
		if (logger.isDebugEnabled()) {
			logger.debug("");
		}

	}

	public void debug(Object object) {
		if (logger.isDebugEnabled()) {

			if (object instanceof List) {
				List list = (List) object;
				for (Object obj : list) {
					debug(obj);
				}
			}
			else if (object instanceof Set) {
				Set set = (Set) object;
				for (Object obj : set) {
					debug(obj);
				}
			}
			else if (object instanceof Object[]) {
				logger.debug(parserArray((Object[]) object));
			}
			else {
				logger.debug(parser(object));
			}
		}
	}

	public static void main(String[] args) {
	}

	private static String parserArray(Object[] objects) {
		if (objects == null || objects.length <= 0) {
			return module + VERTICAL + "";
		}
		StringBuffer sb = new StringBuffer(objects.length);
		for (Object object : objects) {
			sb.append(object).append(',');
		}

		return new String(sb.substring(0, sb.length() - 1));
	}

	private static String parser(Object object) {
		if (object == null) {
			return module + VERTICAL + "null";
		}
		else {
			return module + VERTICAL + object.toString();
		}
	}

	public void info(Object object) {
		if (logger.isInfoEnabled()) {
			logger.info(parser(object));
		}
	}

	public void error(Object object) {
		if (logger.isErrorEnabled()) {
			logger.error(parser(object));
		}
	}

	public void error(Object... object) {
		if (logger.isErrorEnabled()) {
			for (Object obj : object) {
				error(obj);
			}
		}
	}

	public void warn(Object object) {
		if (logger.isWarnEnabled()) {
			logger.warn(parser(object));
		}
	}

	private boolean isDebugger() {
		return logger.isDebugEnabled();
	}

}
