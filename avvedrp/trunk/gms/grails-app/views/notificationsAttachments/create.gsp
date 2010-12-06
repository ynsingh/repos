<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.NotificationAttachments.CreateNotificationsAttachments.head"/></title>         
    </head>
    <body>
      <div class="wrapper">
      <g:subMenuNotification/>
        <div class="body">
            <h1><g:message code="default.NotificationAttachments.UploadAttachments.head"/></h1>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${notificationsAttachmentsInstance}">
                <div class="errors">
                <g:renderErrors bean="${notificationsAttachmentsInstance}" as="list" />
                </div>
            </g:hasErrors>
            <g:form action="save" method="post"  enctype="multipart/form-data" controller="notificationsAttachments">
            <div class="dialog">
                 <table>
                   <tbody> 
                       <tr class="prop">
                         <td>
                            <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:notificationsAttachmentsInstance, field:'notification.id')}"/> 
                            <input type="hidden" id="proposalId" name="proposalId" value="${fieldValue(bean:notificationsAttachmentsInstance, field:'proposal.id')}"/>                   
                            <input type="hidden" id="documentType" name="documentType" value="${params.documentType}"/> 
                         </td> 
                       </tr>
                            
                       <tr class="prop">
                         <td valign="top" class="name">
                             <label for="attachmentType"><g:message code="default.Type.label"/></label>
                         </td>
                         <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'attachmentType','errors')}">
                             <g:select optionKey="id" optionValue="type" from="${AttachmentType.findAllByDocumentTypeAndActiveYesNo(params.documentType,'Y')}" name="attachmentType.id" value="${notificationsAttachmentsInstance?.attachmentType?.id}"  ></g:select>
                         </td>                       
                       </tr> 
                        
                       <tr class="prop">
                         <td valign="top" class="name">
                             <label for="attachmentpath"><g:message code="default.AttachmentPath.label"/></label>
                         </td>
                         <td valign="top" class="value">
                             <input type="file" id="myfile" name="myfile" />
                         </td>
                       </tr> 
                    </tbody>
                  </table>
               </div>
               <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}"  /></span>
               </div>
             </g:form>

             <div class="body">
              <div class="list">
                <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
                   <thead>
                       <tr>
                          <th><g:message code="default.SINo.label"/></th>
                          <th><g:message code="default.Type.label"/></th>
                          <th><g:message code="default.AttachmentPath.label"/></th>
                          <th><g:message code="default.Delete.label"/></th>
                       </tr>
                   </thead>
                    
                   <tbody>
                     <% int j=0 %>
                     <g:each in="${notificationsAttachmentsInstanceList}" status="i" var="notificationsAttachmentsInstance">
                       <% j++ %>
                       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td>${j}</td>
                          <td>${fieldValue(bean:notificationsAttachmentsInstance, field:'attachmentType.type')}</td>
                          <td><a href="${g.createLink(controller:'notificationsAttachments', action:'download', id:notificationsAttachmentsInstance.id)}"><g:message code="${notificationsAttachmentsInstance.attachmentPath}" encodeAs="HTML"/></a></td>
                          <g:form>
                   			 <input type="hidden" name="id" value="${notificationsAttachmentsInstance?.id}" />
                   			 <input type="hidden"  name="notificationId" value="${notificationsAttachmentsInstance?.notification?.id}"/> 
                             <input type="hidden"  name="proposalId" value=""${notificationsAttachmentsInstance?.proposal?.id}"/>
                             <input type="hidden" id="documentType" name="documentType" value="${params.documentType}"/> 
                             <td> <g:actionSubmit class="delete" action="delete" onclick="return confirm('Are you sure?');" value="${message(code: 'default.Delete.button')}" /></td>
                         </g:form>
                       </tr>
                     </g:each>            
                   </tbody>
                </table>
              </div>
             </div> 
        </div>
      </div> 
    </body>
</html>
