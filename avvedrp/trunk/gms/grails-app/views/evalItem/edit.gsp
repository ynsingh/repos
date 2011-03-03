<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.editEvalItem.head')}" />
        <title><g:message code="default.editEvalItem.head" args="[entityName]" /></title>
    </head>
    <body>
      <div class="wrapper">
        <div class="body">
            <h1 align="left"><g:message code="default.editEvalItem.head" args="[entityName]" /></h1>
            	<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
            	</g:if>
            <g:hasErrors bean="${evalItemInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${evalItemInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${evalItemInstance?.id}" />
                <g:hiddenField name="version" value="${evalItemInstance?.version}" />
                	<div class="dialog">
                    	<table>
                        	<tbody>
                           		<tr class="prop">
	                                <td valign="top" class="name">
	                                    <label for="item"><g:message code="default.evalItem.item.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalItemInstance, field: 'item', 'errors')}">
	                                    <g:textField name="item" value="${evalItemInstance?.item}" />
	                                </td>
                           
	                                 <td valign="top" class="name">
	                                    <label for="notification"><g:message code="default.evalItem.notification.label" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalItemInstance, field: 'notification', 'errors')}">
	                                    <g:select optionKey="id" optionValue="notificationCode" from="${(notificationInstanceList)}"  name="notification.id" value="${fieldValue(bean: evalItemInstance, field: 'notification.id')}" noSelection="['null':'select']" />
	                                </td>
                              </tr>
                            
                              <tr class="prop">
                                
	                                <td valign="top" class="name">
	                                    <label for="evalScale"><g:message code="default.evalItem.evalScale.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: evalItemInstance, field: 'evalScale', 'errors')}">
	                                    <g:select optionKey="id" optionValue="scaleTitle" from="${(evalScaleInstanceList)}" name="evalScale.id" value="${fieldValue(bean: evalItemInstance, field: 'evalScale.id')}" noSelection="['null':'select']" ></g:select>
	                                </td>
                             </tr>
                      </tbody>
                    </table>
                </div>
	       <div align="left" class="buttons">
	           <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateEvalItem();"/></span>
	           <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.Delete.button')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	       </div>
      	</g:form>
   	</div>
  </body>
</html>
