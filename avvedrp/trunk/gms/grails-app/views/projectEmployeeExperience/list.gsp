<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectEmployeeExperience.label', default: 'ProjectEmployeeExperience')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'projectEmployeeExperience.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="designation" title="${message(code: 'projectEmployeeExperience.designation.label', default: 'Designation')}" />
                        
                            <g:sortableColumn property="joiningDate" title="${message(code: 'projectEmployeeExperience.joiningDate.label', default: 'Joining Date')}" />
                        
                            <g:sortableColumn property="organizationName" title="${message(code: 'projectEmployeeExperience.organizationName.label', default: 'Organization Name')}" />
                        
                            <g:sortableColumn property="projectEmployeeId" title="${message(code: 'projectEmployeeExperience.projectEmployeeId.label', default: 'Project Employee Id')}" />
                        
                            <g:sortableColumn property="relievingDate" title="${message(code: 'projectEmployeeExperience.relievingDate.label', default: 'Relieving Date')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectEmployeeExperienceInstanceList}" status="i" var="projectEmployeeExperienceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectEmployeeExperienceInstance.id}">${fieldValue(bean: projectEmployeeExperienceInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: projectEmployeeExperienceInstance, field: "designation")}</td>
                        
                            <td><g:formatDate date="${projectEmployeeExperienceInstance.joiningDate}" /></td>
                        
                            <td>${fieldValue(bean: projectEmployeeExperienceInstance, field: "organizationName")}</td>
                        
                            <td>${fieldValue(bean: projectEmployeeExperienceInstance, field: "projectEmployeeId")}</td>
                        
                            <td><g:formatDate date="${projectEmployeeExperienceInstance.relievingDate}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectEmployeeExperienceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
