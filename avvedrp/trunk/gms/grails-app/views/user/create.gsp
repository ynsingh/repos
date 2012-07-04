<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.CreateUser.head"/></title>
	
</head>

<body>
	<div class="wrapper">
	<div class="body">
	<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
		<h1><g:message code="default.User.CreateUser.head"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="save">
			<div class="dialog">
				<table>
				<tbody>
					<tr class="prop">
						<td valign="top" class="name">
						<label for="userRealName"><g:message code="default.FirstName.label"/>:</label>
						<label for="userRealName" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'userRealName','errors')}">
							<input type="text" id="userRealName" name="userRealName" value="${person.userRealName?.encodeAsHTML()}"/>
						</td>
						 
					</tr>
						
					<tr class="prop">
						<td valign="top" class="name">
						<label for="userSurName"><g:message code="default.LastName.label"/>:</label>
						<label for="userSurName" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'userSurName','errors')}">
							<input type="text" id="userSurName" name="userSurName" value="${person.userSurName?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name">
						<label for="passwd"><g:message code="default.Password.label"/>:</label>
						<label for="passwd" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
							<input type="password" id="password" name="password" value="${person.password?.encodeAsHTML()}"/>
						</td>
					</tr>
					
					<tr class="prop">
						<td valign="top" class="name">
						<label for="confirmPasswd"><g:message code="default.ConfirmPassword.label"/>:</label>
						<label for="confirmPasswd" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
							<input type="password" id="confirmPasswd" name="confirmPasswd" value="${person.password?.encodeAsHTML()}"/>
						</td>
					</tr>
					

					
					<tr class="prop">
						<td valign="top" class="name">
						<label for="email"><g:message code="default.Email.label"/>:</label>
						<label for="userSurName" style="color:red;font-weight:bold"> * </label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'email','errors')}">
							<input type="text" id="email" name="email" value="${person.email?.encodeAsHTML()}"/>
						</td>
	               	</tr>	
	               	
	               	<tr class="prop">

						<td valign="top" class="name">
						<label for="userDesignation"><g:message code="default.UserDesignation.label"/>:</label>
						</td>

						<td valign="top" class="value ${hasErrors(bean:person,field:'userDesignation','errors')}">
							<input type="text" id="userDesignation" name="userDesignation" value="${person?.userDesignation?.encodeAsHTML()}"/>
						</td>
					</tr>   
					
					<tr class="prop">

						<td valign="top" class="name">
						<label for="phNumber"><g:message code="default.ContactNo.label"/>:</label>
						</td>

						<td valign="top" class="value ${hasErrors(bean:person,field:'phNumber','errors')}">
							<input type="text" id="phNumber" name="phNumber" value="${person?.phNumber?.encodeAsHTML()}"/>
						</td>
					</tr>    
	               	
	               	<tr class="prop">
						<td valign="top" class="name" align="left">
						<g:message code="default.AssignRoles.label"/>:
						<label for="AssignRoles" style="color:red;font-weight:bold"> * </label>
						</td>
						<td> 
							<g:select optionKey="id" optionValue="authority" from= "${authorityInstance}" id="authorities" name="authorities"  noSelection="['Select':'-Select-']"></g:select>
						</td>
					</tr>
					<g:if test="${personRoleInstance.authority == 'ROLE_SUPERADMIN'}">
						<tr class="prop">
							<td valign="top" class="name" align="left">
								<g:message code="default.Institution.label"/>:
								<label for="Institution" style="color:red;font-weight:bold"> * </label>
							</td>
							<td> 
								<g:select optionKey="id" optionValue="code" from= "${institutionList}" id="institutions" name="institutions"  noSelection="['Select':'-Select-']"></g:select>
							</td>
						</tr>
					</g:if>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><input class="save" type="submit" onClick="return validateUser()" value="${message(code: 'default.Create.button')}"/></span>
			</div>

		</g:form>
	  </div>
	</div>
</body>
