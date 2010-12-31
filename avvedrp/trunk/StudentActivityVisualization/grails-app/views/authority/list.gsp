<head>
	<meta name="layout" content="main" />
	<title>Authority List</title>        
</head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">
                <div id="box">
                	<h3>Authorities</h3>
                	<table width="100%">
			<thead>
				<tr>
					<th>SI.No</th>
					<g:sortableColumn property="authority" title="Authority Name" />
					<g:sortableColumn property="description" title="Description" />
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<g:each in="${authorityList}" status="i" var="authority">
				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					<td>${i+1}</td>
					<td>${authority.authority?.encodeAsHTML()}</td>
					<td>${authority.description?.encodeAsHTML()}</td>
					<td class="actionButtons">
						<span class="actionButton">
							<g:link action="show" id="${authority.id}">View</g:link>
                                                        <g:link action="edit" id="${authority.id}">&nbsp;/&nbsp;Edit</g:link>
						</span>
					</td>
				</tr>
			</g:each>
			</tbody>
			</table>
                    <div class="paginateButtons">
                    	<g:paginate total="${Authority.count()}" />
                    </div>
                </div>
                <br />
                <span class="menuButton"><g:link class="create" action="create">New Authority</g:link></span>
            </div>
            <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
            <g:sideMenu/>
            </g:if >

      </div>
        <g:styleSwitcher/>
</div>

</body>

</body>
