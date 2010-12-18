

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'eligibilityCriteria.label', default: 'EligibilityCriteria')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'eligibilityCriteria.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="eligibilityCriteria" title="${message(code: 'eligibilityCriteria.eligibilityCriteria.label', default: 'Eligibility Criteria')}" />
                        
                            <g:sortableColumn property="mandatoryYesNo" title="${message(code: 'eligibilityCriteria.mandatoryYesNo.label', default: 'Mandatory Yes No')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'eligibilityCriteria.remarks.label', default: 'Remarks')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${eligibilityCriteriaInstanceList}" status="i" var="eligibilityCriteriaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${eligibilityCriteriaInstance.id}">${fieldValue(bean: eligibilityCriteriaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: eligibilityCriteriaInstance, field: "eligibilityCriteria")}</td>
                        
                            <td>${fieldValue(bean: eligibilityCriteriaInstance, field: "mandatoryYesNo")}</td>
                        
                            <td>${fieldValue(bean: eligibilityCriteriaInstance, field: "remarks")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${eligibilityCriteriaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
