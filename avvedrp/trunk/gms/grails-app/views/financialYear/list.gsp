

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'financialYear.label', default: 'FinancialYear')}" />
        <title><g:message code="default.Financialyearlist.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
       <div class="body">
            <h1><g:message code="default.Financialyearlist.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'financialYear.id.label', default: 'Id')}" />
                            
                            <g:sortableColumn property="financialYear" title="${message(code: 'financialYear.financialYear.label', default: 'Financial Year')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'financialYear.activeYesNo.label', default: 'Active Yes No')}" />
                         
                            <g:sortableColumn property="description" title="${message(code: 'financialYear.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                          
                       </tr>
                    </thead>
                    <tbody>
                    <g:each in="${financialYearInstanceList}" status="i" var="financialYearInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${financialYearInstance.id}">${fieldValue(bean: financialYearInstance, field: "id")}</g:link></td>
                             
                            <td>${fieldValue(bean: financialYearInstance, field: "financialYear")}</td>
                            
                            <td>${fieldValue(bean: financialYearInstance, field: "activeYesNo")}</td>
                            
                            <td>${fieldValue(bean: financialYearInstance, field: "description")}</td>
                      
                           <td><g:link action="edit" id="${fieldValue(bean:financialYearInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                      
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
            </div>
        </div>
    </body>
</html>
