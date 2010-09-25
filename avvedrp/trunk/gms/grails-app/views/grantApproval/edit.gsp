

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit GrantApproval</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="listGrantRequest">GrantRequest List</g:link></span>
            <span class="menuButton"><g:link class="list" action="listForGrantRequest" id="${grantApprovalInstance.grantRequest.id}">GrantApproval List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create" id="${session.GrantRequestID}">New GrantApproval</g:link></span>
        </div>
        <div class="body">
            <h1>Edit GrantApproval</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantApprovalInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantApprovalInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantApprovalInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
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
                                <td valign="top" class="name">
                                    <label for="approvalStatus">Approval Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantApprovalInstance,field:'approvalStatus','errors')}">
                                    <input type="text" id="approvalStatus" name="approvalStatus" value="${fieldValue(bean:grantApprovalInstance,field:'approvalStatus')}"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvedDate">Approved Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantApprovalInstance,field:'approvedDate','errors')}">
                                    <g:datePicker name="approvedDate" value="${grantApprovalInstance?.approvedDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvedAmount">Approved Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantApprovalInstance,field:'approvedAmount','errors')}">
                                    <input type="text" id="approvedAmount" name="approvedAmount" value="${fieldValue(bean:grantApprovalInstance,field:'approvedAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantApprovalInstance,field:'description','errors')}">
                                    <g:textArea name="description" value="${fieldValue(bean:grantApprovalInstance,field:'description')}" rows="2" cols="20"/>
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
    </body>
</html>
