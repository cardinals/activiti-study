/**
 * @Project:emms
 * @Package:com.szewec.emms.common 
 * @FileName:ServletResponse.java 
 * @Author:Coria
 * @Date:2013-7-24 下午3:48:59 
 * @Version V1.0
 *  
 */
package com.emmsframework.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * @ClassName:ServletResponse
 * 
 * @Author:Coria
 * @Date:2013-7-24 下午3:48:59
 * @Since:V 1.0
 */
public class ServletResponseUtil
{
	/**
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger LOGGER = Logger
			.getLogger(
			ServletResponseUtil.class);

	public static void response(JSONObject jsonObject,
			HttpServletResponse response) throws Exception {
		// response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json");
		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// out.print(json);
		// out.flush();
		// out.close();

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsonObject.toString());
			pw.flush();
			pw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Title:sendSuccess
	 * 
	 * @param string
	 * @return:void
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public static void sendSuccessMsg(String msg, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("message", msg);
		ServletResponseUtil.response(result, response);
	}

	/**
	 * @param e
	 * @Title:sendErrorMsg
	 * 
	 * @param string
	 * @return:void
	 * @throws Exception
	 * @exception
	 * @since 1.0.0
	 */
	public static void sendErrorMsg(Exception e, String msg,
			HttpServletResponse response) throws Exception {
		LOGGER.error(e);
		JSONObject result = new JSONObject();
		result.put("success", false);
		result.put("message", e.getMessage() + "," + msg);

		ServletResponseUtil.response(result, response);
	}

}
