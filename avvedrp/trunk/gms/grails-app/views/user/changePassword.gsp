<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.ChangePassword.label"/></title>
	<script>

	</script>
</head>

<body>
<div class="wrapper">
	

	<div class="body">
	<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
		<h1><g:message code="default.ChangePassword.label"/></h1>
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
						<td valign="top" class="name"><label for="username"><g:message code="default.LoginName.label"/>:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
							
							${fieldValue(bean:person, field:'username')}
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">
						<label for="passwd"><g:message code="default.OldPassword.label"/>:</label>
						 <label for="passwd" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" >
							<input type="password" id="oldPasswd" name="oldPasswd" />
						</td>
					</tr>
					
					<tr class="prop">
						<td valign="top" class="name">
						<label for="passwd"><g:message code="default.NewPassword.label"/>:</label>
						<label for="passwd" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" >
							<input type="password" id="newPasswd" name="newPasswd" onchange="validateChangePasswordRule()"/>
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name">
						<label for="passwd"><g:message code="default.ConfirmPassword.label"/>:</label>
						<label for="passwd" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" >
							<input type="password" id="confirmNewPasswd" name="confirmNewPasswd" onchange="validateChangeConfirmPasswordRule()" />
						</td>
					</tr>
								
					<!-- <tr class="prop">
						<td valign="top" class="name"><label for="passwd">New Password:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'password','errors')}">
							<input type="password" id="passwd" name="passwd" value="${person.password?.encodeAsHTML()}"/>
						</td>
					</tr> -->
                        
				</tbody>
				</table>
			</div>

			<div >
				<input class="inputbutton" name="updatePassword" type="submit" value="${message(code: 'default.ChangePassword.label')}" onClick="return validatePassword()" />
			</div>
		</g:form>
</div>
	</div>
</body>
