/**
 * @Project:emms
 * @Package:com.emmsframework.ftp 
 * @FileName:FtpTest.java 
 * @Date:2013-9-6 下午2:33:29 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.ftp;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * @ClassName:FtpTest
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-6 下午2:33:29
 * @Since:V 1.0
 */
public class FtpTest
{
	public static void main(String[] args) {
		FTPClient ftp_ = new FTPClient();
		try {
			ftp_.connect("10.0.0.78");
			System.out.println("Connecting...");
			ftp_.login("joe", "zhuyuhua");
			System.out.println("Connetcted");
		}
		catch (SocketException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FTPFile[] files = null;
			ftp_.changeWorkingDirectory("/");
			files = ftp_.listFiles();
			System.out.println("Directory is " + ftp_.printWorkingDirectory());

			System.out.println(files[0].getName() + "-"
					+ files[0].isDirectory() + "-" + files[0].getRawListing());

			System.out.println("***********************");
			ftp_.changeWorkingDirectory("/test");
			files = ftp_.listFiles();
			System.out.println("Directory is " + ftp_.printWorkingDirectory());

			System.out.println(files[1].getName() + "-"
					+ files[1].isDirectory() + "-" + files[1].getSize() + "-"
					+ files[1].getTimestamp().getTime());
			System.out.println(files[1].getRawListing());
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}


}
