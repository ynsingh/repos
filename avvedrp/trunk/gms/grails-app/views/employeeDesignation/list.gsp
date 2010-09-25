<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'employeeDesignation.label', default: 'EmployeeDesignation')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'employeeDesignation.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="designation" title="${message(code: 'employeeDesignation.designation.label', default: 'Designation')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${employeeDesignationInstanceList}" status="i" var="employeeDesignationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${employeeDesignationInstance.id}">${fieldValue(bean: employeeDesignationInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: employeeDesignationInstance, field: "designation")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${employeeDesignationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
