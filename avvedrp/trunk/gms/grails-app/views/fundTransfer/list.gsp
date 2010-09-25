

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundTransfer.label', default: 'FundTransfer')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'fundTransfer.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="createdDate" title="${message(code: 'fundTransfer.createdDate.label', default: 'Created Date')}" />
                        
                            <g:sortableColumn property="modifiedDate" title="${message(code: 'fundTransfer.modifiedDate.label', default: 'Modified Date')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'fundTransfer.amount.label', default: 'Amount')}" />
                        
                            <g:sortableColumn property="createdBy" title="${message(code: 'fundTransfer.createdBy.label', default: 'Created By')}" />
                        
                            <g:sortableColumn property="dateOfTransfer" title="${message(code: 'fundTransfer.dateOfTransfer.label', default: 'Date Of Transfer')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fundTransferInstanceList}" status="i" var="fundTransferInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${fundTransferInstance.id}">${fieldValue(bean: fundTransferInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${fundTransferInstance.createdDate}" /></td>
                        
                            <td><g:formatDate date="${fundTransferInstance.modifiedDate}" /></td>
                        
                            <td>${fieldValue(bean: fundTransferInstance, field: "amount")}</td>
                        
                            <td>${fieldValue(bean: fundTransferInstance, field: "createdBy")}</td>
                        
                            <td><g:formatDate date="${fundTransferInstance.dateOfTransfer}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${fundTransferInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
