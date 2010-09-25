<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectEmployeeQualification.label', default: 'ProjectEmployeeQualification')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'projectEmployeeQualification.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="examname" title="${message(code: 'projectEmployeeQualification.examname.label', default: 'Examname')}" />
                        
                            <g:sortableColumn property="passoutYear" title="${message(code: 'projectEmployeeQualification.passoutYear.label', default: 'Passout Year')}" />
                        
                            <g:sortableColumn property="percMark" title="${message(code: 'projectEmployeeQualification.percMark.label', default: 'Perc Mark')}" />
                        
                            <g:sortableColumn property="projectEmployeeId" title="${message(code: 'projectEmployeeQualification.projectEmployeeId.label', default: 'Project Employee Id')}" />
                        
                            <g:sortableColumn property="university" title="${message(code: 'projectEmployeeQualification.university.label', default: 'University')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectEmployeeQualificationInstanceList}" status="i" var="projectEmployeeQualificationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectEmployeeQualificationInstance.id}">${fieldValue(bean: projectEmployeeQualificationInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "examname")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "passoutYear")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "percMark")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "projectEmployeeId")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeQualificationInstance, field: "university")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectEmployeeQualificationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
