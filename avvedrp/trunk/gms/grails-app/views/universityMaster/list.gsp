

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'universityMaster.label', default: 'UniversityMaster')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'universityMaster.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="createdBy" title="${message(code: 'universityMaster.createdBy.label', default: 'Created By')}" />
                        
                            <g:sortableColumn property="createdDate" title="${message(code: 'universityMaster.createdDate.label', default: 'Created Date')}" />
                        
                            <g:sortableColumn property="modifiedBy" title="${message(code: 'universityMaster.modifiedBy.label', default: 'Modified By')}" />
                        
                            <g:sortableColumn property="modifiedDate" title="${message(code: 'universityMaster.modifiedDate.label', default: 'Modified Date')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'universityMaster.activeYesNo.label', default: 'Active Yes No')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${universityMasterInstanceList}" status="i" var="universityMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${universityMasterInstance.id}">${fieldValue(bean: universityMasterInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: universityMasterInstance, field: "createdBy")}</td>
                        
                            <td><g:formatDate date="${universityMasterInstance.createdDate}" /></td>
                        
                            <td>${fieldValue(bean: universityMasterInstance, field: "modifiedBy")}</td>
                        
                            <td><g:formatDate date="${universityMasterInstance.modifiedDate}" /></td>
                        
                            <td>${fieldValue(bean: universityMasterInstance, field: "activeYesNo")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${universityMasterInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
