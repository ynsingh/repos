<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'externalFundRefund.label', default: 'ExternalFundRefund')}" />
        <title><g:message code="default.refundGrant.label" /></title>
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
	        		<td ><g:message code="default.AmountReceived.label"/>:</td>
		            <td ><strong>
		            		${currencyFormat.ConvertToIndainRS(totalAmtReceived)}
	            		</strong>
	        		</td>
			<td>
			     <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help"> 
			</td>
        		</tr> 
        		<tr>
        			<td ><g:message code="default.TypeOfGrant.label"/>:</td>
		            <td ><strong>
		            	<g:if test="${extrnlFndAllocatnInstance.refundable == 'Y'}">
                        	Refundable
                        </g:if>	
         				<g:else>
         					NonRefundable
         				</g:else>
		            		
	            		</strong>
	        		</td>
	        		<td ><g:message code="default.RefundingStatus.label"/>:</td>
		            <td><strong>${extrnlFndAllocatnInstance.status}</strong></td>
		          </tr> 
	          </table> 
      	 	</div> 
	        <div class="body">
	            <h1><g:message code="default.refundGrant.label" /></h1>
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
	            <g:form action="save" method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="dateOfRefund"><g:message code="default.DateOfRefund.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundRefundInstance, field: 'dateOfRefund', 'errors')}">
	                                    <calendar:datePicker name="dateOfRefund" defaultValue="${new Date()}" value="${externalFundRefundInstance?.dateOfRefund}" dateFormat= "%d/%m/%Y"/>
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="refundAmount"><g:message code="default.RefundingAmount.label" /></label>
	                                    <label for="refundAmount" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundRefundInstance, field: 'refundAmount', 'errors')}">
	                                    <g:textField name="refundAmount" value="${refundAmount}" />
	                                    <g:hiddenField name="grantAllocationId" value="${grantAllocationInstance?.id}" /> 
	                                </td>
	                            </tr>
	                            
	                            <tr class="prop">
	                            	<td valign="top" class="name">
	                                    <label for="refundingStatus"><g:message code="default.RefundingStatus.label" /></label>
	                                    <label for="refundingStatus" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundAllocationInstance, field: 'status', 'errors')}">
	                                    <g:select  name="refundingStatus" from ="${['Partialy Refunded', 'Closed']}"  value="${fieldValue(bean: externalFundAllocationInstance, field: 'status')}" noSelection="['null':'-Select-']" />
	                                </td>
	                            	
	                                <td valign="top" class="name">
	                                    <label for="remarks"><g:message code="default.Remarks.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: externalFundRefundInstance, field: 'remarks', 'errors')}">
	                                    <g:textArea name="remarks" value="${externalFundRefundInstance?.remarks}" rows="3" cols="30"/>
	                                    
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:submitButton name="create" class="save" onClick="return validateGrantFundReFund()" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
	                </div>
	            </g:form>
	            <h1><g:message code="default.RefundList.head"/></h1> 
		        <div class="list">
			        <g:if test="${RefundList}">
		                <table>
		                    <thead>
		                        <tr>
		                        
		                            <th><g:message code="default.SINo.label"/></th>
		                        
		                            <th><g:message code="default.DateOfRefund.label"/></th>
		                            
		                            <th><g:message code="default.RefundedAmount.label"/></th>
		                             
		                            <th><g:message code="default.Remarks.label"/></th>
		                            
		                            <th><g:message code="default.Edit.label"/></th>
		                         </tr>
	                    	</thead>
		                    <tbody>
			                    <g:each in="${RefundList}" status="i" var="externalRefundInstance">
			                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                        	<td>${i+1}</td>
			                        	
			                        	<td><g:formatDate format="dd/MM/yyyy" date="${externalRefundInstance.dateOfRefund}"/></td>
			                        	
			                        	<td>${currencyFormat.ConvertToIndainRS(externalRefundInstance.refundAmount)}</td>
			                        	
			                        	<td>${externalRefundInstance.remarks}</td>
			                        				                        	
			                        	<td><g:link action="edit" id="${externalRefundInstance.id}">
					                     			<g:message code="default.Edit.label"/> 
			                        		</g:link> </td>
			                        </tr>
			                    </g:each>
		                    </tbody>
	            		</table>
	            	</g:if>
					<g:else>
						<br><g:message code="default.NoRecordsAvailable.label"/></br>
					</g:else>
				</div>
	        </div>
        </div>
    </body>
</html>
