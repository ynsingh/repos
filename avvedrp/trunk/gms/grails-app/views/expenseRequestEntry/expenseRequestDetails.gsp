<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.ExpenseRequestDetails.label"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
	    	 <div class="body">
	            <div class="dialog">
<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
	            	<h1><g:message code="default.ExpenseRequestDetails.label"/></h1> 
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
	                <h1><g:message code="default.InvoiceDetails.label"/></h1> 
	                <g:form method="post" >
		                <div class="dialog">
		                <input type="hidden" id="expenseRequestEntry" name="expenseRequestEntry" value="${expenseRequestEntryInstance.id}"/>
		                    <table>
			                    <tr class="prop">
			                        <td valign="top" class="name">
		                                <label for="invoiceNo"><g:message code="default.InvoiceNo.label" /></label>
		                            </td>
		                            <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'invoiceNo', 'errors')}">
	                                    <g:textField name="invoiceNo" value="${expenseRequestEntryInstance?.invoiceNo}" />
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
		                                <label for="invoiceDate"><g:message code="default.InvoiceDate.label"/></label>
		                            </td>
		                            <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'invoiceDate', 'errors')}">
	                                    <calendar:datePicker name="invoiceDate" defaultValue="${new Date()}" value="${expenseRequestEntryInstance?.invoiceDate}" dateFormat= "%d/%m/%Y"/>
	                                </td>
		                        	 
		                        	 <td valign="top" class="name">
		                                <label for="sendForApprovalTo"><g:message code="default.ForApprovalto.label"/></label>
		                                <label for="symbol" style="color:red;font-weight:bold"> * </label>
		                                <td>
		                                	<g:select name="approvalAuthority.id" from="${approvalAuthorityInstance}" optionKey="id" optionValue = "name" value="${proposalApprovalAuthorityMapInstance?.approvalAuthority?.id}" noSelection="['null':'-Select-']"/>
		                                </td>
		                            </td>
		                         </tr>
		
								<tr class="prop">
			                        <td valign="top" class="name">
		                                <label for="invoiceAmount"><g:message code="default.InvoiceAmount.label" /></label>
		                            </td>
		                            <td>
	                                    <g:textField name="invoiceAmount" value="${invoiceAmount}" />
	                                </td>
		                       	 </tr>
			                  </table>
		                   </div>
		                
		                   
	                      	<div class="buttons">
                    			<span class="button"><g:actionSubmit value="${message(code: 'default.Submit.button')}" onClick="return validateExpenseRequestDetails()" action="submit"/> 
		                   </div>
	                   </g:form>
	     			</div>
     			</div>
		</body>
</html>
