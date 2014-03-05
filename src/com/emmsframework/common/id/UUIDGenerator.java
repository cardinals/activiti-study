/**
 * @Project:activiti
 * @Package:com.emmsframework.common.id 
 * @FileName:UUIDGenerator.java 
 * @Date:2013-9-13 上午9:16:41 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.common.id;

import java.io.Serializable;
import java.net.InetAddress;

import org.apache.log4j.Logger;

/**
 * @ClassName:UUIDGenerator
 * @Desc:唯一主键生成办法。从Hibernate中提取出来。
 * @Author:joe
 * @Date:2013-9-13 上午9:16:41
 * @Since:V 1.0
 */
public class UUIDGenerator
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger.getLogger(UUIDGenerator.class);

	private static final int IP;

	public static int IptoInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + bytes[i];
		}
		return result;
	}

	static {
		int ipadd;
		try {
			ipadd = IptoInt(InetAddress.getLocalHost().getAddress());
		}
		catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	public UUIDGenerator() {
	}

	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (UUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	private final static String sep = "";

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	public Serializable generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep)
				.append(format(getJVM())).append(sep)
				.append(format(getHiTime())).append(sep)
				.append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}

	public static String getUUID() {
		return new UUIDGenerator().generate().toString();
	}

	public static void main(String[] args) {
		System.out.println(new UUIDGenerator().generate());
		System.out.println(new UUIDGenerator().generate().toString());
	}
}
