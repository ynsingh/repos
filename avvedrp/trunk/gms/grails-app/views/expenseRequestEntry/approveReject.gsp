<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.RequestEvaluation.label"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
    		<g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
	    	 <div class="body">
	            <h1><g:message code="default.RequestEvaluation.label"/></h1> 
	     		 
	     		 <div class="dialog">
	                    <table>
	                        <tbody>
	                        	
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="projects"><g:message code="default.Projects.label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.projects.code}</td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="purchaseOrderNo"><g:message code="default.PurchaseOrderNo.label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.purchaseOrderNo}</td>
	                                
	                            </tr>
	                        
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="expenseDescription"><g:message code="default.ExpenseDescription.label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.expenseDescription}</td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="purchaseOrderDate"><g:message code="default.PurchaseOrderDate.label" /></label>
	                                </td>
	                                <td><g:formatDate format="dd MMM yyyy" date="${expenseRequestEntryInstance.purchaseOrderDate}"/></td>
	                            </tr>
	                        	
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="dateOfExpense"><g:message code="default.ExpenseDate.label" /></label>
	                                </td>
	                                <td><g:formatDate format="dd MMM yyyy" date="${expenseRequestEntryInstance.dateOfExpense}"/></td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="expenseAmount"><g:message code="default.ExpenseAmount(Rs).label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.expenseAmount}</td>
	                                
	                            </tr>
	                        	
	                            <tr class="prop">
			                        <td valign="top" class="name">
		                                <label for="invoiceNo"><g:message code="default.InvoiceNo.label" /></label>
		                            </td>
		                            <td >${expenseRequestEntryInstance.invoiceNo}</td>
		                             
		                             <td valign="top" class="name">
		                                <label for="invoiceDate"><g:message code="default.InvoiceDate.label"/></label>
		                            </td>
		                            <td><g:formatDate format="dd MMM yyyy" date="${expenseRequestEntryInstance.invoiceDate}"/></td>
		                        </tr>
		                        
		
								<tr class="prop">
			                        <td valign="top" class="name">
		                                <label for="invoiceAmount"><g:message code="default.InvoiceAmount.label" /></label>
		                            </td>
		                            <td>
	                                    ${expenseRequestEntryInstance.invoiceAmount}
	                                </td>
	                                
	                                
		                             <td valign="top" class="name">
		                                <label for="view"><g:message code="default.Invoice.label" /></label>
		                            </td>
		                            <td>
			                            <g:link action="create"  controller='attachments' 
				                            id="${expenseRequestEntryInstance.id}" params="[trackType:'expenseRequestEntry']">
				                            <g:message code="default.View/Upload.label"/>
			                            </g:link>
				                        
		                            </td>
		                       	 </tr>
		                       	 
		                       	 
	                        	 <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="remarks"><g:message code="default.Remarks.label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.remarks}</td>
	                            </tr>
	                            
                        	</tbody>
	                    </table>
	                </div>
	                <h1><g:message code="default.RequestEvaluation.label"/></h1> 
	                	<g:form method="post" name="expenseApRej">
			                <div class="dialog">
			                	<g:hiddenField name="proposalApprovalAuthorityMapId" value="${proposalApprovalAuthorityMapInstance.id}" /> 
			                	<g:if test="${proposalApprovalDetailInstance.id == null}">
			                		<table>
			                			<tr class="prop">
					                       	<td valign="top" class="name"> 
					                       		
						                       		<label for="status"><g:message code="default.RequestEvaluationStatus.label"/>:</label>
						                       		<label for="symbol" style="color:red;font-weight:bold"> * </label>
						                       		</td>
								            		 
								            		<td><g:radio name="status" value="Approved"/> <label for=' Approved '><g:message code="default.Approved.label"/></label>
											  			<br>
											  			<br>	
											  			<g:radio name="status" value="Rejected" /> <label for=' Rejected '><g:message code="default.Rejected.label"/></label>
										  			</td>
								            		
													<td valign="top" class="name">
									                	<label for="description"><g:message code="default.Description.label"/>:</label>
									            	</td>
									           	 	<td>
									            		<g:textArea name="description" value="${proposalApprovalDetailInstance?.remarks}" rows="3" cols="30"/></td>
					                       			</td>
									         	
					                       	 	</tr>
				                       	 	</table>
				                   			<div class="buttons">
			                					<span class="button"><g:actionSubmit value="${message(code: 'default.Submit.button')}" onClick="return validateStatusApproveReject()" action="submitApproval" /> 
				                   			</div>
		                   			</g:if>
		                   			<g:else>
		                   				<table>
				                			<tr class="prop">
						                       	<td valign="top" class="name"> 
					                       			<label for="status"><g:message code="default.RequestEvaluationStatus.label"/>:</label></td>
								            	<td>${proposalApprovalDetailInstance.proposalStatus}</td>
									            <td valign="top" class="name">
				                                	<label for="sendForApprovalTo"><g:message code="default.ForApprovalto.label"/></label></td>
			                                	<td>
			                                		<g:select name="approvalAuthority.id" from="${approvalAuthorityInstance}" optionKey="id" optionValue = "name" value="${proposalApprovalAuthorityMapInstanceList?.approvalAuthority?.id}" noSelection="['null':'-Select-']"/>
			                                	</td>
									         </tr>
		                       	 	  		 <tr class="prop">
												<td valign="top" class="name">
								                	<label for="description"><g:message code="default.Description.label"/>:</label>
								            	</td>
								           	 	<td>
								            		<g:textArea name="description" value="${proposalApprovalDetailInstance?.remarks}" rows="3" cols="30" disabled="true"/></td>
				                       			</td>
			                        		</tr>
			                        	</table>
			                       
		                	   			<div class="buttons">
		                					<span class="button"><g:actionSubmit value="${message(code: 'default.Send.button')}" onClick="return validateApprovalAuthorityApproveReject()" action="sendRequest" /> 
			                   				<span class="button"><g:actionSubmit value="${message(code: 'default.EvaluationCompleted.button')}" onClick="" action="processComplete" /> 
			                   			</div>
		                   			 </g:else>
	                   			</div>
                	  		</g:form>
	                
                 
     			</div>
 			</div>
		</body>
</html>