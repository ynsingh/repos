

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show GrantAllocation</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">GrantAllocation List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocation</g:link></span>
        </div>
        <div class="body">
            <h1>Show GrantAllocation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Account Heads:</td>
                            
                            <td valign="top" class="value"><g:link controller="accountHeads" action="show" id="${grantAllocationInstance?.accountHeads?.id}">${grantAllocationInstance?.accountHeads?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'createdDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'modifiedDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Amount Allocated:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'amountAllocated')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'code')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'createdBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Date Of Allocation:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'dateOfAllocation')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'modifiedBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value"><g:link controller="party" action="show" id="${grantAllocationInstance?.party?.id}">${grantAllocationInstance?.party?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Projects:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${grantAllocationInstance?.projects?.id}">${grantAllocationInstance?.projects?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Remarks:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantAllocationInstance, field:'remarks')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${grantAllocationInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
