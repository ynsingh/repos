<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.editEvalScale.head')}" />
        <title><g:message code="default.editEvalScale.head" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <div class="wrapper">
            	<h1 align="left"><g:message code="default.editEvalScale.head" args="[entityName]" /></h1>
		            <g:if test="${flash.message}">
		            	<div class="message">${flash.message}</div>
		            </g:if>
            <g:hasErrors bean="${evalScaleInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${evalScaleInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${evalScaleInstance?.id}" />
                <g:hiddenField name="version" value="${evalScaleInstance?.version}" />
                	<div class="dialog">
	                    <table>
	                        <tbody>
	                          <tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="scaleTitle"><g:message code="default.evalScale.scaleTitle.label"/>:</label>
	                                  <label for="scaleTitle" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalScaleInstance, field: 'scaleTitle', 'errors')}">
	                                    <g:textField name="scaleTitle" value="${evalScaleInstance?.scaleTitle}" />
	                                </td>
	                           </tr>
	                        </tbody>
	                    </table>
                  </div>
	              <div align="left" class="buttons">
	                <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateEvalScale();"/></span>
	                <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.Delete.button')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	              </div>
            </g:form>
        </div>
      </div>
    </body>
</html>
