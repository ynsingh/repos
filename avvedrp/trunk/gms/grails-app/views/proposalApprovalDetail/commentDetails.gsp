

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
     </head>
    <body>
    <g:javascript library="application"/>
    <modalbox:modalIncludes/>
    <div class="wrapper">
     <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
            <g:if test="${proposalApprovalDetailInstance.remarks != null}">
              <td>${proposalApprovalDetailInstance.remarks}</td>
            </g:if>
           <g:else>
          	<td><g:message code="default.NoDetails.label"/></td>
      	   </g:else>
       </div>         
      </div>
    </div>
    </body>
</html>
                    
                    
       