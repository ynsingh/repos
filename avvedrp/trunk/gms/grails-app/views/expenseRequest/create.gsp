

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ExpenseRequest</title>         
    </head>
    <body>
    <div class="wrapper">
		<g:subMenuList/>
        <div class="body">
            <h1>Create ExpenseRequest</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${expenseRequestInstance}">
            <div class="errors">
                <g:renderErrors bean="${expenseRequestInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                                    <g:select optionKey="id" optionValue="accHeadPeriod" from="${accountHeadList}"  name="accountHead.id" value="${expenseRequestInstance?.accountHead?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="requestedAmount">Requested Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseRequestInstance,field:'requestedAmount','errors')}">
                                    <input type="text" id="requestedAmount" name="requestedAmount" value="${fieldValue(bean:expenseRequestInstance,field:'requestedAmount')}" style="text-align: right"/>
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
                        
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Request" onClick="return validateExpenseRequest()"/></span>
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
