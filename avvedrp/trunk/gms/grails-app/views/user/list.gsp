<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.UserList.head"/></title>
</head>

<body>
 <div class="wrapper">
	<div class="body">
		<h1>User List</h1>
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
					<g:sortableColumn property="user.userRealName" title="${message(code: 'default.FullName.label')}" />
					<g:sortableColumn property="party.nameOfTheInstitution" title="${message(code: 'default.Institution.label')}" />
					<th>Edit</th>
					<g:if test="${session.Role == 'ROLE_ADMIN'}"> 
						<th><g:message code="default.Edit.label"/></th>
					</g:if>
					
				</tr>
			</thead>
			<tbody>
			<g:each in="${userMapList}" status="i" var="userMap">
				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					<td>${i+1}</td>
					<td>${userMap.user.username?.encodeAsHTML()}</td>
					<td>${userMap.user.userRealName?.encodeAsHTML()}</td>
					
					<td>${userMap.party.nameOfTheInstitution}</td>
			        <g:if test="${session.Role == 'ROLE_SITEADMIN'}"> 
					<td class="actionButtons">
					 
						<span class="actionButton">
							<g:link action="edit" id="${userMap.user.id}" >Edit</g:link>
						</span>
						
					</td>
					</g:if>	
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
