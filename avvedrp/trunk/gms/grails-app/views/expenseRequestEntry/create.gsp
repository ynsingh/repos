<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expenseRequestEntry.label', default: 'ExpenseRequestEntry')}" />
        <title><g:message code="default.ExpenseRequestEntry.label"/></title>
    </head>
    <body>
        <div class="wrapper"> 
	        <div class="body">
	            <h1><g:message code="default.ExpenseRequestEntry.label"/></h1>
	            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${expenseRequestEntryInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${expenseRequestEntryInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        	
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="projects"><g:message code="default.Projects.label" /></label>
	                                    <label for="symbol" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'projects', 'errors')}">
	                                    <g:select name="projects.id" from="${projectsList}" optionKey="id" optionValue="code" value="${expenseRequestEntryInstance?.projects?.id}" noSelection="['null':'-Select-']"/>
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="purchaseOrderNo"><g:message code="default.PurchaseOrderNo.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'purchaseOrderNo', 'errors')}">
	                                    <g:textField name="purchaseOrderNo" value="${expenseRequestEntryInstance?.purchaseOrderNo}"/>
	                                </td>
	                                
	                            </tr>
	                        
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="expenseDescription"><g:message code="default.ExpenseDescription.label" /></label>
	                                    <label for="symbol" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'expenseDescription', 'errors')}">
	                                    <g:textField name="expenseDescription" value="${expenseRequestEntryInstance?.expenseDescription}" />
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="purchaseOrderDate"><g:message code="default.PurchaseOrderDate.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'purchaseOrderDate', 'errors')}">
	                                    <calendar:datePicker name="purchaseOrderDate" defaultValue="${new Date()}" value="${expenseRequestEntryInstance?.purchaseOrderDate}" dateFormat= "%d/%m/%Y"/>
	                                   
	                                </td>
	                            </tr>
	                        	
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="dateOfExpense"><g:message code="default.ExpenseDate.label" /></label>
	                                    <label for="symbol" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'dateOfExpense', 'errors')}">
	                                    <calendar:datePicker name="dateOfExpense" defaultValue="${new Date()}" value="${expenseRequestEntryInstance?.dateOfExpense}" dateFormat= "%d/%m/%Y"/>
	                                    
	                                </td>
	                                <td valign="top" class="name">
	                                    <label for="invoiceNo"><g:message code="default.InvoiceNo.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'invoiceNo', 'errors')}">
	                                    <g:textField name="invoiceNo" value="${expenseRequestEntryInstance?.invoiceNo}" />
	                                </td>
	                                
	                            </tr>
	                        	
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="expenseAmount"><g:message code="default.ExpenseAmount(Rs).label" /></label>
	                                    <label for="symbol" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'expenseAmount', 'errors')}">
	                                    <g:textField name="expenseAmount" value="${amount}" />
	                                </td>
	                                
	                                
	                                <td valign="top" class="name">
	                                    <label for="invoiceDate"><g:message code="default.InvoiceDate.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'invoiceDate', 'errors')}">
	                                    <calendar:datePicker name="invoiceDate" defaultValue="${new Date()}" value="${expenseRequestEntryInstance?.invoiceDate}" dateFormat= "%d/%m/%Y"/>
	                                    
	                                </td>
	                            </tr>
	                        	
	                            <tr class="prop">
	                                 <td valign="top" class="name">
	                                    <label for="remarks"><g:message code="default.Remarks.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'remarks', 'errors')}">
	                                    <g:textArea name="remarks" value="${expenseRequestEntryInstance?.remarks}" rows="3" cols="30"/>
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                    <label for="invoiceAmount"><g:message code="default.InvoiceAmount.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'invoiceAmount', 'errors')}">
	                                    <g:textField name="invoiceAmount" value="${invoiceAmount}"/>
	                                </td>
	                            </tr>
	                        	
	                        	 <tr class="prop">
	                               
	                            </tr>
	                        
	                        	
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button"><g:actionSubmit value="${message(code: 'default.Submit.button')}" onClick="return validateExpenseRequestEntry()" action="save" />
	                </div>
	                
	            </g:form>
	        </div>
	        
	        <div class="body">  
	            <div class="list">
		            <g:if test="${expenseRequestEntryInstanceList}">
		                <table>
		                    <thead>
		                        <tr>
		                        
		                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
		                            
		                            <g:sortableColumn property="id" title="${message(code: 'default.Project.label')}" />
		                        	
		                        	<g:sortableColumn property="expenseDescription" title="${message(code: 'default.ExpenseDescription.label')}" />
		                        	
		                        	<g:sortableColumn property="expenseDescription" title="${message(code: 'default.RequestStatus.label')}" />
		                        	
		                        	<g:sortableColumn property="expenseDescription" title="${message(code: 'default.PaymentStatus.label')}" />
		                        	
		                            <g:sortableColumn property="dateOfExpense" title="${message(code: 'default.ExpenseDate.label')}" />
		                        
		                            <g:sortableColumn property="expenseAmount" title="${message(code: 'default.ExpenseAmount(Rs).label')}" />
		                        		                            
		                            <g:sortableColumn property="createdBy" title="${message(code: 'default.InvoiceNo.label')}" />
		                        
		                            <g:sortableColumn property="createdDate" title="${message(code: 'default.InvoiceAmount.label')}"/>
		                        
		                            <th><g:message code="default.UploadInvoice.label"/></th>
		                            
		                        	<th><g:message code="default.Edit.label"/> </th>
		                        </tr>
		                    </thead>
		                    <tbody>
			                    <g:each in="${expenseRequestEntryInstanceList}" status="i" var="expenseRequestEntryInstance">
			                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                        
			                            <td>${i+1}</td>
			                            
			                            <td>${expenseRequestEntryInstance.projects.code}</td>
			                        
			                        	<td>${fieldValue(bean: expenseRequestEntryInstance, field: "expenseDescription")}</td>
			                        	
			                        	<td>${fieldValue(bean: expenseRequestEntryInstance, field: "status")}</td>
			                        	
			                        	<td>${fieldValue(bean: expenseRequestEntryInstance, field: "paymentStatus")}</td>
			                        	
			                        	<td><g:formatDate format="dd/MM/yyyy" date="${expenseRequestEntryInstance.dateOfExpense}"/></td>
			                        	
			                            <td>${currencyFormat.ConvertToIndainRS(expenseRequestEntryInstance.expenseAmount)}</td>
			                        			                        
			                            <td>${fieldValue(bean: expenseRequestEntryInstance, field: "invoiceNo")}</td>
			                        
			                            <td>${currencyFormat.ConvertToIndainRS(expenseRequestEntryInstance.invoiceAmount)}</td>
			                           		                            
			                            <td>
				                            <g:link action="create"  controller='attachments' 
					                            id="${expenseRequestEntryInstance.id}" params="[trackType:'expenseRequestEntry']">
					                            <g:message code="default.Upload.label"/>
				                            </g:link>
			                        	</td>
			                        
				                        <td>
				                        	<g:link action="edit" id="${expenseRequestEntryInstance.id}">
				                     			<g:message code="default.Edit.label"/> 
				                     		</g:link>
				                 		</td>
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
