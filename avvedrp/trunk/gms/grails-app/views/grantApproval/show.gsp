

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show GrantApproval</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="listGrantRequest">GrantRequest List</g:link></span>
            <span class="menuButton"><g:link class="list" action="listForGrantRequest" id="${grantApprovalInstance.grantRequest.id}">GrantApproval List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create" id="${session.GrantRequestID}">New GrantApproval</g:link></span>
        </div>
        <div class="body">
            <h1>Show GrantApproval</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'id')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Grant Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.grantMaster.code')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Grant Title:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.grantMaster.title')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Financial Year:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.financialYear')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Projects:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${grantApprovalInstance?.grantRequest?.projects?.id}">${grantApprovalInstance?.grantRequest?.projects?.projectName}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Amount Requested:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'grantRequest.amountRequested')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Approval Status:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'approvalStatus')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Approved Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'approvedAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Approved By:</td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${grantApprovalInstance?.approvedBy?.id}">${grantApprovalInstance?.approvedBy?.userRealName}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Approved Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'approvedDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantApprovalInstance, field:'description')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${grantApprovalInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
