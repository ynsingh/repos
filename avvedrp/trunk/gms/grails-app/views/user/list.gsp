<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.UserList.head"/></title>
</head>
<body>
 <div class="wrapper">
	<div class="body">
		<h1><g:message code="default.User.UserList.head"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:if test="${userMapList}">
		<div class="list">
	<table cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
					<g:sortableColumn property="user.username" title="${message(code: 'default.LoginName.label')}" />
					<g:sortableColumn property="user.userRealName" title="${message(code: 'default.FirstName.label')}" />
					<g:sortableColumn property="user.userSurName" title="${message(code: 'default.LastName.label')}" />
					<g:sortableColumn property="user.role" title="${message(code: 'default.RoleNames.label')}" />
					<g:if test="${personRoleInstance.authority == 'ROLE_SUPERADMIN'}">
					<th><g:message code="default.Institution.label"/></th>
					</g:if> 
				    <th><g:message code="default.Edit.label"/></th>
					
				</tr>
			</thead>
			<tbody>
			<g:each in="${userMapList}" status="i" var="userMap">
				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					<td>${i+1}</td>
					<td class="actionButtons"><span class="actionButton">${userMap.user.username?.encodeAsHTML()}</span></td>
					<td class="actionButtons"><span class="actionButton">${userMap.user.userRealName?.encodeAsHTML()}</span></td>
					<td class="actionButtons"><span class="actionButton">${userMap.user.userSurName?.encodeAsHTML()}</span></td>
				    <% def authorityInstance = UserRole.executeQuery("select UR.role from UserRole UR where UR.user.id="+userMap.user.id)%>
					<g:if test="${authorityInstance[0]?.authority == 'ROLE_SITEADMIN'|| authorityInstance[0]?.authority == 'ROLE_SUPERADMIN'}">
					<td class="actionButtons"><span class="actionButton">${authorityInstance[0]?.authority}</span></td>
					</g:if>
				    <g:else>
				    <td class="actionButtons"><span class="actionButton"><g:link action="create" controller="userRole" id="${userMap.user.id}" ><g:message code="default.AssignRoles.label"/></g:link></span></td>
				    </g:else>
				    
				    <g:if test="${personRoleInstance.authority == 'ROLE_SUPERADMIN'}">
				    <td class="actionButtons"><span class="actionButton">${userMap.party.code.encodeAsHTML()}</span></td>
				    </g:if>
		            <td class="actionButtons"><span class="actionButton"><g:link action="edit" id="${userMap.user.id}" ><g:message code="default.Edit.label"/></g:link></span></td>
				</tr>
			</g:each>
			</tbody>
			</table>
		</div>
		</g:if>
            <g:else>
            <br>No Records Available</br>
            </g:else>
	    </div>
	   
	
			
	    
	</div>
</body>
</html>
