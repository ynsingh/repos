<head>
	<meta name="layout" content="main" />
	<title>Change UserName</title>
	<script>

	</script>
</head>

<body>
<div class="wrapper">
	

	<div class="body">
		<h1>Change User Name</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="updateUserName">
			<div class="dialog">
				<table>
				<tbody>
				
					<input type="hidden" name="id" value="${person.id}" />
					<input type="hidden" name="version" value="${person.version}" />
				
					
					<tr class="prop">
						<td valign="top" class="name"><label for="usrName">Old UserName:</label></td>
						<td valign="top" >
							<input type="userName" id="oldUsrName" name="oldUsrName" />
						</td>
					</tr>
					
					<tr class="prop">
						<td valign="top" class="name"><label for="usrName">New UserName</label></td>
						<td valign="top" >
							<input type="userName" id="newUsrName" name="newUsrName" />
						</td>
					</tr>
					
								
					
				</tbody>
				</table>
			</div>

			<div >
				<input class="inputbutton" name="updateUserName" type="submit" value="Change User Name"  />
			</div>
		</g:form>
</div>
	</div>
</body>
