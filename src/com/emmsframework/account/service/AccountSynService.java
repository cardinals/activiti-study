/**
 * @Project:activiti
 * @Package:com.emmsframework.account.service 
 * @FileName:AccountService.java 
 * @Date:2013-9-12 下午4:28:50 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.account.service;

import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSUser;


/**
 * @ClassName:AccountService
 * @Desc:维护Activiti用户、角色、权限接口
 * @Author:joe
 * @Date:2013-9-12 下午4:28:50
 * @Since:V 1.0
 */
public interface AccountSynService
{
	
	/**
	 * 
	 * @Title:synSave
	 * @Desc:同步数据到Activiti Identify模块
	 * @param user
	 * @param roleid
	 * @return:void
	 * @exception
	 * @since 1.0.0
	 */
	public void synSaveUser(TSUser user, String roleid);

	/**
	 * @Title:synSaveRole
	 * @Desc:同步数据到Activit Group模块
	 * @param role
	 * @return:void
	 * @exception
	 * @since 1.0.0
	 */
	public void synSaveRole(TSRole role);
     
    /**
     * 同步用户、角色数据到工作流
     * @throws Exception
     */
    public void synAllUserAndRoleToActiviti() throws Exception;
 
    /**
     * 
     * @Title:deleteAllActivitiIdentifyData
     * @Desc:删除工作流引擎Activiti的用户、角色以及关系
     * @throws Exception  
     * @return:void 
     * @exception
     * @since  1.0.0
     */
    public void deleteAllActivitiIdentifyData() throws Exception;

	/**
	 * @Title:synDeleteUser
	 * @Desc:删除activiti用户
	 * @param id
	 * @return:void
	 * @exception
	 * @since 1.0.0
	 */
	public void synDeleteUser(String userId);

	/**
	 * @Title:synDeleteRole
	 * @Desc:删除activiti group
	 * @param id
	 * @return:void
	 * @exception
	 * @since 1.0.0
	 */
	public void synDeleteRole(String roleId);


}
