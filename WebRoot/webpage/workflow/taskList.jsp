<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  		
  		<table width="100%" border="1">
		<thead>
			<tr>
				<th>ProcessDefinitionId</th>
				<th>DeploymentId</th>
				<th>名称</th>
				<th>KEY</th>
				<th>版本号</th>
				<th>XML</th>
				<th>图片</th>
				<th>是否挂起</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${processDefList}" var="process">
				<tr>
					<td>${process.id }</td>
					<td>${process.deploymentId }</td>
					<td>${process.name }</td>
					<td>${process.key }</td>
					<td>${process.version }</td>
					<td><a target="_blank" href='processDefController/loadResource.do?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName }</a></td>
					<td><a target="_blank" href='processDefController/loadResource.do?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName }</a></td>
<%-- 					<td>${deployment.deploymentTime }</td> --%>
					<td>${process.suspended} |
						<c:if test="${process.suspended }">
							<a href="processdefinition/update/active/${process.id}">激活</a>
						</c:if>
						<c:if test="${!process.suspended }">
							<a href="processdefinition/update/suspend/${process.id}">挂起</a>
						</c:if>
					</td>
					<td>
                        <a href='processDefController/deleteDeployment.do?deploymentId=${process.deploymentId}'>删除</a>
                        <a href='processDefController.do'>转换为Model</a>
                    </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  	
  </div>
  
 </div><!-- end of easyui-layout -->