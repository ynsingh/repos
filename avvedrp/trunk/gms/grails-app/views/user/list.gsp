<head>
	<meta name="layout" content="main" />
	<title>User List</title>
</head>

<body>
 <div class="wrapper">
	<div class="nav">
		        <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
		        
					 <g:if test="${(session.Role != 'ROLE_USER') && (session.Role != 'ROLE_PI')}"> 
		            <span class="menuButton"><g:link class="create" action="create">New User</g:link></span>
                  
					 </g:if>
					 <g:if test="${session.Role == 'ROLE_ADMIN'}"> 
		           
                    <span class="menuButton"><g:link class="create" controller="user" action="newUserCreate">New Site Admin</g:link></font></span> 
					 </g:if>  
	</div>

	<div class="body">
		<h1>User List</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:if test="${userMapList}">
		<div class="list">
			<table>
			<thead>
				<tr>
					<g:sortableColumn property="id" title="Id" />
					<g:sortableColumn property="user.username" title="Login Name" />
					<g:sortableColumn property="user.userRealName" title="Full Name" />
					<g:sortableColumn property="party.nameOfTheInstitution" title="Institution" />
					<g:if test="${session.Role == 'ROLE_ADMIN'}"> 
					<th>Edit</th>
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
			        <g:if test="${session.Role == 'ROLE_ADMIN'}"> 
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

		<div class="paginateButtons">
			<g:paginate total="${User.count()}" />
		</div>
    </div>

	</div>
</body>
