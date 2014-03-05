/**
 * @Project:emms
 * @Package:com.emmsframework.demo 
 * @FileName:ExceptionController.java 
 * @Date:2013-8-28 下午2:49:00 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName:ExceptionController
 * 
 * @Author:joe
 * @Date:2013-8-28 下午2:49:00
 * @Since:V 1.0
 */
@Controller
@RequestMapping("/exceptionController")
public class ExceptionController
{
	@RequestMapping(params = "null")
	public void testNullPointerException() {
		String str = null;
		// 这里就会发生空指针异常，然后就会返回定义在SpringMVC配置文件中的null视图
		System.out.println(str.toLowerCase());
	}

	public static void main(String[] args) {
		String string = null;
		System.out.println(string.toLowerCase());
	}

	@RequestMapping(params = "number")
	public void testNumberFormatException() {
		// 这里就会发生NumberFormatException，然后就会返回定义在SpringMVC配置文件中的number视图
		Integer.parseInt("abc");
	}

	@RequestMapping(params = "default")
	public void testDefaultException() {
		if (1 == 1)
			// 由于该异常类型在SpringMVC的配置文件中没有指定，所以就会返回默认的exception视图
			throw new RuntimeException("Error!");
	}

}
