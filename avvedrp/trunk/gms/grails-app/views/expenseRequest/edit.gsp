

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ExpenseRequest</title>
    </head>
    <body>
        <div class="body">
            <h1>Edit ExpenseRequest</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${expenseRequestInstance}">
            <div class="errors">
                <g:renderErrors bean="${expenseRequestInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${expenseRequestInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grantAllocation">Grant Allocation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseRequestInstance,field:'grantAllocation','errors')}">
                                     <g:select optionKey="id" optionValue="grantCode" from="${grantAllocationInstanceList}" name="grantAllocation.id" value="${expenseRequestInstance?.grantAllocation?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accountHead">Account Head:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseRequestInstance,field:'accountHead','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${AccountHeads.list()}" name="accountHead.id" value="${expenseRequestInstance?.accountHead?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="requestedAmount">Requested Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseRequestInstance,field:'requestedAmount','errors')}">
                                    <input type="text" id="requestedAmount" name="requestedAmount" value="${fieldValue(bean:expenseRequestInstance,field:'requestedAmount')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="requestedDate">Requested Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseRequestInstance,field:'requestedDate','errors')}">
                                    <calendar:datePicker name="requestedDate" defaultValue="${new Date()}" value="${expenseRequestInstance?.requestedDate}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fundAvailableYesNo">Fund Available:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseRequestInstance,field:'fundAvailableYesNo','errors')}">
                                    <g:select name="fundAvailableYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:expenseRequestInstance,field:'fundAvailableYesNo')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="Update" onClick="return validateExpenseRequest()"/></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
