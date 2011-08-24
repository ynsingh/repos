

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetDetails.label', default: 'BudgetDetails')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                             <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <th><g:message code="budgetDetails.budgetMaster.label" default="Budget Master" /></th>
                        
                            <th><g:message code="budgetDetails.accountHead.label" default="Account Head" /></th>
                        
                            <g:sortableColumn property="allocatedAmount" title="${message(code: 'budgetDetails.allocatedAmount.label', default: 'Allocated Amount')}" />
                            
                             <g:sortableColumn property="activeYesNo" title="${message(code: 'budgetDetails.activeYesNo.label', default: 'Active Yes No')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'budgetDetails.remarks.label', default: 'Remarks')}" />
                        
                           
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${budgetDetailsInstanceList}" status="i" var="budgetDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>${fieldValue(bean: budgetDetailsInstance, field: "budgetMaster.title")}</td>
                        
                            <td>${fieldValue(bean: budgetDetailsInstance, field: "accountHead.name")}</td>
                        
                            <td>${fieldValue(bean: budgetDetailsInstance, field: "allocatedAmount")}</td>
                            
                              <td>${fieldValue(bean: budgetDetailsInstance, field: "activeYesNo")}</td>
                        
                            <td>${fieldValue(bean: budgetDetailsInstance, field: "remarks")}</td>
                        
                          
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${budgetDetailsInstanceTotal}" />
            </div>
        </div>
        </div>
    </body>
</html>
