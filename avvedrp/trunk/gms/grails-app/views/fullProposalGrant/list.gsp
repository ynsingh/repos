

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fullProposalGrant.label', default: 'FullProposalGrant')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'fullProposalGrant.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="grantAgency" title="${message(code: 'fullProposalGrant.grantAgency.label', default: 'Grant Agency')}" />
                        
                            <g:sortableColumn property="grantName" title="${message(code: 'fullProposalGrant.grantName.label', default: 'Grant Name')}" />
                        
                            <g:sortableColumn property="appliedDate" title="${message(code: 'fullProposalGrant.appliedDate.label', default: 'Applied Date')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'fullProposalGrant.remarks.label', default: 'Remarks')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'fullProposalGrant.activeYesNo.label', default: 'Active Yes No')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fullProposalGrantInstanceList}" status="i" var="fullProposalGrantInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${fullProposalGrantInstance.id}">${fieldValue(bean: fullProposalGrantInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: fullProposalGrantInstance, field: "grantAgency")}</td>
                        
                            <td>${fieldValue(bean: fullProposalGrantInstance, field: "grantName")}</td>
                        
                            <td><g:formatDate date="${fullProposalGrantInstance.appliedDate}" /></td>
                        
                            <td>${fieldValue(bean: fullProposalGrantInstance, field: "remarks")}</td>
                        
                            <td>${fieldValue(bean: fullProposalGrantInstance, field: "activeYesNo")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${fullProposalGrantInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
