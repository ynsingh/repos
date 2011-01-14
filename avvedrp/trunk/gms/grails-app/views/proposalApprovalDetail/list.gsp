

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'proposalApprovalDetail.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="proposalDetailId" title="${message(code: 'proposalApprovalDetail.proposalDetailId.label', default: 'Proposal Detail Id')}" />
                        
                            <g:sortableColumn property="proposalStatus" title="${message(code: 'proposalApprovalDetail.proposalStatus.label', default: 'Proposal Status')}" />
                        
                            <g:sortableColumn property="approvalDate" title="${message(code: 'proposalApprovalDetail.approvalDate.label', default: 'Approval Date')}" />
                        
                            <g:sortableColumn property="moreComments" title="${message(code: 'proposalApprovalDetail.moreComments.label', default: 'More Comments')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'proposalApprovalDetail.remarks.label', default: 'Remarks')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalApprovalDetailInstanceList}" status="i" var="proposalApprovalDetailInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${proposalApprovalDetailInstance.id}">${fieldValue(bean: proposalApprovalDetailInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: proposalApprovalDetailInstance, field: "proposalDetailId")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalDetailInstance, field: "proposalStatus")}</td>
                        
                            <td><g:formatDate date="${proposalApprovalDetailInstance.approvalDate}" /></td>
                        
                            <td>${fieldValue(bean: proposalApprovalDetailInstance, field: "moreComments")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalDetailInstance, field: "remarks")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${proposalApprovalDetailInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
