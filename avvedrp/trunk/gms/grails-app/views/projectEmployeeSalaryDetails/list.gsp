<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectEmployeeSalaryDetails.label', default: 'ProjectEmployeeSalaryDetails')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'projectEmployeeSalaryDetails.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="endDate" title="${message(code: 'projectEmployeeSalaryDetails.endDate.label', default: 'End Date')}" />
                        
                            <g:sortableColumn property="projectEmployeeId" title="${message(code: 'projectEmployeeSalaryDetails.projectEmployeeId.label', default: 'Project Employee Id')}" />
                        
                            <g:sortableColumn property="salaryAmount" title="${message(code: 'projectEmployeeSalaryDetails.salaryAmount.label', default: 'Salary Amount')}" />
                        
                            <g:sortableColumn property="salaryComponentId" title="${message(code: 'projectEmployeeSalaryDetails.salaryComponentId.label', default: 'Salary Component Id')}" />
                        
                            <g:sortableColumn property="withEffectFrom" title="${message(code: 'projectEmployeeSalaryDetails.withEffectFrom.label', default: 'With Effect From')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectEmployeeSalaryDetailsInstanceList}" status="i" var="projectEmployeeSalaryDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectEmployeeSalaryDetailsInstance.id}">${fieldValue(bean: projectEmployeeSalaryDetailsInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${projectEmployeeSalaryDetailsInstance.endDate}" /></td>
                        
                            <td>${fieldValue(bean: projectEmployeeSalaryDetailsInstance, field: "projectEmployeeId")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeSalaryDetailsInstance, field: "salaryAmount")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeSalaryDetailsInstance, field: "salaryComponentId")}</td>
                        
                            <td><g:formatDate date="${projectEmployeeSalaryDetailsInstance.withEffectFrom}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectEmployeeSalaryDetailsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
