

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetMaster.label', default: 'BudgetMaster')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'budgetMaster.id.label', default: 'Id')}" />
                        
                            <th><g:message code="budgetMaster.financialYear.label" default="Financial Year" /></th>
                        
                            <th><g:message code="budgetMaster.party.label" default="Party" /></th>
                        
                            <g:sortableColumn property="budgetCode" title="${message(code: 'budgetMaster.budgetCode.label', default: 'Budget Code')}" />
                        
                            <g:sortableColumn property="budgetDescription" title="${message(code: 'budgetMaster.budgetDescription.label', default: 'Budget Description')}" />
                        
                            <g:sortableColumn property="totalBudgetAmount" title="${message(code: 'budgetMaster.totalBudgetAmount.label', default: 'Total Budget Amount')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${budgetMasterInstanceList}" status="i" var="budgetMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${budgetMasterInstance.id}">${fieldValue(bean: budgetMasterInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "financialYear")}</td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "party")}</td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "budgetCode")}</td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "budgetDescription")}</td>
                        
                            <td>${fieldValue(bean: budgetMasterInstance, field: "totalBudgetAmount")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${budgetMasterInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
