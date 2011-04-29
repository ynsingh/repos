<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApproval.label', default: 'ProposalApproval')}" />
        <title><g:message code="default.FullProposalReviewerStatusList.label"/></title>
    </head>
    <body>
       <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.FullProposalReviewerStatusList.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                           <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                            
                           <th><g:message code ="default.ProposalTitle.label" /></th>
                            
                            <th> <g:message code="default.ReviewerStatus.label" /></th>
                            
                             <th><g:message code ="default.Comments.label" /></th>
                            </tr>
                           
                       </thead>
                      <tbody>
               <g:each in="${ProposalApprovalValueInstance}" status="i" var="proposalApprovalInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                      <td>${i+1}</td>
                   
                   
                        <%def proposalInstance = FullProposal.get(proposalApprovalInstance.proposalApproval.proposalApprovalAuthorityMap.proposalId)%>
                       <td>${fieldValue(bean: proposalInstance.preProposal, field: "projectTitle")}</td>
                        <td>${fieldValue(bean: proposalApprovalInstance, field: "proposalStatus")}</td>
                        <td>${fieldValue(bean: proposalApprovalInstance, field: "remarks")}</td>
                      
                      
                      </tr>
                      </g:each>
                
                      </tbody>
                </table>
            </div>
        </div>
        </div>
    </body>
</html>
              