


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'checklist.label', default: 'Checklist')}" />
        <title><g:message code="default.editChecklist.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.editChecklist.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${checklistInstance}">
            <div class="errors">
                <g:renderErrors bean="${checklistInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${checklistInstance?.id}" />
                <g:hiddenField name="version" value="${checklistInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="field"><g:message code="default.Field.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: checklistInstance, field: 'field', 'errors')}">
                                    <g:textField name="field" value="${checklistInstance?.field}" />
                                </td>
                            </tr>
                           
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" onClick="return validateCheckLists()" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
     </div>
    </body>
</html>
