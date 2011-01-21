

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.EditDepartment.head"/></title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.EditDepartment.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyDepartmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyDepartmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyDepartmentInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.DepartmentCode.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'departmentCode','errors')}">
                                    <input type="text" id="departmentCode" name="departmentCode" value="${fieldValue(bean:partyDepartmentInstance,field:'departmentCode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.DepartmentName.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:partyDepartmentInstance,field:'name')}"/>
                                </td>
                            </tr>                         
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Institution.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyDepartmentInstance,field:'party','errors')}">
                                    <strong>${fieldValue(bean:partyDepartmentInstance, field:'party.code')}</strong>
                                    <input type="hidden" id="party.id" name="party.id" value="${fieldValue(bean:partyDepartmentInstance,field:'party.id')}"/>
                                </td>
                            </tr> 

                        </tbody>
                    </table>
                </div>
                <div class="buttons">                
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateDepartment()" /></span>
                    
		      <!-- =================To place delete button =============--->
		      <span class="button">
			<g:actionSubmit class="delete"  action="delete" 
			value="${message(code: 'default.Delete.button')}" 
			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</span>
		     <!--======================================================--->
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
