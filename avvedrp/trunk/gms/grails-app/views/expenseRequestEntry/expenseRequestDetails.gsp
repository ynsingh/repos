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