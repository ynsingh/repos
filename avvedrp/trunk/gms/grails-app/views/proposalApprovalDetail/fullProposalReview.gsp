

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fullProposalDetail.label', default: 'FullProposalDetail')}" />
        <title><g:message code="default.FullProposalReview.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <div class="body">
        <div class ="wrapper">
            <h1><g:message code="default.FullProposalReview.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Proposal Title</td>
                            
                            <td valign="top" class="value">${fullProposalDetailInstance.projectTitle}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fullProposalDetail.fileName.label" default="File Name" /></td>
                            
                            <td valign="top" class="value"><a href="${g.createLink(controller:'proposal', action:'download', id:fullProposalDetailInstance?.proposal?.id)}"><g:message code="${fullProposalDetailInstance?.proposal?.proposalDocumentationPath}" encodeAs="HTML"/></a></td>
                    
                       
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fullProposalDetail.comments.label" default="Comments" /></td>
                            
                            <td valign="top" class="value">${fullProposalDetailInstance.proposal.lockedYN}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="fullProposalDetail.proposalSubmittedDate.label" default="Proposal Submitted Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${fullProposalDetailInstance?.applicationSubmitDate}" /></td>
                            
                        </tr>
                    
                      
                        <tr>
                    <td>
		   		 	     	 <g:message code="default.Remarks.label" default="Project Title" />
						 </td>
		   		 	     <td>
		   		 	     	 <g:textArea id="myField" name="myField" value="" rows="5" cols="40"/>
						 </td>
					 </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="button">
                <table>
                        <tbody><tr>
		   		 	     <td>
                    <g:form method="post" controller="proposalApprovalDetail" >
     <input type=hidden id="proposalApprovalAuthorityMap.id" name="proposalApprovalAuthorityMap.id" value="${proposalApprovalAuthorityMapInstance?.id}">
     <input type=hidden id="approvalAuthorityDetail.id" name="approvalAuthorityDetail.id" value="${approvalAuthorityDetailInstance?.id}">
     <input type=hidden id="proposalId" name="proposalId" value="${proposalApprovalAuthorityMapInstance?.proposalId}">
     <input type=hidden id="proposalStatus" name="proposalStatus" value="Approved">
     <input type=hidden id="remar" name="remarks" value="">
      <g:actionSubmit class="inputbutton" value="Approve" action="saveFullProposal" onclick="(document.getElementById('remar').value = document.getElementById('myField').value);"/>
      </g:form> </td>
                            
		   		 	     <td>
      <g:form method="post" controller="proposalApprovalDetail" >
      <input type=hidden id="proposalApprovalAuthorityMap.id" name="proposalApprovalAuthorityMap.id" value="${proposalApprovalAuthorityMapInstance?.id}">
     <input type=hidden id="approvalAuthorityDetail.id" name="approvalAuthorityDetail.id" value="${approvalAuthorityDetailInstance?.id}">
     <input type=hidden id="proposalId" name="proposalId" value="${proposalApprovalAuthorityMapInstance?.proposalId}">
     <input type=hidden id="proposalStatus" name="proposalStatus" value="NeedMoreInfo">
     <input type=hidden id="remar1" name="remarks" value="">
      <g:actionSubmit class="inputbutton" value="Need More Info" action="saveFullProposal" onclick="(document.getElementById('remar1').value = document.getElementById('myField').value);"/>
     </g:form></td>
                           
		   		 	     <td>
     <g:form method="post" controller="proposalApprovalDetail" >
     <input type=hidden id="proposalApprovalAuthorityMap.id" name="proposalApprovalAuthorityMap.id" value="${proposalApprovalAuthorityMapInstance?.id}">
     <input type=hidden id="approvalAuthorityDetail.id" name="approvalAuthorityDetail.id" value="${approvalAuthorityDetailInstance?.id}">
     <input type=hidden id="proposalId" name="proposalId" value="${proposalApprovalAuthorityMapInstance?.proposalId}">
     <input type=hidden id="proposalStatus" name="proposalStatus" value="Rejected">
     <input type=hidden id="remar2" name="remarks" value="">
      <g:actionSubmit class="inputbutton" value="Reject" action="saveFullProposal" onclick="(document.getElementById('remar2').value = document.getElementById('myField').value);" />
       </g:form> </td>
                            </tr>
                            </tbody>
                    </table>
                </div>
        </div>
    </body>
</html>
