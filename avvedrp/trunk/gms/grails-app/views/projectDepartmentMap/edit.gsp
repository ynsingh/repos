

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projectsDepartmentMap.EditProjectsToDepartment.head"/></title>
    </head>
    <body>
    <g:subMenuProjects/>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.projectsDepartmentMap.EditProjectsToDepartment.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectDepartmentMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectDepartmentMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${projectDepartmentMapInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>        
                         </tr>
                         <input type="hidden" name="projects.id" value="${projectsInstance?.id}" />
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyDepartment"><g:message code="default.Projects.label"/>:</label>
                                </td>
                            <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'projects','errors')}">
                            <strong>${projectDepartmentMapInstance.projects.code}</strong>
                        	</tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyDepartment"><g:message code="default.Department.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectDepartmentMapInstance,field:'partyDepartment','errors')}">
                                    <g:select optionKey="id" optionValue="departmentCode" from="${partyDepartmentList}" name="partyDepartment.id" value="${projectDepartmentMapInstance?.partyDepartment?.id}" ></g:select>
                                </td>
                            </tr> 

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" /></span>					
					<span class="button">
						<g:actionSubmit class="delete"  action="delete" 
						value="${message(code: 'default.Delete.button')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</span>
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
