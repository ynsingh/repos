<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.DeactivateProjects.head"/></title>
</head>

<body>
<div class="wrapper">
	<div class="body">
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help"> 
		<h1><g:message code="default.User.DeactivateProjects.head"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${projectsInstance}">
		<div class="errors">
		<g:renderErrors bean="${projectsInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form action="savedeactivate">
		<div class="dialog">
		<table>
		<tbody>
			<tr class="prop">
				<td valign="top" class="name"><label for="authority"><g:message code="default.Projects.label"/>:</label>
				<label for="authority" style="color:red;font-weight:bold"> * </label></td>
				<td valign="top" class="value ${hasErrors(bean:projects,field:'name','errors')}">
					 <g:select optionKey="id" optionValue="name" from="${projectsInstance}" name="projects.id"  noSelection="['null':'-Select-']"></g:select>
				</td>
			</tr>
			
			 <tr class="prop">
                <td valign="top" class="name">
                    <label for="activeYesNo">Active Yes No:</label>
                </td>
                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'activeYesNo','errors')}">
                    <g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(projectsInstance,field:'activeYesNo')}" noSelection="['null':'-Select-']" />
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
