<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'externalFundRefund.label', default: 'ExternalFundRefund')}" />
        <title><g:message code="default.editRefundList.label" /></title>
    </head>
    <body>
    	<div class="wrapper"> 
	        <g:subMenuList/>  
	        <div class="proptable">    
	            <table width="100%" align="left">
	              <tr>
	             	<td ><g:message code="default.ProjectCode.label"/>:</td>
		            <td><strong>${grantAllocationInstance.projects.code}</strong></td>
		            <td ><g:message code="default.AmountGranted.label"/>:</td>
		            <td ><strong>
		            		${currencyFormat.ConvertToIndainRS(grantAllocationInstance.amountAllocated)}
	            		</strong>
	        		</td>
        		</tr> 
        		<tr>
        			<td ><g:message code="default.AmountReceived.label"/>:</td>
		            <td ><strong>
		            		${currencyFormat.ConvertToIndainRS(totalAmtReceived)}
	            		</strong>
	        		</td>
	        		<td ><g:message code="default.AmountToRefund.label"/>:</td>
		            <td ><strong>
		            		${currencyFormat.ConvertToIndainRS(balance)}
	            		</strong>
	        		</td>
		          </tr> 
	          </table> 
      	 	</div> 
	        <div class="body">
	            <h1><g:message code="default.editRefundList.label" /></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:if test="${flash.error}">
            		<div class="errors">${flash.error}</div>
            	</g:if>
	            <g:hasErrors bean="${externalFundRefundInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${externalFundRefundInstance}" as="list" />
	            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${externalFundRefundInstance?.id}" />
	                <g:hiddenField name="version" value="${externalFundRefundInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                                                                          
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="dateOfRefund"><g:message code="externalFundRefund.dateOfRefund.label" default="Date Of Refund" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundRefundInstance, field: 'dateOfRefund', 'errors')}">
	                                     <calendar:datePicker name="dateOfRefund" defaultValue="${new Date()}" value="${externalFundRefundInstance?.dateOfRefund}" dateFormat= "%d/%m/%Y"/>
	                                    
	                                </td>
	                            
	                                <td valign="top" class="name">
	                                  <label for="refundAmount"><g:message code="externalFundRefund.refundAmount.label" default="Refund Amount" /></label>
	                                  <label for="refundAmount" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundRefundInstance, field: 'refundAmount', 'errors')}">
	                                    <g:textField name="refundAmount" value="${amount}" />
	                                </td>
	                            </tr>
	                        
	                        	<tr class="prop">
	                        		<td valign="top" class="name">
	                                    <label for="refundingStatus"><g:message code="default.RefundingStatus.label" /></label>
	                                    <label for="refundingStatus" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundRefundInstance?.externalFundAllocation?.status, field: 'status', 'errors')}">
	                                    <g:select  name="refundingStatus" from ="${['Partialy Refunded', 'Closed']}"  value="${externalFundRefundInstance?.externalFundAllocation?.status}" />
	                                </td>
	                        		
	                                <td valign="top" class="name">
	                                  <label for="remarks"><g:message code="externalFundRefund.remarks.label" default="Remarks" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundRefundInstance, field: 'remarks', 'errors')}">
	                                    <g:textArea  name="remarks" value="${externalFundRefundInstance?.remarks}" rows="3" cols="30" />
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateGrantFundReFund()" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
	                    
	                </div>
	            </g:form>
	        </div>
         </div>
    </body>
</html>
