

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create NotificationsAttachments</title>         
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
     		
        </div>
        
  <tr>
    <td scope="col" > 
        <div class="body">
            <h1>Upload Attachments</h1>
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
                                    <label for="attachmentType">Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:notificationsAttachmentsInstance,field:'attachmentType','errors')}">
                                   
                                 <g:select optionKey="id" optionValue="type" from="${AttachmentType.findAllByDocumentType(params.documentType)}" name="attachmentType.id" value="${notificationsAttachmentsInstance?.attachmentType?.id}"  ></g:select>
                                </td>
                                
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attachmentpath">Attachment path:</label>
                                </td>
                                 <td valign="top" class="value">
                               <input type="file" id="myfile" name="myfile" />
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                   
                </div>
               
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                    </div>
                    </g:form>
        </td>

        <tr>
      
   
       <td scope="row"> <div class="list">
          
            <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Slno" />
                        
                   	        
                   	    
                   	        <th>Type</th>
                   	    
                   	        <g:sortableColumn property="attachmentPath" title="Attachment Path" />
                        
                   	           <th>Delete</th>
                        
                        </tr>
                    </thead>
                    
                    <tbody>
                    <g:each in="${notificationsAttachmentsInstanceList}" status="i" var="notificationsAttachmentsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                         <td>${(i + 1)}</td>
                          
                            <td>${fieldValue(bean:notificationsAttachmentsInstance, field:'attachmentType.type')}</td>
                        
                            <td><a href="${g.createLink(controller:'notificationsAttachments', action:'download', id:notificationsAttachmentsInstance.id)}"><g:message code="${notificationsAttachmentsInstance.attachmentPath}" encodeAs="HTML"/></a></td>
                        
                           <g:form>
                   				 <input type="hidden" name="id" value="${notificationsAttachmentsInstance?.id}" />
                   				   <input type="hidden"  name="notificationId" value="${notificationsAttachmentsInstance?.notification?.id}"/> 
                                 <input type="hidden"  name="proposalId" value=""${notificationsAttachmentsInstance?.proposal?.id}"/>
                                 <input type="hidden" id="documentType" name="documentType" value="${params.documentType}"/> 
                                 <td> <g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></td>
                         </g:form>
                        </tr>
                    </g:each>            
                    </tbody>
                </table>
     </div>
     </div> 
	</td>
  </tr>
   
 
    </body>
</html>
