

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.AttachmentType.CreateAttachmentType.head"/></title>         
    </head>
    <body>
      <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.AttachmentType.CreateAttachmentType.head"/></h1>
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
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr>
                                <td valign="top" class="name">
                                    <label for="documentType"><g:message code="default.DocumentType.label"/>:</label>
                                    <label for="documentType" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'documentType','errors')}">
                                    <g:select name="documentType" from="${['Proposal', 'Notification', 'Invoice', 'Receipt', 'Project']}" value="${attachmentTypeInstance?.documentType}" ></g:select>
                                </td>
                            </tr>
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
                                    <g:textArea id="description" name="description" value="${fieldValue(bean:attachmentTypeInstance,field:'description')}" rows="3" cols="30"/>
                                </td>
                            </tr>                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateAttachmentType();" /></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        
                   	        <th><g:message code="default.DocumentType.label"/></th>
                             
                   	        <g:sortableColumn property="type" title="${message(code: 'default.Type.label')}" />
                        
                   	        <g:sortableColumn property="description" title="${message(code: 'default.Description.label')}" />
                        
                   	        <th><g:message code="default.Edit.label"/></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                     <% int j=0 %>
                     <g:each in="${attachmentTypeInstanceList}" status="i" var="attachmentTypeInstance">
                        <%  j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${j}</td>
                            
                            <td>${fieldValue(bean:attachmentTypeInstance, field:'documentType')}</td>
                        
                            <td>${fieldValue(bean:attachmentTypeInstance, field:'type')}</td>
                        
                            <td>${fieldValue(bean:attachmentTypeInstance, field:'description')}</td>
                        
                            <td><g:link action="edit" id="${attachmentTypeInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        
                        </tr>
                     </g:each>
                    </tbody>
                </table>
            </div>
        </div>
      </div> 
    </body>
</html>
