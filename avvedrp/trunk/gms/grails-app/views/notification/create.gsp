

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Notification.CreateNotification.head"/></title>     
	</head>
    <body>
     <g:subMenuNotification/>
     <div class="wrapper">
       <div class="body">
         <h1><g:message code="default.Notification.CreateNotification.head"/></h1>
         <g:if test="${flash.message}">
           <div class="message">${flash.message}</div>
         </g:if>
         <g:hasErrors bean="${notificationInstance}">
           <div class="errors">
             <g:renderErrors bean="${notificationInstance}" as="list" />
           </div>
         </g:hasErrors>
        <g:form  action="save"  method="post" enctype="multipart/form-data" controller="notification">  
          <div class="dialog">
            <table>
                <tbody>
                        
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="project"><g:message code="default.Projects.label"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'project','errors')}">
                            <g:select optionKey="id" optionValue="code" id="projects" from="${grantAllocationWithprojectsInstanceList.projects}"  name="project.id" value="${notificationInstance?.project?.id}" noSelection="['null':'select']"></g:select>                           
                        </td>
                    </tr> 
                        
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="notificationCode"><g:message code="default.NotificationCode.label"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'notificationCode','errors')}">
                            <input type="text" id="notificationCode" name="notificationCode" value="${fieldValue(bean:notificationInstance,field:'notificationCode')}"/>
                        </td>
                    </tr> 
                        
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="notificationDate"><g:message code="default.NotificationDate.label"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'notificationDate','errors')}">
                            <calendar:datePicker name="notificationDate" defaultValue="${new Date()}" value="${notificationInstance?.notificationDate}" dateFormat= "%d/%m/%Y"   />
                        </td>
                    </tr> 
                            
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="proposalSubmissionLastDate"><g:message code="default.LastProposalSubmissionDate.label"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'proposalSubmissionLastDate','errors')}">
                            <calendar:datePicker name="proposalSubmissionLastDate" defaultValue="${new Date()}" value="${notificationInstance?.proposalSubmissionLastDate}" dateFormat= "%d/%m/%Y"   />
                        </td>
                    </tr> 
                        
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="description"><g:message code="default.Description.label"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean:notificationInstance,field:'description','errors').encodeAsHTML()}">
                            <fckeditor:editor name="description" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                        </td>
                    </tr> 
                        	
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="applicationForm"><g:message code="default.ApplicationForm.label"/></label>
                        </td>
                        <td valign="top" class="value">
                            <input type="file" id="myFile" name="myFile"/><g:message code="default.ApplicationFormat.label"/> 
                        </td>
                    </tr> 
                            
                </tbody>
            </table>
          </div>
          <div class="buttons">
             <span class="button"><input class="save" type="submit"  onClick="return validateNotificationCreate()" value="${message(code: 'default.Create.button')}" /></span>
          </div>
        </g:form>
       </div>
     </div>
    </body>
</html>
