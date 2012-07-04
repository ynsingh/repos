<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.RequestEvaluation.label"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
    		
	    	 <div class="body">
	            <h1><g:message code="default.RequestEvaluation.label"/></h1> 
	     		 <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
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
	                                <td>${currencyFormat.ConvertToIndainRS(expenseRequestEntryInstance.expenseAmount)}</td>
	                                
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
			                            <g:link action="invoiceAttachList" id="${expenseRequestEntryInstance.id}">
				                            <g:message code="default.View.label"/>
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
	                <div class="dialog">
	            	<h1><g:message code="default.accountDetails.label"/></h1> 
	                    <table>
	                        <tbody>
	                        	
	                            <tr class="prop">
	                               <td width="150px">
	                               <label for="currentBalance"><g:message code="default.CurrentBalance.label"/>:</label>
	                               </td>
	                                <td>${currencyFormat.ConvertToIndainRS(totAllAmount-totExpense-sumSubGrantAllot)}
	                                (<g:message code="default.againstFundAllocation.label"/>)
	                                </td>
	                            </tr>
	                            <tr class="prop">
	                            <td width="150"></td>
	                            <td>${currencyFormat.ConvertToIndainRS(receivedAmount-totExpense-sumTransferInstance)}
	                                (<g:message code="default.againstFundReceived.label"/>)
	                                </td>
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
				                                	<label for="sendForApprovalTo"><g:message code="default.ForApprovalto.label"/></label>
				                                	<label for="sendForApprovalTo" style="color:red;font-weight:bold"> * </label>
						                       		</td>
				                                	
			                                	<td>
			                                		<g:select name="approvalAuthority.id" from="${approvalAuthorityInstance}" optionKey="id" optionValue = "name" onchange="${remoteFunction(controller:'expenseRequestEntry',action:'approvalAuthoritySelect',update:'apprvlAuthrtySelt',  params:'\'approvalAuthority.id=\' + this.value' )}"
			                                		value="${proposalApprovalAuthorityMapInstanceList?.approvalAuthority?.id}" noSelection="['null':'-Select-']"/>
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
			                       
		                	   				<div id="apprvlAuthrtySelt"> 
					                			<span class="button"><g:actionSubmit value="${message(code: 'default.Send.button')}"  disabled="false"  onClick="return validateApprovalAuthorityApproveReject()" action="sendRequest" />
					                		</div>
					                			<span class="button" style="margin-left:100px;margin-top:-24px; position:absolute;"><input type="submit" onclick="" value="Evaluation Completed " name="_action_processComplete">
						           </g:else>
	                   		</div>
                	  </g:form>
	   			</div>
 			</div>
		</body>
</html>