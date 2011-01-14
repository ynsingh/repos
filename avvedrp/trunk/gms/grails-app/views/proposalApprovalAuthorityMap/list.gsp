

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'proposalApprovalAuthorityMap.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="proposalType" title="${message(code: 'proposalApprovalAuthorityMap.proposalType.label', default: 'Proposal Type')}" />
                        
                            <g:sortableColumn property="proposalId" title="${message(code: 'proposalApprovalAuthorityMap.proposalId.label', default: 'Proposal Id')}" />
                        
                            <g:sortableColumn property="approveOrder" title="${message(code: 'proposalApprovalAuthorityMap.approveOrder.label', default: 'Approve Order')}" />
                        
                            <g:sortableColumn property="processRestartOrder" title="${message(code: 'proposalApprovalAuthorityMap.processRestartOrder.label', default: 'Process Restart Order')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'proposalApprovalAuthorityMap.remarks.label', default: 'Remarks')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalApprovalAuthorityMapInstanceList}" status="i" var="proposalApprovalAuthorityMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${proposalApprovalAuthorityMapInstance.id}">${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "proposalType")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "proposalId")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "approveOrder")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "processRestartOrder")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "remarks")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${proposalApprovalAuthorityMapInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
