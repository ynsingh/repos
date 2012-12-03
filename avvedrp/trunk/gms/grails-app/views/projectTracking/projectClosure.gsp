
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectFundDetails.head"/></title>         
    </head>
   
    <body>
        <div class="wrapper"> 
            <div class="body">
            
             <h1><g:message code="default.ProjectFundDetails.head"/></h1>
             <g:if test="${flash.message}">
                <div class="message">${flash.message}
                </div>
             </g:if>
             <g:hasErrors bean="${projectTrackingInstance}">
                <div class="errors">
                     <g:renderErrors bean="${projectTrackingInstance}" as="list" />
                </div>
               </g:hasErrors>
			   <g:form name="fundDet" action="closeProject" method="post" >
	               <div class="dialog">
	                           <table>
	                                <tr>
					               <td><g:message code="default.AmountAllocated.label"/></td>
						           <td>${currencyFormat.ConvertToIndainRS(projectsInstance.totAllAmount)}</td> 
						            <td><g:message code="default.ExpenseAmount(Rs).label"/></td>
						           <td>${currencyFormat.ConvertToIndainRS(sumAmount)}</td>
						         </tr> 
						         <tr>  
						            <td><g:message code="default.AmountReceived.label"/></td>
						            <td >${currencyFormat.ConvertToIndainRS(sumGrantRecieve)}</td>
						            <td><g:message code="default.fundTransfer.amounttransferred.label"/></td>
						            <td>${currencyFormat.ConvertToIndainRS(sumTransferInstance)} </td>
					            </tr> 
					            <tr>
					             <td><g:message code="default.AmountRefunded.label"/></td>
						         <td>${currencyFormat.ConvertToIndainRS(amtRefund)}</td>
					            </tr>
	                           </table>
	                      
	                       <div class="buttons">
						       <span class="button"><input class="save" type="submit" value="${message(code: 'default.closeProject.button')}" /></span>
						   </div>
	              </div>
	            </g:form>          	     
         </div>
      </div>
	</body>
</html>