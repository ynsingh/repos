<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Employee Salary Details</title>
        <script type="text/javascript">
		</script>
    </head>
    <body>
    	<div class="wrapper">
	        <div class="body">
	            <h1><g:message code="default.EditEmployeeSalaryDetails.edit.head"/></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${projectEmployeeSalaryDetailsInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${projectEmployeeSalaryDetailsInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${projectEmployeeSalaryDetailsInstance?.id}" />
	                <g:hiddenField name="version" value="${projectEmployeeSalaryDetailsInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
		                        <tr class="prop">
		                                <td valign="top" class="name">
		                                  <label for="salaryComponentId">
		                                  	 <g:message code="default.ProjectEmployeeSalary.SalaryComponentId.label" />
	                                  	  </label>
		                                </td>
		                                <td><g:select name='salaryComponent.id' optionKey="id" optionValue="Name" 
		                                		from="${salaryComponentInstance}" 
		                                		value="${projectEmployeeSalaryDetailsInstance?.salaryComponent?.id}">
		                                	</g:select>
	                                	</td>
		                            </tr>
		                            
		                            <tr class="prop">
		                                <td valign="top" class="name">
		                                  <label for="salaryAmount"><g:message code="default.ProjectEmployeeSalary.SalaryAmount.label" />  
		                                  		(<g:message code="default.Rs.label" />)
		                                  </label>
		                                </td>
		                                <td valign="top" 
		                                	class="value ${hasErrors(bean: projectEmployeeSalaryDetailsInstance, field:'salaryAmount','errors')}">
		                                    <g:textField name="salaryAmount" 
		                                    	value="${projectEmployeeSalaryDetailsInstance.salaryAmount}" />
		                                </td>
		                             </tr>
		                        	                            	                        
		                            <tr class="prop">
		                                <td valign="top" class="name">
		                                  <label for="withEffectFrom"><g:message code="default.ProjectEmployeeSalary.WithEffectFrom.label" />
		                                  </label>
		                                 </td>
		                                <td valign="top" 
		                                	class="value ${hasErrors(bean: projectEmployeeSalaryDetailsInstance, field:'withEffectFrom','errors')}">
		                                    <calendar:datePicker name="withEffectFrom" precision="day" 
		                                    	value="${projectEmployeeSalaryDetailsInstance?.withEffectFrom}"/>
		                                </td>
		                              </tr>
		                             
		                             <tr class="prop">
		                                <td valign="top" class="name">
		                                  <label for="status"><g:message code="default.Active.label" /></label>
		                                </td>
		                               	<td> <g:select name="status" from="${['Y','N']}" 
		                               		value="${projectEmployeeSalaryDetailsInstance?.status}"/>
	                               		</td>
	                            	 </tr>
	                       		 </tbody>
	                    	</table> 
	                    </div>
		                <div class="buttons">
	                		<input type="hidden" name="projectEmployee.id" id="projectEmployee.id" value="${projectEmployeeInstance.id}"/>
	                		<span class="button"><g:actionSubmit class="save" action="update" 
	                			onClick="return validateProjectEmployeeSalaryDetails()" 
	                    		value="${message(code: 'default.Update.button')}" />
	                		</span>
	               		</div>              
	            	</g:form>
	        </div>
        </div>
    </body>
</html>
