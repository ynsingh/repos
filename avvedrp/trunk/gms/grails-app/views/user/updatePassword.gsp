<head>
	<meta name="layout" content="main" />
	<title>Change Password</title>
</head>

<body>

	<div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
              
           
        </div>

	<div class="body">
		<h1>Change Password</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="updatePassword">
			<div class="dialog">
				<table>
				<tbody>
				
					<input type="hidden" name="id" value="${person.id}" />
					<input type="hidden" name="version" value="${person.version}" />
				
					<tr class="prop">
						<td valign="top" class="name"><label for="username">Login Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
							<input type="text" id="username" name="username" value="${person.username?.encodeAsHTML()}"/>
						</td>
					</tr>

								
					<tr class="prop">
						<td valign="top" class="name"><label for="passwd">Old Password:</label></td>
						<td valign="top" >
							<input type="password" id="oldPasswd" name="oldPasswd" />
						</td>
					</tr>
					
					<tr class="prop">
						<td valign="top" class="name"><label for="passwd">New Password:</label></td>
						<td valign="top" >
							<input type="password" id="newPasswd" name="newPasswd" />
						</td>
					</tr>

					
                        

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><input name="updatePassword" type="submit" value="Change Password" /></span>
			</div>

		</g:form>

	</div>
</body>
