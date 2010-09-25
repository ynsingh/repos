<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectEmployee.label', default: 'ProjectEmployee')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'projectEmployee.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="DOB" title="${message(code: 'projectEmployee.DOB.label', default: 'DOB')}" />
                        
                            <g:sortableColumn property="empName" title="${message(code: 'projectEmployee.empName.label', default: 'Emp Name')}" />
                        
                            <g:sortableColumn property="empNo" title="${message(code: 'projectEmployee.empNo.label', default: 'Emp No')}" />
                        
                            <th><g:message code="projectEmployee.employeeDesignation.label" default="Employee Designation" /></th>
                        
                            <g:sortableColumn property="joiningDate" title="${message(code: 'projectEmployee.joiningDate.label', default: 'Joining Date')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectEmployeeInstanceList}" status="i" var="projectEmployeeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectEmployeeInstance.id}">${fieldValue(bean: projectEmployeeInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${projectEmployeeInstance.DOB}" /></td>
                        
                            <td>${fieldValue(bean: projectEmployeeInstance, field: "empName")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeInstance, field: "empNo")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeInstance, field: "employeeDesignation")}</td>
                        
                            <td><g:formatDate date="${projectEmployeeInstance.joiningDate}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectEmployeeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
