<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.CreateRolePrevileges.head "/></title>
</head>

<body>
<div class="wrapper">
	<div class="body">

		<h1><g:message code="default.User.CreateRolePrevileges.head"/></h1>
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
				<td valign="top" class="name"><label for="authority"><g:message code="default.AuthorityName.label"/>:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'authority','errors')}">
					 <g:select optionKey="id" optionValue="authority" from="${authorityInstanceList}" name="authority" value="${rolePrivilegesInstance?.role?.id}" ></g:select>
				</td>
			</tr>

			
		</tbody>
		</table>
		</div>

		<div class="buttons">
			<span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" /></span>
		</div>
		</g:form>
	</div>
	</div>
</body>
