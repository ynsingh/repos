

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expenseRequestEntry.label', default: 'ExpenseRequestEntry')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.grantAllocation.label" default="Grant Allocation" /></td>
                            
                            <td valign="top" class="value"><g:link controller="grantAllocation" action="show" id="${expenseRequestEntryInstance?.grantAllocation?.id}">${expenseRequestEntryInstance?.grantAllocation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.grantAllocationSplit.label" default="Grant Allocation Split" /></td>
                            
                            <td valign="top" class="value"><g:link controller="grantAllocationSplit" action="show" id="${expenseRequestEntryInstance?.grantAllocationSplit?.id}">${expenseRequestEntryInstance?.grantAllocationSplit?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.modifiedBy.label" default="Modified By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "modifiedBy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.modifiedDate.label" default="Modified Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${expenseRequestEntryInstance?.modifiedDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.createdBy.label" default="Created By" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "createdBy")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.createdDate.label" default="Created Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${expenseRequestEntryInstance?.createdDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.dateOfExpense.label" default="Date Of Expense" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${expenseRequestEntryInstance?.dateOfExpense}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.expenseAmount.label" default="Expense Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "expenseAmount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.expenseDescription.label" default="Expense Description" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "expenseDescription")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.expenseRequestCode.label" default="Expense Request Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "expenseRequestCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.investigator.label" default="Investigator" /></td>
                            
                            <td valign="top" class="value"><g:link controller="investigator" action="show" id="${expenseRequestEntryInstance?.investigator?.id}">${expenseRequestEntryInstance?.investigator?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.invoiceAmount.label" default="Invoice Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "invoiceAmount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.invoiceDate.label" default="Invoice Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${expenseRequestEntryInstance?.invoiceDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.invoiceNo.label" default="Invoice No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "invoiceNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.level.label" default="Level" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "level")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.projects.label" default="Projects" /></td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${expenseRequestEntryInstance?.projects?.id}">${expenseRequestEntryInstance?.projects?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.purchaseOrderDate.label" default="Purchase Order Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${expenseRequestEntryInstance?.purchaseOrderDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.purchaseOrderNo.label" default="Purchase Order No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "purchaseOrderNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.remarks.label" default="Remarks" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "remarks")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="expenseRequestEntry.status.label" default="Status" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: expenseRequestEntryInstance, field: "status")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${expenseRequestEntryInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
