

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projectRefund.label', default: 'ProjectRefund')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'projectRefund.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="modifiedBy" title="${message(code: 'projectRefund.modifiedBy.label', default: 'Modified By')}" />
                        
                            <g:sortableColumn property="modifiedDate" title="${message(code: 'projectRefund.modifiedDate.label', default: 'Modified Date')}" />
                        
                            <g:sortableColumn property="createdBy" title="${message(code: 'projectRefund.createdBy.label', default: 'Created By')}" />
                        
                            <g:sortableColumn property="createdDate" title="${message(code: 'projectRefund.createdDate.label', default: 'Created Date')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'projectRefund.remarks.label', default: 'Remarks')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectRefundInstanceList}" status="i" var="projectRefundInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectRefundInstance.id}">${fieldValue(bean: projectRefundInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: projectRefundInstance, field: "modifiedBy")}</td>
                        
                            <td><g:formatDate date="${projectRefundInstance.modifiedDate}" /></td>
                        
                            <td>${fieldValue(bean: projectRefundInstance, field: "createdBy")}</td>
                        
                            <td><g:formatDate date="${projectRefundInstance.createdDate}" /></td>
                        
                            <td>${fieldValue(bean: projectRefundInstance, field: "remarks")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectRefundInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
