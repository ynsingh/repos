

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show GrantReceipt</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">GrantReceipt List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantReceipt</g:link></span>
        </div>
        <div class="body">
            <h1>Show GrantReceipt</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Grant Allocation:</td>
                            
                            <td valign="top" class="value"><g:link controller="grantAllocation" action="show" id="${grantReceiptInstance?.grantAllocation?.id}">${grantReceiptInstance?.grantAllocation?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'amount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Date Of Receipt:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'dateOfReceipt')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Grant Allocation Split:</td>
                            
                            <td valign="top" class="value"><g:link controller="grantAllocationSplit" action="show" id="${grantReceiptInstance?.grantAllocationSplit?.id}">${grantReceiptInstance?.grantAllocationSplit?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'modifiedBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Modified Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'modifiedDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created By:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'createdBy')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Created Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'createdDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantReceiptInstance, field:'description')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${grantReceiptInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
