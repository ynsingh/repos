<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
       <title><g:message code="default.FullProposalReviewalStatus.label"/></title> 
    </head>
    <body>
        <div class ="wrapper">
        <div class="body">
        
           <h1>FullProposal Reviewal Status<h1>
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
      <td> ${proposalApprovalStatus?.size()} </td>
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
      </g:if>
      <g:else>
     <g:message code="default.NoDetails.label"/>
      </g:else>
      
      </table>
    
       </div> 
       
     </div>
       </div>
    </body>
</html>
