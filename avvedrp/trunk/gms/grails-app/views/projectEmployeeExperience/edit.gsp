<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Employee Experiance</title>
        <script type="text/javascript">
	    </script>
    </head>
    <body>
    	<div class="wrapper">
	    	<div class="body">
	            <h1><g:message code="default.projectEmployeeExperience.edit.head" /></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${projectEmployeeExperienceInstance}">
		        	<div class="errors">
		                <g:renderErrors bean="${projectEmployeeExperienceInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${projectEmployeeExperienceInstance?.id}" />
	                <g:hiddenField name="version" value="${projectEmployeeExperienceInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        
	                             <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="organizationName">
	                                  	<g:message code="default.projectEmployeeExperience.OrganizationName.label"/>
	                                  </label>
	                                </td>
	                                <td valign="top" 
	                                	class="value ${hasErrors(bean: projectEmployeeExperienceInstance, field: 'organizationName','errors')}">
	                                    <g:textField name="organizationName" value="${projectEmployeeExperienceInstance?.organizationName}" />
	                                </td>
	                            </tr>
	                            
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="designation">
	                                  	<g:message code="default.Designation.label" /></label>
	                                </td>
	                                <td valign="top" 
	                                	class="value ${hasErrors(bean: projectEmployeeExperienceInstance, field: 'designation', 'errors')}">
	                                    <g:textField name="designation" value="${projectEmployeeExperienceInstance?.designation}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="joiningDate">
	                                  	<g:message code="default.JoiningDate.label" /></label>
	                                </td>
	                                <td valign="top" 
	                                	class="value ${hasErrors(bean: projectEmployeeExperienceInstance, field: 'joiningDate', 'errors')}">
	                                   <calendar:datePicker name="joiningDate"  
	                                   		value="${projectEmployeeExperienceInstance?.joiningDate}" defaultValue=""/></td>
	                            </tr>
	                                                                   
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="relievingDate"><g:message code="default.RelievingDate.label" /></label>
	                                </td>
	                                <td valign="top" 
	                                	class="value ${hasErrors(bean: projectEmployeeExperienceInstance, field: 'relievingDate', 'errors')}">
	                                    <calendar:datePicker name="relievingDate" precision="day" 
	                                    	value="${projectEmployeeExperienceInstance?.relievingDate}"/>
	                                </td>
	                            </tr>
	                    		<tr class="prop">
	                                <td valign="top" class="name"> 
	                        	</tr> 
	                		</tbody>
	                    </table> 
	        		</div>
	                <div class="buttons">
	                                <input type="hidden" name="projectEmployee.id" id="projectEmployee.id" value="${projectEmployeeInstance.id}"/>
	                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateProjectEmployeeExperience()" 
	                    	value="${message(code: 'default.Update.button')}" />
	                	</span>
						<span class="button">
							<g:actionSubmit class="delete"  action="delete" 
							value="${message(code: 'default.Delete.button')}" 
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</span>
	                </div>
	            </g:form>
	        </div>
        </div>
    </body>
</html>
