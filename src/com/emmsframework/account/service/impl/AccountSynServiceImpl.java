/**
 * @Project:activiti
 * @Package:com.emmsframework.account.service.impl 
 * @FileName:AccountServiceImpl.java 
 * @Date:2013-9-12 下午4:32:02 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.emmsframework.account.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emmsframework.account.service.AccountSynService;

/**
 * @ClassName:AccountServiceImpl
 * @Desc:TODO
 * @Author:joe
 * @Date:2013-9-12 下午4:32:02
 * @Since:V 1.0
 */
@Service
@Transactional
public class AccountSynServiceImpl implements AccountSynService
{

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SystemService systemService;
	/**
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(AccountSynServiceImpl.class);

	/**
	 * 添加Activiti Identify的用户于组关系
	 * 
	 * @param roleIds
	 *            角色ID集合
	 * @param userId
	 *            用户ID
	 */
	private void addMembershipToIdentify(List<String> roleIds, String userId) {
		for (String roleId : roleIds) {
			logger.debug("add role to activit,userid=" + userId + ",roleId="
					+ roleId);
			identityService.createMembership(userId, roleId);
		}
	}

	/**
	 * 使用系统用户对象属性更新到Activiti User对象中
	 * 
	 * @param user
	 *            系统用户对象
	 * @param activitiUser
	 *            Activiti User
	 */
	private void cloneAndSaveActivitiUser(TSUser user, User activitiUser) {
		activitiUser.setFirstName(user.getUserName());
		activitiUser.setLastName(user.getUserName());
		activitiUser.setPassword(user.getPassword());
		activitiUser.setEmail(user.getEmail());
		identityService.saveUser(activitiUser);
	}
	
	@Override
	public void deleteAllActivitiIdentifyData() throws Exception {
		deleteAllMemerShip();
		deleteAllRole();
		deleteAllUser();

	}  

	
	/** 
     * 删除用户和组的关系 
     */  
    public void deleteAllMemerShip() {  
        String sql = "delete from ACT_ID_MEMBERSHIP";  
        commonService.executeSql(sql);
        logger.debug("deleted from activiti membership.");  
    }  


	/** 
     * 删除用户和组的关系 
     */  
    public void deleteAllRole() {  
        String sql = "delete from ACT_ID_GROUP";  
        commonService.executeSql(sql);
        logger.debug("deleted from activiti group.");  
    }

	/** 
     * 删除用户和组的关系 
     */  
    public void deleteAllUser() {  
        String sql = "delete from ACT_ID_USER";
        commonService.executeSql(sql);
        logger.debug("deleted from activiti user.");  
    }
	
	  /**
	 * 添加工作流用户以及角色
	 * 
	 * @param user
	 *            用户对象{@link User}
	 * @param roleIds
	 *            用户拥有的角色ID集合
	 */
	private void newActivitiUser(TSUser user, List<String> roleIds) {

		String userId = user.getId();

		// 添加用户
		saveActivitiUser(user);

		// 添加membership
		addMembershipToIdentify(roleIds, userId);
	}  
   
    /**
	 * 添加一个用户到Activiti
	 * 
	 * @param user
	 *            用户对象
	 */
	private void saveActivitiUser(TSUser user) {
		String userId = user.getId();
		User activitiUser = identityService.newUser(userId);
		cloneAndSaveActivitiUser(user, activitiUser);
		logger.debug("add activiti user: "
				+ ToStringBuilder.reflectionToString(activitiUser));
	}  
   
    /**
     * 同步用户、角色数据到工作流
     * @throws Exception
     */
	@Override
	public void synAllUserAndRoleToActiviti() throws Exception {
			 
	        // 清空工作流用户、角色以及关系
	        deleteAllActivitiIdentifyData();
	 
	        // 复制角色数据
	        synRoleToActiviti();
	 
	        // 复制用户以及关系数据
	        synUserWithRoleToActiviti();

	}  
   
	@Override
	public void synDeleteUser(String userId) {

		// 删除Activiti membership
		deleteMembershipUserId(userId);

		// 同步删除Activiti User
		identityService.deleteUser(userId);

	}

	@Override
	public void synDeleteRole(String roleId) {
		// 删除Activiti membership
		deleteMembershipByRoleId(roleId);

		// 同步删除Activiti User
		identityService.deleteGroup(roleId);
	}

	/** 
     * 同步所有角色数据到{@link Group} 
     */  
    private void synRoleToActiviti() {  
    	
    	List<TSRole> allRole = systemService.loadAll(TSRole.class);
    	
        for (TSRole role : allRole) {  
            String groupId = role.getId();
            Group group = identityService.newGroup(groupId);  
            group.setName(role.getRoleName());  
            group.setType(role.getRoleCode()); 
            
            identityService.saveGroup(group);  
        }  
    }

	/**
	 * 
	 * @Title:synUserAndRole
	 * @Desc:同步数据到Activiti Identify模块
	 * @param user
	 * @param roleIds
	 * @return:void
	 * @exception
	 * @since 1.0.0
	 */
	@Override
	public void synSaveUser(TSUser user, String roleIdStr) {

		UserQuery userQuery = identityService.createUserQuery();
		List<User> activitiUsers = userQuery.userId(user.getId()).list();
		List<String> roleIds = converStrToLis(roleIdStr);
		if (activitiUsers.size() == 1) {
			updateActivitiUser(user, roleIds, activitiUsers.get(0));
		}
		else if (activitiUsers.size() > 1) {
			String errorMsg = "发现重复用户：id=" + user.getId();
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}
		else {
			newActivitiUser(user, roleIds);
		}

	}

	@Override
	public void synSaveRole(TSRole role) {
		GroupQuery groupQuery = identityService.createGroupQuery();

		List<Group> groups = groupQuery.groupId(role.getId()).list();
		Group group;
		if (groups.size() == 1) {
			group = groups.get(0);
			group.setName(role.getRoleName());
			group.setType(role.getRoleCode());
		}
		else if (groups.size() > 1) {
			String errorMsg = "发现重复角色：id=" + role.getId();
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}
		else {
			group = identityService.newGroup(role.getId());
			group.setName(role.getRoleName());
			group.setType(role.getRoleCode());
		}
		identityService.saveGroup(group);
	}

	/**
	 * @Title:converStrToLis
	 * @Desc:TODO
	 * @param roleIdStr
	 * @return
	 * @return:List<String>
	 * @exception
	 * @since 1.0.0
	 */
	private List<String> converStrToLis(String roleIdStr) {
		if (StringUtil.isEmpty(roleIdStr)) {
			return Collections.emptyList();
		}
		String[] roleids = roleIdStr.split(",");
		return Arrays.asList(roleids);
	}

	public static void main(String[] args) {
		String string = null;
		List<String> list = new AccountSynServiceImpl().converStrToLis(string);
		for (String string2 : list) {
			System.out.println(string2);
		}

	}

	/**
	 * 复制用户以及关系数据
	 */  
    private void synUserWithRoleToActiviti() {  
    	
        List<TSUser> allUser = systemService.loadAll(TSUser.class);
        
        for (TSUser user : allUser) {
        	
            String userId = user.getId();
   
            // 添加一个用户到Activiti   
            saveActivitiUser(user);
            
            // 角色和用户的关系   
           List<TSRoleUser> roleUsers=systemService.findByProperty(TSRoleUser.class,"TSUser.id",user.getId());
           
    	    for (TSRoleUser tsRoleUser : roleUsers) {
				logger.debug("add membership {user: " + userId + ",role:"
						+ tsRoleUser.getTSRole().getId() + "}");
    	    	identityService.createMembership(userId,tsRoleUser.getTSRole().getId());  
    		}	
        }  
    }

	/**
	 * 更新工作流用户以及角色
	 * 
	 * @param userId
	 *            用户对象{@link User}
	 * @param roleIds
	 *            用户拥有的角色ID集合
	 * @param activitiUser
	 *            Activiti引擎的用户对象
	 */
	private void updateActivitiUser(TSUser user, List<String> roleIds,
			User activitiUser) {

		String userId = user.getId();

		// 更新用户主体信息
		cloneAndSaveActivitiUser(user, activitiUser);

		// 删除Activiti membership
		deleteMembershipUserId(userId);

		// 添加membership
		addMembershipToIdentify(roleIds, userId);
	}

	private void deleteMembershipUserId(String userId) {
		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery()
				.groupMember(userId).list();
		for (Group group : activitiGroups) {
			logger.debug("delete group from activit: "
					+ ToStringBuilder.reflectionToString(group));
			identityService.deleteMembership(userId, group.getId());
		}
	}

	private void deleteMembershipByRoleId(String roleId) {
		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery()
				.groupId(roleId).list();
		for (Group group : activitiGroups) {
			logger.debug("delete group from activit: "
					+ ToStringBuilder.reflectionToString(group));
			identityService.deleteMembership(group.getId(), roleId);
		}
	}
}
