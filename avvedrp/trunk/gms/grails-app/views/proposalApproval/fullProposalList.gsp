

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.FullProposalList.label')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
      
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.FullProposalList.label"/></h1>
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
                        
                            <th><g:message code="default.ProposalTitle.label"/></th>
                           
                            <th><g:message code="default.Investigator.label"/></th>
                            
                            <th><g:message code="default.FullProposalStatus.label"/></th>
                            <th><g:message code="default.ApprovalAuthority.label"  /></th>
                            <th><g:message code="default.Approve/Reject.label" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fullProposalApprovalInstanceList}" status="i" var="proposalInstance" >
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                           <td>${i+1}</td>
                        
                            <td>${proposalInstance.preProposal.party.code}</td>
                            
                            <td>${proposalInstance.preProposal.projectTitle}</td>
                            <td>${proposalInstance.preProposal.person.username}</td>
                            
                            <td>${proposalInstance.proposalStatus}</td>
                           <td>${proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.name}</td>
                        <td>
                        <g:if test="${proposalApprovalAuthorityMapInstanceList[i].viewAll!='Y'}">
                        	<g:link controller="proposalApprovalDetail" action="fullProposalReview" id="${proposalInstance.id}" params="['id':proposalInstance.id,'proposalApprovalAuthorityMap.id':proposalApprovalAuthorityMapInstanceList[i].id]" id="${proposalInstance.id}"><g:message code="default.Approve/Reject.label" /></g:link>
                        	</g:if>
                        	<g:else>
                        	<g:link controller="preProposal" action="submittedPreProposal" id="${proposalInstance.id}"><g:message code="default.View.label"/></g:link>
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
