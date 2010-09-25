<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployeeQualification.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeQualificationInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployeeQualification.examname.label" default="Examname" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeQualificationInstance, field: "examname")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployeeQualification.passoutYear.label" default="Passout Year" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeQualificationInstance, field: "passoutYear")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployeeQualification.percMark.label" default="Perc Mark" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeQualificationInstance, field: "percMark")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployeeQualification.projectEmployeeId.label" default="Project Employee Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeQualificationInstance, field: "projectEmployeeId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployeeQualification.university.label" default="University" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeQualificationInstance, field: "university")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${projectEmployeeQualificationInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /><img src="${createLinkTo( 
dir:'images',file:'edit.jpg' )}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
