

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Notification.PublishNotification.head"/></title>
    </head>
    <body>
       <g:javascript library="application"/>
       <modalbox:modalIncludes/>
       <div class="wrapper">
        <div class="tablewrapper">
          <div class="body">
            <h1><g:message code="default.Notification.PublishNotification.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
              <table>
                  <tbody>
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.Projects.label"/></td>
                        <td valign="top" style="font-size:100%" class="value"><b>${notificationsInstance?.project?.name.encodeAsHTML()}</b></td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.DateOfNotification.label"/></td>
                        <td><g:formatDate format="dd-MM-yyyy" date="${notificationsInstance.notificationDate}"/></td>
                    </tr>
                        
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.LastProposalSubmissionDate.label"/></td>
                        <td><g:formatDate format="dd-MM-yyyy" date="${notificationsInstance.proposalSubmissionLastDate}"/></td>
                    </tr>
                    
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.Description.label"/></td>
                        <td valign="top" class="value">${notificationsInstance.description}</td>
                    </tr>
                    
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.Type.label"/></td>
                        <td valign="top" class="value">${fieldValue(bean:notificationsInstance, field:'project.projectType.type')}</td>
                    </tr>
                          
                    <tr class="prop">
           				<td valign="top" class="name"><g:message code="default.Documentation.label"/></td>
           	            <td valign="top" class="value">
           				<g:if test="${notificationsAttachmentsInstance}">
       					  <div class="list">
       						<table >
       						  <thead>
       							<th><g:message code="default.DocumentType.label"/></th>
       							<th><g:message code="default.View.label"/></th>
       						  </thead>
       						  <tbody>
       						    <tr>
       							 <g:each in="${notificationsAttachmentsInstance}" status="j" var="notificationsAttachmentsInstance">
       							  <g:if test="${notificationsAttachmentsInstance.attachmentType.documentType=='Notification'}">
       							   <tr class="${(j % 2) == 0 ? 'odd' : 'even'}">
       						         <td>
       							       ${fieldValue(bean:notificationsAttachmentsInstance, field:'attachmentType.type')}
       							     </td>
       							     <td>
       							       <a href="${g.createLink(controller:'notificationsAttachments', action:'download', id:notificationsAttachmentsInstance.id)}"><g:message code="default.View.label" encodeAs="HTML" target="_blank"/></a>
       							     </td>
       							   </tr>
       							  </g:if>
       							 </g:each>
       			                </tr>
       						  </tbody>
       					    </table>
       					  </div>
       					</g:if>
       					<g:else>
     					  <g:message code="default.NoDocumentationAttached.label"/>
						</g:else>
                    </tr>
                  </tbody>
              </table>
            </div>
            <g:if test="${!notificationsEmailsInstance}">
              <div class="singlebutton">
                <g:form controller="notificationsEmails" action="publish" id="${fieldValue(bean:notificationsInstance, field:'id')}">  
                  <span class="button"><g:actionSubmit class="inputbutton" value="${message(code: 'default.Publish.button')}" action="publish"/> </span> 
                </g:form> 
              </div>
            </g:if>
            <g:else>
              <%--<div class="message">Proposal Submited for this Notification</div>
              <h1 style="color:33CCFF">Proposal Submited for this Notification</h1>
              --%>
              <g:message code="default.NotificationPublished.label"/>
            </g:else>
          </div>
        </div>
       </div>
    </body>
</html>
