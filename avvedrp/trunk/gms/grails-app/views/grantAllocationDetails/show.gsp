

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show GrantAllocationDetails</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list" controller="grantAllocation">GrantAllocation List</g:link></span>
            <span class="menuButton"><g:link class="list" action="list">GrantAllocationDetails List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocationDetails</g:link></span>
        </div>
        <div class="body">
            <h1>Show Grant Allocation Details</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationDetailsInstance, field:'id')}</td>
                            
                        </tr>
                        
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
                            <td valign="top" class="name">Expense Head Master:</td>
                            
                            <td valign="top" class="value"><g:link controller="expenseHeadMaster" action="show" id="${grantAllocationDetailsInstance?.expenseHeadMaster?.id}">${grantAllocationDetailsInstance?.expenseHeadMaster?.expenseHeadName}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Expense Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationDetailsInstance, field:'expenseAmount')}</td>
                            
                        </tr>
                    
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${grantAllocationDetailsInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
