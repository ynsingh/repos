<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.AssignNotificationtoApprovalAuthority.label"/></title>
</head>

<body>
<div class="wrapper">
	<div class="body">

		<h1><g:message code="default.AssignNotificationtoApprovalAuthority.label"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:form action="saveNotificationAuthorityMap">
		<div class="dialog">
		<table>
		<tbody>
			<tr class="prop">
				<td valign="top" class="name"><label for="authority"><g:message code="default.ApprovalAuthority.label"/>:</label>
				<label for="authority" style="color:red;font-weight:bold"> * </label></td>
				<td valign="top" class="value ${hasErrors(bean:approvalAuthority,field:'name','errors')}" width="90%">
				<g:if test="${currentApprovalAuthority}">
				<g:select optionKey="id" optionValue="name" from="${approvalAuthorityInstance}" name="approvalAuthorityId" value="${currentApprovalAuthority?.approvalAuthority?.id}"></g:select>
				</g:if>
				<g:else>
				<g:select optionKey="id" optionValue="name" from="${approvalAuthorityInstance}" name="approvalAuthorityId" noSelection="['null':'-Select-']"></g:select>
				</g:else>
				<g:hiddenField name="proposalId" value="${proposalId}" />
				</td>
			</tr>

			
		</tbody>
		</table>
		</div>

		<div class="buttons">
			<span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateNotificationAuthorityMap()" /></span>
		</div>
		</g:form>
	</div>
	</div>
</body>
