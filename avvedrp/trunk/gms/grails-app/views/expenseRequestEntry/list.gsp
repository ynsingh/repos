

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expenseRequestEntry.label', default: 'ExpenseRequestEntry')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'expenseRequestEntry.id.label', default: 'Id')}" />
                        
                            <th><g:message code="expenseRequestEntry.grantAllocation.label" default="Grant Allocation" /></th>
                        
                            <th><g:message code="expenseRequestEntry.grantAllocationSplit.label" default="Grant Allocation Split" /></th>
                        
                            <g:sortableColumn property="modifiedBy" title="${message(code: 'expenseRequestEntry.modifiedBy.label', default: 'Modified By')}" />
                        
                            <g:sortableColumn property="modifiedDate" title="${message(code: 'expenseRequestEntry.modifiedDate.label', default: 'Modified Date')}" />
                        
                            <g:sortableColumn property="createdBy" title="${message(code: 'expenseRequestEntry.createdBy.label', default: 'Created By')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${expenseRequestEntryInstanceList}" status="i" var="expenseRequestEntryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${expenseRequestEntryInstance.id}">${fieldValue(bean: expenseRequestEntryInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: expenseRequestEntryInstance, field: "grantAllocation")}</td>
                        
                            <td>${fieldValue(bean: expenseRequestEntryInstance, field: "grantAllocationSplit")}</td>
                        
                            <td>${fieldValue(bean: expenseRequestEntryInstance, field: "modifiedBy")}</td>
                        
                            <td><g:formatDate date="${expenseRequestEntryInstance.modifiedDate}" /></td>
                        
                            <td>${fieldValue(bean: expenseRequestEntryInstance, field: "createdBy")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${expenseRequestEntryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
