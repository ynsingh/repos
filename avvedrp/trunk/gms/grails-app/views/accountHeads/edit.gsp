

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.AccountHeads.EditAccountHeads.head"/></title>         
    </head>
   
    <body>
      <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.AccountHeads.EditAccountHeads.head"/></h1>
            <g:if test="${flash.message}">
               <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${accountHeadsInstance}">
            <div class="errors">
               <g:renderErrors bean="${accountHeadsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${accountHeadsInstance?.id}" />
                <div class="dialog">
                   <table >
                        <tbody>
                        
                           <tr class="prop">
                              <g:if test="${accountHeadsInstance.parent !=null}">
                                <td valign="top" class="name">
                                    <label for="parent"><g:message code="default.MainAccount.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'parent','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${AccountHeads.findAllByActiveYesNoAndParentIsNull('Y')}" name="parent.id" value="${accountHeadsInstance?.parent?.id}" noSelection="['null':'-Select-']"></g:select>
                                </td>
                              </g:if>
                           </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:accountHeadsInstance,field:'name')}"/>
                                </td>
                           </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                     <label for="code"><g:message code="default.Code.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:accountHeadsInstance,field:'code')}"/>
                                </td>
                           </tr>                         
                        </tbody>
                   </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.Update.button')}" onClick="return validateAccountHead()" /></span>
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
