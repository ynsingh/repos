

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.AttachmentType.EditAttachmentType.head"/></title>         
    </head>
    <body>
      <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.AttachmentType.EditAttachmentType.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
				<div class="errors">${flash.error}</div>
			 </g:if>
            <g:hasErrors bean="${attachmentTypeInstance}">
              <div class="errors">
                <g:renderErrors bean="${attachmentTypeInstance}" as="list" />
              </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${attachmentTypeInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="default.Type.label"/>:</label>
                                    <label for="type" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attachmentTypeInstance,field:'type','errors')}">
                                    <input type="text" id="type" name="type" value="${fieldValue(bean:attachmentTypeInstance,field:'type')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.Description.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attachmentTypeInstance,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:attachmentTypeInstance,field:'description')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateAttachmentTypeEdit()" value="${message(code: 'default.Update.button')}"  /></span>
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
