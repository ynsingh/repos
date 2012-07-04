

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
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
            <h1><g:message code="default.PreProposalList.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
         <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                          <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                          <th><g:message code="default.ProposalTitle.label"/></th>
                          <th><g:message code="default.SubmittedBy.label"  /></th>
	                      <th> <g:message code="default.PreProposalStatus.label" /></th>
	                      <th><g:message code="default.ApprovalAuthority.label"  /></th>
	                      <g:if test="${proposalType=='FullProposal'}">
	                       <th><g:message code="default.BudgetDetails.label"/></th>
	                      </g:if> 
	                      <th><g:message code="default.Approve/Reject.label" /></th>
                         </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preProposalInstanceList}" status="i" var="proposalInstance" >
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${i+1}</td>
                            <td>${proposalApplicationList[i].projectTitle}</td>
                            <td>${proposalInstance.person.username}</td>
                            <td>${proposalInstance.proposalStatus}</td>
                            <td>${proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.name}</td>
                            <td>
                        <g:if test="${proposalApprovalAuthorityMapInstanceList[i].viewAll!='Y'}">
                        	<g:link controller="proposalApprovalDetail" action="preProposalReview" id="${proposalInstance.id}" params="['id':proposalInstance.id,'proposalApprovalAuthorityMap.id':proposalApprovalAuthorityMapInstanceList[i].id]" id="${proposalInstance.id}"><g:message code="default.Approve/Reject.label" /></g:link>
                        	</g:if>
                        	<g:else>
                        	<g:link controller="proposal" action="submittedPreProposal" id="${proposalInstance.id}"><g:message code="default.View.label"/></g:link>
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
