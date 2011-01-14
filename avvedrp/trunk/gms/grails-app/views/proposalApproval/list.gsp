

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApproval.label', default: 'ProposalApproval')}" />
        <title><g:message code="default.PreProposalList.label"/></title>
    </head>
    <body>
      
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.PreProposalList.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <tr>
                      
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <th><g:message code="default.Institution.label"  /></th>
                        	
                            <th><g:message code="default.ProjectTitle.label"/></th>
                           
                            <th><g:message code="default.Investigator.label"  /></th>
                            
                            <th> <g:message code="default.PreProposalStatus.label" /></th>
                            <th><g:message code="default.ApprovalAuthority.label"  /></th>
                            <th><g:message code="default.Approve/Reject.label" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalApprovalInstanceList}" status="i" var="proposalApprovalInstance" >
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <% def proposalInstance= PreProposal.get(proposalApprovalInstanceList[i].proposalApprovalAuthorityMap.proposalId) %>
                            <td>${i+1}</td>
                        
                            <td>${proposalInstance.party.code}</td>
                            
                            <td>${proposalInstance.projectTitle}</td>
                            <td>${proposalInstance.person.username}</td>
                            
                            <td>${proposalInstance.preProposalStatus}</td>
                           <td>${proposalApprovalInstance.approvalAuthorityDetail.approvalAuthority.name}</td>
                        <td>
                        <g:if test="${proposalApprovalInstance.viewAll!='Y'}">
                        	<g:link controller="proposalApprovalDetail" action="preProposalReview" id="${proposalApprovalInstance.id}"><g:message code="default.Approve/Reject.label" /></g:link>
                        	</g:if>
                        	<g:else>
                        	<g:link controller="preProposal" action="submittedPreProposal" id="${proposalApprovalInstance.proposalApprovalAuthorityMap.proposalId}"><g:message code="default.View.label"/></g:link>
                        	</g:else>
                        </td>
                            
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
