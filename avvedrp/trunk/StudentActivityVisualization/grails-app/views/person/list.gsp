<head>
	<meta name="layout" content="main" />
	<title>Authority List</title>
</head>

<body>
<head>
	<meta name="layout" content="main" />
	<title>Authority List</title>
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">
                <div id="box">
                	<h3>Users List</h3>
                        <g:if test="${flash.message}">
                        <div class="message">${flash.message}</div>
                        </g:if>
                	<table>
			<thead>
				<tr>
					<th>SI.No</th>
					<g:sortableColumn property="username" title="Login Name" />
					<g:sortableColumn property="userRealName" title="Full Name" />
					<g:sortableColumn property="enabled" title="Enabled" />
					<g:sortableColumn property="description" title="Description" />
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<g:each in="${personList}" status="i" var="person">
				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					<td>${i+1}</td>
					<td>${person.username?.encodeAsHTML()}</td>
					<td>${person.userRealName?.encodeAsHTML()}</td>
					<td>${person.enabled?.encodeAsHTML()}</td>
					<td>${person.description?.encodeAsHTML()}</td>
					<td class="actionButtons">
						<span class="actionButton">
							<g:link action="show" id="${person.id}">View</g:link>
                                                        <g:link action="edit" id="${person.id}">&nbsp;/&nbsp;Edit</g:link>
						</span>
					</td>
				</tr>
			</g:each>
			</tbody>
			</table>
                   	<div class="paginateButtons">
			<g:paginate total="${Person.count()}" />
		        </div>
                </div>
                <br />
               <span class="menuButton"><g:link class="create" action="create">New User</g:link></span>
            </div>
            <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
            <g:sideMenu/>
            </g:if >

      </div>
         <g:styleSwitcher/>
</div>

</body>

</body>
