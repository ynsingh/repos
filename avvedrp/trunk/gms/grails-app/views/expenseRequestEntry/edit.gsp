


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expenseRequestEntry.label', default: 'ExpenseRequestEntry')}" />
        <title><g:message code="default.EditExpenseRequestEntry.label"/></title>
    </head>
    <body>
    	<div class="wrapper">
	        <div class="body">
	            <h1><g:message code="default.EditExpenseRequestEntry.label"/></h1>
	            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${expenseRequestEntryInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${expenseRequestEntryInstance}" as="list" />
	            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${expenseRequestEntryInstance?.id}" />
	                <g:hiddenField name="version" value="${expenseRequestEntryInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="projects"><g:message code="default.Projects.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'projects', 'errors')}">
	                                    <g:select name="projects.id" from="${projectsList}" optionKey="id" optionValue="code" value="${expenseRequestEntryInstance?.projects?.id}"  />
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                  <label for="purchaseOrderNo"><g:message code="default.PurchaseOrderNo.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'purchaseOrderNo', 'errors')}">
	                                    <g:textField name="purchaseOrderNo" value="${expenseRequestEntryInstance?.purchaseOrderNo}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="expenseDescription"><g:message code="default.ExpenseDescription.label" /></label>
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
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: expenseRequestEntryInstance, field: 'expenseAmount', 'errors')}">
	                                    <g:textField name="expenseAmount" value="${expenseAmount}" />
	                                </td>
	                                
	                                <td valign="top" class="name">
	                                  <label for="invoiceDate"><g:message code="default.InvoiceDate.label" /></label>
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
	                                    <g:textField name="invoiceAmount" value="${invoiceAmount}" />
	                                </td>
	                            </tr>
	                        
	                        </tbody>
	                    </table>
	                </div>
	                <g:if test="${!proposalApprovalAuthorityMapInstanceList}">
	                <div class="buttons">
	                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" onClick="return validateExpenseRequestEntry()"/></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </div>
	                </g:if>
	                <g:else>
	                	<label for="AlreadySentForApproval"><g:message code="default.AlreadySentForApproval.message"/></label>
	                </g:else>
	            </g:form>
	        </div>
        </div>
    </body>
</html>
