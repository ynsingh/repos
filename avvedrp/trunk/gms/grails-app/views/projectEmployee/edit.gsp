<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Project Employee</title>
    	<script type="text/javascript">
		</script>
    </head>
    <body>
    	<div class="wrapper">
    	<g:subMenuList/> 
	        <div class="body">
	            <h1><g:message code="default.projectEmployees.edit.head"/></h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${projectEmployeeInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${projectEmployeeInstance}" as="list" />
	            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${projectEmployeeInstance?.id}" />
	                <g:hiddenField name="version" value="${projectEmployeeInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="empNo"><g:message code="default.projectEmployees.EmployeeNumber.label"/></label>:
	                                  <label for="EmployeeNumber" style="color:red;font-weight:bold"> * </label></td>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeInstance, field: 'empNo', 'errors')}">
	                                    <g:textField name="empNo" value="${projectEmployeeInstance?.empNo}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="empName"><g:message code="default.EmployeeName.label" />:</label>
	                                  <label for="empName" style="color:red;font-weight:bold"> * </label></td>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeInstance, field: 'empName', 'errors')}">
	                                    <g:textField name="empName" value="${projectEmployeeInstance?.empName}" />
	                                </td>
	                            </tr>
	                        
	                          	<tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="DOB"><g:message code="default.projectEmployees.DateOfBirth.label"/></label>:
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeInstance, field: 'DOB', 'errors')}">
	                                  <calendar:datePicker name="DOB"  value="${projectEmployeeInstance?.dOB}"  defaultValue="" 
	                                  	dateFormat= "%d/%m/%Y"/>
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="employeeDesignation"><g:message code="default.Designation.label"/></label>:
	                                  <label for="empName" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td><g:select name='employeeDesignation.id' optionKey="id" optionValue="Designation" 
	                                		from="${employeeDesignationInstanceList}" value="${projectEmployeeInstance?.employeeDesignation?.id}">
                                		</g:select></td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="joiningDate"><g:message code="default.JoiningDate.label"/></label>:
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeInstance, field: 'joiningDate', 'errors')}">
	                                    <calendar:datePicker name="joiningDate" value="${projectEmployeeInstance?.joiningDate}" defaultValue="" 
	                                    	dateFormat= "%d/%m/%Y"/>
	                                </td>
	                            </tr>
	                                                                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="relievingDate"><g:message code="default.RelievingDate.label"/></label>:
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeInstance, field: 'relievingDate', 'errors')}">
	                                    <calendar:datePicker name="relievingDate"  value="${projectEmployeeInstance?.relievingDate}"  defaultValue="" 
	                                    	dateFormat= "%d/%m/%Y"/>
	                                </td>
	                            </tr>	                            
	                     	</tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button" ><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" 
	                    	onClick="return validateProjectEmployee()"/>
						<span class="button">
							<g:actionSubmit class="delete"  action="delete" 
							value="${message(code: 'default.Delete.button')}" 
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						</span>
	                	</span>
	                </div>     
	            </g:form>
        	</div>
        </div>
    </body>
</html>
