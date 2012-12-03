
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectFundDetails.head"/></title>         
    </head>
   
    <body>
    <g:subMenuList/>  
        <div class="wrapper"> 
            <div class="body">
             <g:if test="${flash.message}">
                <div class="message">${flash.message}
                </div>
             </g:if>
             <g:if test="${flash.error}">
                <div class="errors">${flash.error}
                </div>
             </g:if>
             <g:hasErrors bean="${projectTrackingInstance}">
                <div class="errors">
                     <g:renderErrors bean="${projectTrackingInstance}" as="list" />
                </div>
               </g:hasErrors>
           <g:if test="${params.refund != 'NotRefund'}">
			 <g:if test="${projectsInstance.parent !=null}">     
			  <g:form name="amtRfnd" action="refundAmount" method="post" >
			   <h1><g:message code="default.ProjectRefundFundDetails.head"/></h1>
			 	 <div class="dialog">
	                           <table>
	                                <tr>
					               <td><g:message code="default.AmountAllocated(Rs).label"/></td>
						           <td>${currencyFormat.ConvertToIndainRS(projectsInstance.totAllAmount)}</td> 
						            <td><g:message code="default.ExpenseAmount(Rs).label"/></td>
						           <td>${currencyFormat.ConvertToIndainRS(sumAmount[0].doubleValue())}</td>
						         </tr> 
						         <tr>  
						            <td><g:message code="default.AmountReceived.label"/></td>
						            <td >${currencyFormat.ConvertToIndainRS(sumGrantRecieve.doubleValue())} </td>
						             <g:hiddenField name="AmountReceived" value="${sumGrantRecieve}" />
						            <td><g:message code="default.fundTransfer.amounttransferred.label"/></td>
						            <td>${currencyFormat.ConvertToIndainRS(sumTransferInstance[0].doubleValue())}</td>
					            </tr> 
					             <tr>  
						            <td><g:message code="default.BalanceAmount.label"/></td>
						            <td >${currencyFormat.ConvertToIndainRS(balance.doubleValue())} </td>
						            <g:hiddenField name="balnc" value="${balance}" />
						         </tr> 
	                           </table>
	              </div>
                  <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="refundAmount"><g:message code="projectRefund.refundAmount.label" default="Refund Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'refundAmount', 'errors')}">
                                 <input type="text" id="refundAmount" name="refundAmount" value="${refundAmount}" style="text-align: right" />
                                </td>
                            </tr>

					        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfRefund"><g:message code="projectRefund.dateOfRefund.label" default="Date Of Refund" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'dateOfRefund', 'errors')}">
                                    <calendar:datePicker id="dateOfTracking" name="dateOfRefund" defaultValue="${new Date()}" value="${projectRefundInstance?.dateOfRefund}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="projectRefund.remarks.label" default="Remarks" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectRefundInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${projectRefundInstance?.remarks}" rows="3" cols="30"/>
                                </td>
                            </tr>
                        
                      </tbody>
                    </table>
                        <div class="buttons">
                             <span class="button"><input class="save" type="submit" onClick="return validateGrantFundReFund()" value="${message(code: 'default.RefundAmount.button')}" /></span>
                        </div>
                      </div>
                    </g:form>
            </g:if>  
         </g:if> 
          <g:else>
                 <g:form name="fundDet" action="closeProject" method="post" >
                   <h1><g:message code="default.ProjectFundDetails.head"/></h1>
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
	                           </table>
	                      
	                       <div class="buttons">
						       <span class="button"><input class="save" type="submit"  value="${message(code: 'default.closeProject.button')}" /></span>
						   </div>
	              </div>
	            </g:form>   
	       </g:else>
		 </div>
      </div>
	</body>
</html>