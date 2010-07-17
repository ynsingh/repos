

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ExpenseRequest</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ExpenseRequest List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ExpenseRequest</g:link></span>
        </div>
        <div class="body">
            <h1>Show ExpenseRequest</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:expenseRequestInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Grant Allocation:</td>
                            
                            <td valign="top" class="value"><g:link controller="grantAllocation" action="show" id="${expenseRequestInstance?.grantAllocation?.id}">${expenseRequestInstance?.grantAllocation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Account Head:</td>
                            
                            <td valign="top" class="value"><g:link controller="accountHeads" action="show" id="${expenseRequestInstance?.accountHead?.id}">${expenseRequestInstance?.accountHead?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Requested Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:expenseRequestInstance, field:'requestedAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Requested Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:expenseRequestInstance, field:'requestedDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Fund Available:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:expenseRequestInstance, field:'fundAvailableYesNo')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${expenseRequestInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
