<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectEmployee.label', default: 'ProjectEmployee')}" />
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
                            <td valign="top" class="name"><g:message code="projectEmployee.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployee.DOB.label" default="DOB" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${projectEmployeeInstance?.DOB}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployee.empName.label" default="Emp Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeInstance, field: "empName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployee.empNo.label" default="Emp No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeInstance, field: "empNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployee.employeeDesignation.label" default="Employee Designation" /></td>
                            
                            <td valign="top" class="value"><g:link controller="employeeDesignation" action="show" id="${projectEmployeeInstance?.employeeDesignation?.id}">${projectEmployeeInstance?.employeeDesignation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployee.joiningDate.label" default="Joining Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${projectEmployeeInstance?.joiningDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployee.projectId.label" default="Project Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: projectEmployeeInstance, field: "projectId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="projectEmployee.relievingDate.label" default="Relieving Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${projectEmployeeInstance?.relievingDate}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${projectEmployeeInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
