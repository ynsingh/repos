<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantExpense.ExpenseEntry.head"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
    		
	    	<div class="body">
	     		<h1><g:message code="default.ExpenseRequestDetails.label"/></h1>  
	     		<g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
	            <div class="errors">${flash.error}</div>
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
                                          <label for="dateOfExpense"><g:message code="default.ExpenseDate.label"/></label> 
                                     </td>
                                     <td><g:formatDate format="dd MMM yyyy" date="${expenseRequestEntryInstance.purchaseOrderDate}"/></td>
                                     
	                               <td valign="top" class="name">
	                                    <label for="purchaseOrderDate"><g:message code="default.PurchaseOrderDate.label" /></label>
	                                </td>
	                                <td><g:formatDate format="dd MMM yyyy" date="${expenseRequestEntryInstance.purchaseOrderDate}"/></td>
	                            </tr>
	                        	
	                        	<tr class="prop">
	                        		 <td valign="top" class="name">
	                                    <label for="expenseDescription"><g:message code="default.ExpenseDescription.label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.expenseDescription}</td>
	                                
	                                <td valign="top" class="name">
		                                <label for="invoiceNo"><g:message code="default.InvoiceNo.label" /></label>
		                            </td>
		                            <td >${expenseRequestEntryInstance.invoiceNo}</td>
		                        	
	                            </tr>
	                        	
	                            <tr class="prop">
			                         
	                                <td valign="top" class="name">
	                                    <label for="expenseAmount"><g:message code="default.ExpenseAmount(Rs).label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.expenseAmount}</td>		                             
		                             
		                        	
	                                <td valign="top" class="name">
		                                <label for="invoiceDate"><g:message code="default.InvoiceDate.label"/></label>
		                            </td>
		                            <td><g:formatDate format="dd MMM yyyy" date="${expenseRequestEntryInstance.invoiceDate}"/></td>                                
	                                
	                                
		                        </tr>
		                        
		
								<tr class="prop">
			                         <td valign="top" class="name">
	                                    <label for="remarks"><g:message code="default.Remarks.label" /></label>
	                                </td>
	                                <td>${expenseRequestEntryInstance.remarks}</td>
	                                
	                                <td valign="top" class="name">
		                                <label for="invoiceAmount"><g:message code="default.InvoiceAmount.label" /></label>
		                            </td>
		                            <td>
	                                    ${expenseRequestEntryInstance.invoiceAmount}
	                                </td>
                                </tr>
                                <tr class="prop">
                                	<td> </td><td> </td>
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
		                       	 
                        	</tbody>
	                    </table>
                    </div>
                    <h1><g:message code="default.PaymentEntry.label" /></h1>
                    <g:form method="post" > 
                            <div class="dialog"> 
                               <table>
                                  <tbody>
                                  	 <g:hiddenField name="projects.id" value="${expenseRequestEntryInstance.projects.id}" /> 
                                  	 <g:hiddenField name="expenseRequestCode" value="${expenseRequestEntryInstance.expenseRequestCode}" />
                                     <tr class="prop"> 
                                         <td valign="top" class="name"> 
                                          <label for="dateOfExpense"><g:message code="default.DateOfPayment.label"/></label> 
                                          <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'dateOfExpense','errors')}"> 
                                            <calendar:datePicker name="dateOfExpense" defaultValue="${new Date()}" value="${grantExpenseInstance?.dateOfExpense}" dateFormat= "%d/%m/%Y"/> 
                                            
                                         </td>
                                     	
                                     	<td valign="top" class="name"> 
                                             <label for="expenseAmount"><g:message code="default.PaymentAmount.label"/></label> 
                                             <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'expenseAmount','errors')}"> 
                                             <input type="text" id="expenseAmount" name="expenseAmount" value="${amount}" style="text-align: right" />
                                 
                                         </td>
                                     </tr>
                                     
                                     <tr class="prop">
                                         <td valign="top" class="name">
                                             <label for="grantAllocation"><g:message code="default.GrantAllocation.label"/></label>
                                             <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(grantExpenseInstance,field:'grantAllocation','errors')}">
                                             <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${grantExpenseInstance?.grantAllocation?.id}" noSelection="['null':'-Select-']"></g:select>
                                         </td>
                                     	
                                     	 <td valign="top" class="name"> 
                                             <label for="grantAllocationSplit"><g:message code="default.AccountHeads.label"/></label> 
                                             <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'grantAllocationSplit','errors')}"> 
                                             <g:select optionKey="id" optionValue="accHeadPeriod" from="${accountHeadList}" noSelection="['null':'-Select-']" name="grantAllocationSplit.id" value="${grantExpenseInstance?.grantAllocationSplit?.id}" ></g:select> 
                                         </td>
                                     </tr>
                                                    
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                            <label for="modeOfPayment"><g:message code="default.ModeOfPayment.label"/></label>
                                            <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'modeOfPayment','errors')}">
                                            <g:select name="modeOfPayment" from="${['DD','Cheque','BankTransfer']}"  value="${fieldValue(bean:grantExpenseInstance,field:'modeOfPayment')}" noSelection="['null':'-Select-']"></g:select>
                                         </td>
                                         
                                         <td valign="top" class="name">
                                             <label for="bankName"><g:message code="default.BankName.label"/></label>
                                             <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'bankName','errors')}">
                                             <input type="text" id="bankName" name="bankName" value="${fieldValue(bean:grantExpenseInstance,field:'bankName')}" style="text-align: right" />
                                         </td>
                                    </tr>      
               
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                            <label for="ddNo"><g:message code="default.DD/ChequeNo.label"/></label>
                                            <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddNo','errors')}">
                                            <input type="text" id="ddNo" name="ddNo" value="${fieldValue(bean:grantExpenseInstance,field:'ddNo')}" style="text-align: right" />
                                         </td>
                                         
                                         <td valign="top" class="name">
                                             <label for="ddBranch"><g:message code="default.Branch.label"/></label>
                                             <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddBranch','errors')}">
                                             <input type="text" id="ddBranch" name="ddBranch" value="${fieldValue(bean:grantExpenseInstance,field:'ddBranch')}" style="text-align: right" />
                                         </td>
                                    </tr> 
                        
                                    <tr class="prop">
                                         <td valign="top" class="name">
                                             <label for="ddDate"><g:message code="default.DD/ChequeDate.label"/></label>
                                             <label for="symbol" style="color:red;font-weight:bold"> * </label>
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'ddDate','errors')}">
                                             <calendar:datePicker name="ddDate" defaultValue="${new Date()}" value="${grantExpenseInstance?.ddDate}" dateFormat= "%d/%m/%Y"/>
                                  		 </td>
                                  		 
                                  		 <td valign="top" class="name"> 
                                             <label for="description"><g:message code="default.Description.label"/></label> 
                                         </td>
                                         <td valign="top" class="value ${hasErrors(bean:grantExpenseInstance,field:'description','errors')}"> 
                                             <g:textArea name="description" value="${fieldValue(bean:grantExpenseInstance,field:'description')}" rows="3" cols="30"/> 
                                         </td>
                                    </tr> 
                            		
                                  </tbody>
                               </table>
                            </div>
                            			
	                            <div class="buttons"> 
	                              <span class="button"><g:actionSubmit class="save" action="saveExpense" value="${message(code: 'default.Save.button')}" onClick="return validateGrantExpense()" /></span>
	                            </div>
                           
                         </g:form>
                       
                          
		            <g:if test="${grantExpenseInstanceList}">
						<h1><g:message code="default.ListExpenses.ExpenseList.head"/></h1> 
				     		 <div class="list">
		                        <table>
		                        	<thead>
		                            	<tr> 
		                                     <th><g:message code="default.SINo.label"/></th>
		                                      <th><g:message code="default.DateOfPayment.label"/></th>
		                   	                 <th><g:message code="default.AccountHeads.label"/></th>
		                                     <th><g:message code="default.ExpenseAmount(Rs).label"/></th>
		                                     <th><g:message code="default.UploadReceipt.label"/></th>
		                                     <th><g:message code="default.Edit.label"/></th>
		                                  </tr>
		                                  
		                               </thead>
		                               <tbody>
		                                  <g:each in="${grantExpenseInstanceList}" status="i" var="grantExpenseInstance"> 
		                                     <tr class="${(i % 2) == 0 ? 'odd' : 'even'}"> 
		                                         <td>${i+1}</td>
		                                         <td><g:formatDate format="dd MMM yyyy" date="${grantExpenseInstance.dateOfExpense}"/></td>
		                                         <td>${fieldValue(bean:grantExpenseInstance, field:'grantAllocationSplit.accountHead.code')}</td>
		                                         <td>${grantExpenseInstance.expenseAmount}</td>
		                                         <td><g:link action="create"  controller='attachments' 
							                            id="${grantExpenseInstance.id}" params="[trackType:'grantExpense']">
							                            <g:message code="default.Upload.label"/>
						                            </g:link>
					                              </td>
					                              <td><g:link action="editexpense" id="${fieldValue(bean:grantExpenseInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
		                                       </tr>
		                                    </g:each> 
		                               </tbody>
		                             </table>
		                         </g:if>
		                         
		                     </div>
		                 </div>
	                 </div>  
     	</div>
    </body>
</html>