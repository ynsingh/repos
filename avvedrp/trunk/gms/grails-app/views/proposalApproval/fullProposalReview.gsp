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
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
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
                           
                           <th><g:message code="default.SubmittedBy.label"  /></th>
                            
                           <th> <g:message code="default.ReviewerStatus.label" /></th>
                            
                           <th><g:message code ="default.Comments.label" /></th>
                       </tr>
                           
                       </thead>
                      <tbody>
                        <% int j=0 %>
                         <% int l=0 %>
                    <g:each in="${ProposalApprovalValueInstance}" status="i" var="proposalApprovalInstance">
                      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                       <td>${i+1}</td>
	                      <td>${proposalApplicationInstanceList[j]?.projectTitle}</td>
	                      <%  j++ %>
	                       <td>${proposalInstanceList[l].person.username}</td>
	                       <%  l++ %>
	                      <td>${fieldValue(bean: proposalApprovalInstance, field: "proposalStatus")}</td>
	                      <g:if test="${proposalApprovalInstance.remarks == null}">
					          <td>No Comments</td>
					      </g:if>
					      <g:else>
			    		      <td><input type="hidden" name="detailId${i}" id="detailId${i}" value="${proposalApprovalInstance?.id}">
	                          <g:link controller ="proposalApproval" action="fullProposalReview"  onclick="return validateApproved(document.getElementById('detailId${i}').value)"><g:message code="default.View.label"/></g:link></td>
         			      </g:else>
                           </tr>
                      </g:each>
                
                      </tbody>
                </table>
            </div>
        </div>
        </div>
    </body>
</html>
              