

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create GrantRequest</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="listGrantDetails">GrantDetails List</g:link></span>
            <span class="menuButton"><g:link class="list" action="listForGrant" id="${grantRequestInstance.grantDetails.id}">GrantRequest List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Grant Request</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantRequestInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantRequestInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        	<tr class="prop">
	                            <td valign="top" class="name">Code:</td>
	                            
	                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.code')}</td>
                            
	                        </tr>
	                        
	                    	<tr class="prop">
	                            <td valign="top" class="name">Title:</td>
	                            
	                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.title')}</td>
	                            
	                        </tr>
                        
                        	<tr class="prop">
	                            <td valign="top" class="name">Financial Year:</td>
	                            
	                            <td valign="top" class="value">${fieldValue(bean:grantRequestInstance, field:'grantDetails.financialYear')}</td>
                            
                        	</tr>
                        
                        	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantRequestInstance,field:'projects','errors')}">
                                	<g:select optionKey="id" optionValue="projectName" from="${Projects.findAllByProjectInvestigator(User.findById(session.UserId))}" name="projects.id" value="${grantRequestInstance?.projects?.id}" ></g:select>
                                    <g:hiddenField name="grantDetails.id" value="${grantRequestInstance?.grantDetails?.id}" />
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfRequest">Date Of Request:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantRequestInstance,field:'dateOfRequest','errors')}">
                                    <g:datePicker name="dateOfRequest" value="${grantRequestInstance?.dateOfRequest}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amountRequested">Amount Requested:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantRequestInstance,field:'amountRequested','errors')}">
                                    <input type="text" id="amountRequested" name="amountRequested" value="${fieldValue(bean:grantRequestInstance,field:'amountRequested')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantRequestInstance,field:'description','errors')}">
                                    <g:textArea name="description" value="${fieldValue(bean:grantRequestInstance,field:'description')}" rows="2" cols="20"/>
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
