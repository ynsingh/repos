<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Edit Employee Qualification</title>
        <script type="text/javascript">
		</script>
    </head>
    <body>
    	<div class="wrapper">
    	<g:subMenuList/> 
	    	<div class="body">
	            <h1><g:message code="default.ProjectEmployeeQualification.edit.head" /></h1> 
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${projectEmployeeQualificationInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${projectEmployeeQualificationInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${projectEmployeeQualificationInstance?.id}" />
	                <g:hiddenField name="version" value="${projectEmployeeQualificationInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="examname"><g:message code="default.ProjectEmployeeQualification.ExamName.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, field: 'examname', 'errors')}">
	                                    <g:textField style='width: 200px; ' name="examname" value="${projectEmployeeQualificationInstance?.examname}" />
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="passoutYear"><g:message code="default.ProjectEmployeeQualification.PassoutYear.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, field: 'passoutYear', 'errors')}">
	                                    <g:select name="passoutYear" from="${['2010', '2009', '2008','2007','2006','2005','2004','2003','2002','2001','2000']}" 
	                                    	value="${projectEmployeeQualificationInstance?.passoutYear}"/> 
	                                </td>
	                            </tr>
	                        
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="percMark"><g:message code="default.ProjectEmployeeQualification.PercentageofMark.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, field: 'percMark', 'errors')}">
	                                    <g:textField name="percMark" value="${fieldValue(bean: projectEmployeeQualificationInstance, field: 'percMark')}" />
	                                </td>
	                            </tr>
	                            
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="university"><g:message code="default.ProjectEmployeeQualification.University.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: projectEmployeeQualificationInstance, field: 'university', 'errors')}">
	                                    <g:textField style='width: 200px;' name="university" value="${projectEmployeeQualificationInstance?.university}" />
	                                </td>
	                            </tr>
	                    	</tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                	<input type="hidden" name="projectEmployee.id" id="projectEmployee.id" value="${projectEmployeeInstance.id}"/>
	                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" 
	                    	onClick="return validateProjectEmployeeQualification()"/>
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
