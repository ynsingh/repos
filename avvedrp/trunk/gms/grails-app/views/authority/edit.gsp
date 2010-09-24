


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
            <g:hasErrors bean="${authorityInstance}">
              <div class="errors">
                <g:renderErrors bean="${authorityInstance}" as="list" />
              </div>
            </g:hasErrors>
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
                                    <g:textField name="authority" value="${authorityInstance?.authority}" />
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
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="default.Active.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:authorityInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:authorityInstance,field:'activeYesNo')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateAuthority();" /></span>
                </div>
            </g:form>
          </div>
        </div>
    </body>
</html>
