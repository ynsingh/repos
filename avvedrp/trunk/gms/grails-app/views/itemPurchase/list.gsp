<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'itemPurchase.label', default: 'ItemPurchase')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'itemPurchase.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="assetCode" title="${message(code: 'itemPurchase.assetCode.label', default: 'Asset Code')}" />
                        
                            <g:sortableColumn property="billNo" title="${message(code: 'itemPurchase.billNo.label', default: 'Bill No')}" />
                        
                            <g:sortableColumn property="cost" title="${message(code: 'itemPurchase.cost.label', default: 'Cost')}" />
                        
                            <g:sortableColumn property="dateReceived" title="${message(code: 'itemPurchase.dateReceived.label', default: 'Date Received')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'itemPurchase.name.label', default: 'Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${itemPurchaseInstanceList}" status="i" var="itemPurchaseInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${itemPurchaseInstance.id}">${fieldValue(bean: itemPurchaseInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: itemPurchaseInstance, field: "assetCode")}</td>
                        
                            <td>${fieldValue(bean: itemPurchaseInstance, field: "billNo")}</td>
                        
                            <td>${fieldValue(bean: itemPurchaseInstance, field: "cost")}</td>
                        
                            <td><g:formatDate date="${itemPurchaseInstance.dateReceived}" /></td>
                        
                            <td>${fieldValue(bean: itemPurchaseInstance, field: "name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${itemPurchaseInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
