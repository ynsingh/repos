

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show GrantRequest</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="listGrantDetails">GrantDetails List</g:link></span>
            <span class="menuButton"><g:link class="list" action="listForGrant" id="${grantRequestInstance.grantDetails.id}">GrantRequest List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create" id="${grantRequestInstance.grantDetails.id}">New GrantRequest</g:link></span>
        </div>
        <div class="body">
            <h1>Show Grant Request</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'id')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Grant Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.code')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Grant Title:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.title')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Financial Year:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'grantDetails.financialYear')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Projects:</td>
                            
                            <td valign="top" class="value"><g:link controller="projects" action="show" id="${grantRequestInstance?.projects?.id}">${grantRequestInstance?.projects?.projectName}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Date Of Request:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'dateOfRequest')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Amount Requested:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'amountRequested')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'description')}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Request Status:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'requestStatus')}</td>
                            
                        </tr>
                        
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${grantRequestInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
