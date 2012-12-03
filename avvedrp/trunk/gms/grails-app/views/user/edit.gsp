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
				<input type="hidden" name="party.id" value="${party.id}" />
				<input type="hidden" name="authority" value="${authorityPersonInstance}" />
				<input type="hidden" name="version" value="${person.version}" />
				<div class="dialog">
					<table>
					<tbody>
					 	<td>
                         <input type="hidden" id="currentRoleId" name="currentRoleId" value="${authorityPersonInstance.id}"> 
                        </td>    
	
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

							<td valign="top" class="name">
							<label for="phNumber"><g:message code="default.aadhaarNo.label"/>:</label>
							</td>
	
							<td valign="top" class="value ${hasErrors(bean:person,field:'aadhaarNo','errors')}">
								<input type="text" id="aadhaarNo" name="aadhaarNo" value="${person?.aadhaarNo?.encodeAsHTML()}"/>
							</td>
					    </tr>   
					  
					<g:if test="${personRoleInstance.authority == 'ROLE_SUPERADMIN'}">
					    <tr class="prop">	
							<td valign="top" class="name">
							 <label for="password"><g:message code="default.Password.label"/>:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'userSurName','errors')}">
								<input type="password" id="Passwd" name="Passwd" />
							</td>
						</tr>
					</g:if>
							
				<g:if test="${personRoleInstance.authority == 'ROLE_SUPERADMIN'}">	
					<g:if test="${authorityPersonInstance.authority == 'ROLE_SUPERADMIN'}">
					</g:if>
					<g:else>
						<tr class="prop">
							<td valign="top" class="name">
							<label for="enabled"><g:message code="default.Enabled.label"/>:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'enabled','errors')}">
								<g:checkBox name="enabled" value="${person.enabled}"/>
							</td>
						</tr>
				    </g:else>
				</g:if>	
				<g:else>
					<g:if test="${authorityPersonInstance.authority == 'ROLE_SITEADMIN'}">
					</g:if>
					<g:else>
						<tr class="prop">
							<td valign="top" class="name">
							<label for="enabled"><g:message code="default.Enabled.label"/>:</label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'enabled','errors')}">
								<g:checkBox name="enabled" value="${person.enabled}"/>
							</td>
						</tr>
				    </g:else>
				</g:else>
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
				     <g:if test="${personRoleInstance.authority == 'ROLE_SUPERADMIN'}">	
				        <g:if test="${authorityPersonInstance.authority == 'ROLE_SUPERADMIN'}">
					    </g:if>
				        <g:else>
				          <span class="button"><g:actionSubmit class="delete" action="deleteUser" value="${message(code: 'default.Delete.button')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
				        </g:else>
				    </g:if>
				</div>
			</g:form>
		</div>
	</div>
</body>

</html>
