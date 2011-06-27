<html>
<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.EditUser.head"/></title>
</head>


<body>
	<div class="wrapper">
		<div class="body">
			<h1><g:message code="default.User.EditUser.head"/></h1>
			<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${person}">
			<div class="errors">
				<g:renderErrors bean="${person}" as="list" />
			</div>
			</g:hasErrors>
			<g:form>
				<input type="hidden" name="id" value="${person.id}" />
				<input type="hidden" name="version" value="${person.version}" />
				<div class="dialog">
					<table>
					<tbody>
	
						<tr class="prop">
							<td valign="top" class="name">
							<label for="username"><g:message code="default.LoginName.label"/>:</label></td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
								${fieldValue(bean:person, field:'username')}
								
							</td>
						</tr>
	
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
						<g:if test="${authorityPersonInstance.authority == 'ROLE_PI'}">
						</g:if>
						<g:else>
						<tr class="prop">	
							<td valign="top" class="name">
							<label for="role"><g:message code="default.Role.label"/>:</label>
							<label for="role" style="color:red;font-weight:bold"> * </label>
							</td>
							<td> 
							   <g:select optionKey="id" optionValue="authority" from= "${authorityInstance}" id="authorities" name="authorities" value="${authorityPersonInstance?.id}" ></g:select>
							</td>
						</tr>
						</g:else>	
													
						<tr class="prop">
							<td valign="top" class="name">
							<label for="enabled"><g:message code="default.Enabled.label"/>:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'enabled','errors')}">
								<g:checkBox name="enabled" value="${person.enabled}"/>
							</td>
						</tr>
	
						<tr class="prop">
							<td valign="top" class="name"><label for="email"><g:message code="default.Email.label"/>:</label>
							<label for="email" style="color:red;font-weight:bold"> * </label></td></td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'email','errors')}">
								<input type="text" id="email" name="email" value="${person?.email?.encodeAsHTML()}"/>
							</td>		
						</tbody>
					</table>
				</div>
	
				<div class="buttons">
					<span class="button"><g:actionSubmit class="save" action="update" onclick="return validateEditUser();" value="${message(code: 'default.Update.button')}" /></span>				
				</div>
			</g:form>
		</div>
	</div>
</body>

</html>
