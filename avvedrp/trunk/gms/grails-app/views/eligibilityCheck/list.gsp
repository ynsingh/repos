

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'eligibilityCheck.label', default: 'EligibilityCheck')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'eligibilityCheck.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'eligibilityCheck.description.label', default: 'Description')}" />
                        
                            <th><g:message code="eligibilityCheck.eligibilityCriteria.label" default="Eligibility Criteria" /></th>
                        
                            <th><g:message code="eligibilityCheck.proposal.label" default="Proposal" /></th>
                        
                            <g:sortableColumn property="qualifiedYesNo" title="${message(code: 'eligibilityCheck.qualifiedYesNo.label', default: 'Qualified Yes No')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${eligibilityCheckInstanceList}" status="i" var="eligibilityCheckInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${eligibilityCheckInstance.id}">${fieldValue(bean: eligibilityCheckInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: eligibilityCheckInstance, field: "description")}</td>
                        
                            <td>${fieldValue(bean: eligibilityCheckInstance, field: "eligibilityCriteria")}</td>
                        
                            <td>${fieldValue(bean: eligibilityCheckInstance, field: "proposal")}</td>
                        
                            <td>${fieldValue(bean: eligibilityCheckInstance, field: "qualifiedYesNo")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${eligibilityCheckInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
