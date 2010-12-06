<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProjectType.EditProjectType.head"/></title>         
    </head>
    <body>
      <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.ProjectType.EditProjectType.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectTypeInstance}">
              <div class="errors">
                <g:renderErrors bean="${projectTypeInstance}" as="list" />
              </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${projectTypeInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="default.Type.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectTypeInstance,field:'type','errors')}">
                                    <input type="text" id="type" name="type" value="${fieldValue(bean:projectTypeInstance,field:'type')}"/>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}"  onClick="return validateProjectType();" /></span>
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
