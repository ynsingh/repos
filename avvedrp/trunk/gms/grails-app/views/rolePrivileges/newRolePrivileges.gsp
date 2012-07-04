<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.CreateRolePrivileges.head"/></title>
</head>

<body>
<div class="wrapper">
	<div class="body">
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help"> 
		<h1><g:message code="default.User.CreateRolePrivileges.head"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${authority}">
		<div class="errors">
		<g:renderErrors bean="${authority}" as="list" />
		</div>
		</g:hasErrors>

		<g:form action="saveNewRolePriviliges">
		<div class="dialog">
		<table>
		<tbody>
			<tr class="prop">
				<td valign="top" class="name"><label for="authority"><g:message code="default.AuthorityName.label"/>:</label>
				<label for="authority" style="color:red;font-weight:bold"> * </label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'authority','errors')}">
					 <g:select optionKey="id" optionValue="authority" from="${authorityInstanceList}" name="authority" value="${rolePrivilegesInstance?.role?.id}" noSelection="['null':'-Select-']"></g:select>
				</td>
			</tr>
						
		</tbody>
		</table>
		</div>
        	<div class="buttons">
				<span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateRolePrivilege()"/></span>
			</div>
		</g:form>
	</div>
	</div>
</body>
