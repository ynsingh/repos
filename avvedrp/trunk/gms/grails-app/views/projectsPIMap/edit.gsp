<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projectsPIMap.EditProjectsToPI.head"/></title>
    </head>
    <body>
   <g:subMenuList/>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.projectsPIMap.EditProjectsToPI.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsPIMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsPIMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${projectsPIMapInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'investigator','errors')}">
                                   <g:select optionKey="id" optionValue="name" from="${investigatorList}" name="investigator.id" value="${projectsPIMapInstance?.investigator?.id}" ></g:select>
                               		
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects"><g:message code="default.Projects.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'projects','errors')}">
                                   <strong> ${projectsPIMapInstance.projects.code}</strong>
                                   <input type="hidden" id="projectId" name="projectId" value="${projectsPIMapInstance.projects.id}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role"><g:message code="default.Role.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsPIMapInstance,field:'role','errors')}">
                                     <strong>${projectsPIMapInstance?.role}</strong>
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
