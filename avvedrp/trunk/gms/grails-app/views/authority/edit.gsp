


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'authority.label', default: 'Authority')}" />
        <title><g:message code="default.Role.EditRole.head"/></title>
    </head>
    <body>
        <div class="wrapper">
          <div class="body">
            <h1><g:message code="default.Role.EditRole.head" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            
            <g:form method="post" >
                <g:hiddenField name="id" value="${authorityInstance?.id}" />
                <g:hiddenField name="version" value="${authorityInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="authority"><g:message code="default.Role.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: authorityInstance, field: 'authority', 'errors')}">
                                    <input type="text" id="authority" name="authority" style="width:250px"
                                 value="${authorityInstance.authority}" onBlur="return validAuthority(this);"/>
                                 <label for="role" style="color:blue;font-weight:bold"> <g:message code="default.Role.NameFormat.label"/></label> 
  						       </td>
                                <td valign="left">
                                   
                                </td>
                               
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="default.Description.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: authorityInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${authorityInstance?.description}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateAuthority();" /></span>                    

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
