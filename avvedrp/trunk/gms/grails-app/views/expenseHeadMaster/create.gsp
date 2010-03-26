

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ExpenseHeadMaster</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ExpenseHeadMaster List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ExpenseHeadMaster</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${expenseHeadMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${expenseHeadMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="expenseHeadName">Expense Head Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseHeadMasterInstance,field:'expenseHeadName','errors')}">
                                    <input type="text" id="expenseHeadName" name="expenseHeadName" value="${fieldValue(bean:expenseHeadMasterInstance,field:'expenseHeadName')}"/>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseHeadMasterInstance,field:'remarks','errors')}">
                                	<g:textArea name="remarks" value="${fieldValue(bean:expenseHeadMasterInstance,field:'remarks')}" rows="3" cols="30"/>
                                </td>
                            </tr>  
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo">Active Yes No:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:expenseHeadMasterInstance,field:'activeYesNo','errors')}">
                                	<g:select name="activeYesNo" from="${['Y', 'N']}" value="${fieldValue(bean:expenseHeadMasterInstance,field:'activeYesNo')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
