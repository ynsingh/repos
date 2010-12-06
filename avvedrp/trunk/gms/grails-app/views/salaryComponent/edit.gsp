<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'salaryComponent.label', default: 'SalaryComponent')}" />
        <title><g:message code="default.EditSalaryComponent.edit.head" /></title>
    </head>
    <body>
		<div class="wrapper">
            <div class="body">
	           <h1>
	            <g:message code="default.EditSalaryComponent.edit.head" />
	           </h1>
	            <g:if test="${flash.message}">
	            	<div class="message">${flash.message}</div>
	            </g:if>
	            <g:hasErrors bean="${salaryComponentInstance}">
		            <div class="errors">
		                <g:renderErrors bean="${salaryComponentInstance}" as="list" />
		            </div>
	            </g:hasErrors>
	            <g:form method="post" >
	                <g:hiddenField name="id" value="${salaryComponentInstance?.id}" />
	                <g:hiddenField name="version" value="${salaryComponentInstance?.version}" />
	                <div class="dialog">
	                    <table>
	                        <tbody>
	                            <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="name"><g:message code="default.Name.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: salaryComponentInstance, field: 'name', 'errors')}">
	                                    <g:textField id="name" name="name" value="${salaryComponentInstance?.name}" />
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	                <div class="buttons">
	                    <span class="button">
	                    	<g:actionSubmit class="save" action="update" 
	                    	 value="${message(code: 'default.Update.button')}" onClick="return validateSalaryComponent()"/>
                    	</span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" 
	                    	 value="${message(code: 'default.Delete.button')}" 
	                    	 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    	</span>
                	</div>
	            </g:form>
            </div>
        </div>
    </body>
</html>
