<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
       <title><g:message code="default.PreProposalReviewalStatus.label"/></title> 
    </head>
    <body>
        <div class ="wrapper">
        <div class="body">
        
           <h1><g:message code="default.PreProposalReviewalStatus.label"/><h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>

             <div class="dialog">
                <table>
             <g:if test="${authorityInstance}">   
       
      <tr>
     
                
      <td><g:message code="default.TotalAuthoritiesforreviewal.label"/>:<td>
      <td> ${authorityInstance?.size()} </td>
      </tr>
      
      <tr>
      </tr>
      <tr>
      </tr>
      <tr>
      </tr>
      <g:if test="${proposalInstance.proposalStatus=='Approved'}">  
           <td><g:message code="default.PreProposalReviewalStatus.label"/>:<td>
           <td><g:message code="default.ReviewCompleted.label"/><td>
          </g:if> 
          <g:else>
      <tr>
     
             
          
      <td><g:message code="default.CurrentApprovalAuthority.label"/>:<td>
      <td> ${currentApprovalAuthority?.name} </td>
      
      </tr>
      
      <tr>
      </tr>
      <tr>
      </tr>
      <tr>
      </tr>
      
      <tr>
     
                
      <td><g:message code="default.TotalMembersInCurrentApprovalAuthority.label"/>:<td>
       <td> ${currentApprovalAuthorityMembers?.size()} </td>
      </tr>
      
      <tr>
      </tr>
      <tr>
      </tr>
      <tr>
      </tr>
      
      <tr>
     
                
      <td><g:message code="default.ReviewedMembersInCurrentAuthority.label"/>:<td>
      <td> ${proposalDetailInstance?.size()} </td>
      </tr>
      </g:else>
      </g:if>
      <g:else>
     <g:message code="default.NoDetails.label"/>
      </g:else>
      
      </table>
   
       </div> </div> 
       <g:if test="${proposalApprovalDetailInstanceList}">
       <div class="body">
            <h1><g:message code="default.ReviewDetails.label" args="[entityName]" /></h1>
       <div class="list">
                 <table cellspacing="0" cellpadding="0">
		            <thead>
                        <tr>
						    <th><g:message code="default.ApprovalAuthority.label"/></th> 	
						    
						     <g:sortableColumn property="name" title="${message(code: 'default.Reviewer.label', default: 'Name')}" />
                            
                            <g:sortableColumn property="proposalStatus" title="${message(code: 'default.status.label', default: 'ProposalStatus')}" />
     
                            <g:sortableColumn property="remarks" title="${message(code: 'default.Remarks.label', default: 'Remarks')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalApprovalDetailInstanceList}" status="i" var="proposalApprovalDetailInstanceList">
                       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				        
				        <td>${fieldValue(bean: proposalApprovalDetailInstanceList.proposalApproval.proposalApprovalAuthorityMap.approvalAuthority, field: "name")}</td>
				         
				          <td>${fieldValue(bean: proposalApprovalDetailInstanceList.proposalApproval.approvalAuthorityDetail.person, field: "userRealName")}</td>
                        
                          <td>${fieldValue(bean: proposalApprovalDetailInstanceList, field: "proposalStatus")}</td>
                        
                          <td>${fieldValue(bean: proposalApprovalDetailInstanceList, field: "remarks")}</td>
				        
					   </tr>
                     
			              
                          
                    </g:each>
                    </tbody>
                </table>
            </div>
     </div></g:if>
       </div>
    </body>
</html>
