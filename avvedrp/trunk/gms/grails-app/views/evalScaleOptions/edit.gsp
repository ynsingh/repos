<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.editEvalScaleOptions.head')}" />
        <title><g:message code="default.editEvalScaleOptions.head" args="[entityName]" /></title>
    </head>
    <body>
      <div class="wrapper">  
        <div class="body">
            <h1 align="left"><g:message code="default.editEvalScaleOptions.head" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${evalScaleOptionsInstance}">
            <div class="errors">
                <g:renderErrors bean="${evalScaleOptionsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${evalScaleOptionsInstance?.id}" />
                <g:hiddenField name="version" value="${evalScaleOptionsInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="evalScale"><g:message code="default.evalScaleOptions.evalScale.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evalScaleOptionsInstance, field: 'evalScale', 'errors')}">
                                    ${fieldValue(bean: evalScaleOptionsInstance, field: 'evalScale.scaleTitle')}
                                    <input type=hidden name="evalScale.id" id = "evalScale.id" value="${evalScaleOptionsInstance.evalScale.id}">
                                </td>
                                
                                <td valign="top" class="name">
                                    <label for="scaleOption"><g:message code="default.evalScaleOptions.scaleOption.label"/>:</label>
                                    <label for="scaleOption" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evalScaleOptionsInstance, field: 'scaleOption', 'errors')}">
                                    <g:textField name="scaleOption" value="${evalScaleOptionsInstance?.scaleOption}" />
                                </td>
                            </tr>
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="scaleOptionIndex"><g:message code="default.evalScaleOptions.scaleOptionIndex.label"/>:</label>
                                    <label for="scaleOptionIndex" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: evalScaleOptionsInstance, field: 'scaleOptionIndex', 'errors')}">
                                    <g:textField name="scaleOptionIndex" value="${fieldValue(bean: evalScaleOptionsInstance, field: 'scaleOptionIndex')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div align="left" class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateEvalScaleOptions();"/></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.Delete.button')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
         </div>
       </div> 
    </body>
</html>
