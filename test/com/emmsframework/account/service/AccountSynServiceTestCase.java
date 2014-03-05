/**
 * @Project:activiti
 * @Package:com.emmsframework.account.service 
 * @FileName:AccountSynServiceTestCase.java 
 * @Date:2013-9-13 上午9:50:16 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.account.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import spring.SpringTxTestCase;

/** 
 * @ClassName:AccountSynServiceTestCase 
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-13 上午9:50:16 
 * @Since:V 1.0 
 */
public class AccountSynServiceTestCase extends SpringTxTestCase
{

	private static final Logger logger = Logger
			.getLogger(AccountSynServiceTestCase.class);

	@Autowired
	private AccountSynService accountSynService;


	@Test
	public void sysAllDataToActiviti() throws Exception {

		accountSynService.synAllUserAndRoleToActiviti();

	}
}
