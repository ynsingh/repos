

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
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
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
                        
                            <th><g:message code="default.ProposalTitle.label"/></th>
                           
                            <th><g:message code="default.SubmittedBy.label"/></th>
                            
                            <th><g:message code="default.FullProposalStatus.label"/></th>
                            <th><g:message code="default.ApprovalAuthority.label"  /></th>
                           <th><g:message code="default.BudgetDetails.label"/></th>
	                        <th><g:message code="default.Approve/Reject.label" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fullProposalApprovalInstanceList}" status="i" var="proposalInstance" >
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${i+1}</td>
                            <td>${proposalApplicationList[i].projectTitle}</td>
                            <td>${proposalInstance.person.username}</td>
                            <td>${proposalInstance.proposalStatus}</td>
                            <td>${proposalApprovalAuthorityMapInstanceList[i].approvalAuthority.name}</td>
                            <td><g:link action="budgetList" controller='budgetDetails' id="${fieldValue(bean:proposalInstance, field:'id')}"  params="[moduleType:'FullProposal']"><g:message code="default.View.label"/></g:link></td>
                            <td>
                      		  <g:if test="${proposalApprovalAuthorityMapInstanceList[i].viewAll!='Y'}">
                         		<g:link controller="proposalApprovalDetail" action="fullProposalReview" id="${proposalInstance.id}" params="['id':proposalInstance.id,'proposalApprovalAuthorityMap.id':proposalApprovalAuthorityMapInstanceList[i].id]" id="${proposalInstance.id}"><g:message code="default.Approve/Reject.label" /></g:link>
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
