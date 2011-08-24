

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetModuleMap.label', default: 'BudgetModuleMap')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <th><g:message code="budgetModuleMap.budgetMaster.label" default="Budget Master" /></th>
                        
                            <g:sortableColumn property="moduleType" title="${message(code: 'budgetModuleMap.moduleType.label', default: 'Module Type')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'budgetModuleMap.activeYesNo.label', default: 'Active Yes No')}" />
                                                       
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${budgetModuleMapInstanceList}" status="i" var="budgetModuleMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                             <td>${i+1}</td>
                        
                            <td>${fieldValue(bean: budgetModuleMapInstance, field: "budgetMaster.title")}</td>
                        
                            <td>${fieldValue(bean: budgetModuleMapInstance, field: "moduleType")}</td>
                        
                            <td>${fieldValue(bean: budgetModuleMapInstance, field: "activeYesNo")}</td>
                            
                              <td><g:link action="edit" id="${fieldValue(bean:budgetModuleMapInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${budgetModuleMapInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
