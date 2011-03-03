

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'evalItem.label', default: 'EvalItem')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'evalItem.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="createdDate" title="${message(code: 'evalItem.createdDate.label', default: 'Created Date')}" />
                        
                            <g:sortableColumn property="modifiedDate" title="${message(code: 'evalItem.modifiedDate.label', default: 'Modified Date')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'evalItem.activeYesNo.label', default: 'Active Yes No')}" />
                        
                            <g:sortableColumn property="createdBy" title="${message(code: 'evalItem.createdBy.label', default: 'Created By')}" />
                        
                            <th><g:message code="evalItem.evalScale.label" default="Eval Scale" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${evalItemInstanceList}" status="i" var="evalItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${evalItemInstance.id}">${fieldValue(bean: evalItemInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${evalItemInstance.createdDate}" /></td>
                        
                            <td><g:formatDate date="${evalItemInstance.modifiedDate}" /></td>
                        
                            <td>${fieldValue(bean: evalItemInstance, field: "activeYesNo")}</td>
                        
                            <td>${fieldValue(bean: evalItemInstance, field: "createdBy")}</td>
                        
                            <td>${fieldValue(bean: evalItemInstance, field: "evalScale")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${evalItemInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
