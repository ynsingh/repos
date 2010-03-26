

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit GrantAllocationDetails</title>
    </head>
    <body>
         <div class="wrapper"> 
    
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list" controller="grantAllocation">GrantAllocation List</g:link></span>
            <span class="menuButton"><g:link class="list" action="list">GrantAllocationDetails List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocationDetails</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Grant Allocation Details</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationDetailsInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationDetailsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantAllocationDetailsInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                            <td valign="top" class="name">Project:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${grantAllocationDetailsInstance?.grantAllocation?.projectModules?.projects?.id}">${grantAllocationDetailsInstance?.grantAllocation?.projectModules?.projects?.projectName}</g:link></td>
                            
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name">Project Module:</td>
	                            
	                            <td valign="top" class="value"><g:link controller="projectModules" action="show" id="${grantAllocationDetailsInstance?.grantAllocation?.projectModules?.id}">${grantAllocationDetailsInstance?.grantAllocation?.projectModules?.projectName}</g:link></td>
	                            
	                        </tr>
	                        
	                        <tr class="prop">
	                            <td valign="top" class="name">Installment No:</td>
	                            
	                            <td valign="top" class="value">${fieldValue(bean:grantAllocationDetailsInstance, field:'grantAllocation.installmentNo')}</td>
	                            
	                        </tr>
	                        
	                         <tr class="prop">
	                            <td valign="top" class="name">Installment Amount:</td>
	                            
	                            <td valign="top" class="value">${fieldValue(bean:grantAllocationDetailsInstance, field:'grantAllocation.installmentAmount')}</td>
	                            
	                        </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="expenseHeadMaster">Expense Head Master:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationDetailsInstance,field:'expenseHeadMaster','errors')}">
                                    <g:select optionKey="id" optionValue="expenseHeadName" from="${ExpenseHeadMaster.list()}" name="expenseHeadMaster.id" value="${grantAllocationDetailsInstance?.expenseHeadMaster?.id}" ></g:select>
                                    <g:hiddenField name="grantAllocation.id" value="${grantAllocationDetailsInstance?.grantAllocation?.id}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="expenseAmount">Expense Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationDetailsInstance,field:'expenseAmount','errors')}">
                                    <input type="text" id="expenseAmount" name="expenseAmount" value="${fieldValue(bean:grantAllocationDetailsInstance,field:'expenseAmount')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
         </div>
    </body>
</html>
