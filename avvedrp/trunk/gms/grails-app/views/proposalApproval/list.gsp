

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
         <g:if test="${params.ProposalType=='PreProposal'}">
            <h1><g:message code="default.PreProposalList.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
         </g:if>
         <g:else>
            <h1><g:message code="default.FullProposalList.label"/></h1>
             <g:if test="${flash.message}">
             <div class="message">${flash.message}</div>
             </g:if>
         </g:else>
          
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                          <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                          <th><g:message code="default.Institution.label"  /></th>
                          <th><g:message code="default.ProposalTitle.label"/></th>
                          <th><g:message code="default.Investigator.label"  /></th>
	                      <th> <g:message code="default.PreProposalStatus.label" /></th>
	                      <th><g:message code="default.ApprovalAuthority.label"  /></th>
	                      <th><g:message code="default.Approve/Reject.label" /></th>
                         </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preProposalInstanceList}" status="i" var="proposalInstance" >
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${i+1}</td>
                            <td>${proposalInstance.party.code}</td>
                            <td>${proposalApplicationList[i].projectTitle}</td>
                            <td>${proposalInstance.person.username}</td>
                            <td>${proposalInstance.proposalStatus}</td>
                            <td>${proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.name}</td>
                        <td>
                        <g:if test="${proposalApprovalAuthorityMapInstanceList[i].viewAll!='Y'}">
                        	<g:if test="${proposalType=='PreProposal'}">
                        	<g:link controller="proposalApprovalDetail" action="preProposalReview" params="['id':proposalInstance.id,'proposalApprovalAuthorityMap.id':proposalApprovalAuthorityMapInstanceList[i].id]" id="${proposalInstance.id}"><g:message code="default.Approve/Reject.label" /></g:link>
                        	</g:if>
                        	<g:else>
                        	<g:link controller="proposalApprovalDetail" action="fullProposalReview" id="${proposalInstance.id}" params="['id':proposalInstance.id,'proposalApprovalAuthorityMap.id':proposalApprovalAuthorityMapInstanceList[i].id]"><g:message code="default.Approve/Reject.label" /></g:link>
                        	</g:else>
                        </g:if>
                    	<g:else>
                    	<g:if test="${proposalType=='PreProposal'}">
                    	<g:link controller="proposal" action="submittedPreProposal" id="${proposalInstance.id}"><g:message code="default.View.label"/></g:link>
                    	</g:if>
                    	<g:else>
                    	<g:link controller="proposal" action="submittedPreProposal" id="${proposalInstance.parent.id}"><g:message code="default.View.label"/></g:link>
                    	</g:else>
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
