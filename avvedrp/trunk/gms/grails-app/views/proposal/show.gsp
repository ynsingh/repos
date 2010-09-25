
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ShowProposal.head"/></title>
    </head>
    <body>
      <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.Proposal.ShowProposal.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                       <tr class="prop">
                          <td valign="top" class="name"><g:message code="default.ProposalCode.label"/></td>
                          <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'code')}</td>
                       </tr>
                        
                       <tr class="prop">
                          <td valign="top" class="name"><g:message code="default.Projects.label"/></td>
                          <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'notification.project.name')}</td>
                            
                       </tr>
                    
                       <tr class="prop">
                          <td valign="top" class="name"><g:message code="default.NotificationCode.label"/></td>
                          <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'notification.notificationCode')}</td>
                        </tr>
                    
                        <tr class="prop">
                          <td valign="top" class="name"><g:message code="default.Institution.label"/></td>
                          <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'party.code')}</td>
                        </tr>
                        
                        <tr class="prop">
                          <td valign="top" class="name"><g:message code="default.ProposalSubmittedDate.label"/></td>
                          <td valign="top" class="value"><g:formatDate format="dd-MM-yyyy" date="${proposalInstance.proposalSubmitteddate}"/></td>
                        </tr>
                        
                        <tr class="prop">
                          <td valign="top" class="name"><g:message code="default.Description.label"/></td>
                          <td valign="top" class="value">${proposalInstance.description}</td>
                        </tr>
                        
                        <tr class="prop">
           				  <td valign="top" class="name"><g:message code="default.Documentation.label"/></td>
           				  <td valign="top" class="value">
           				   <g:if test="${notificationsAttachmentsInstance}">
       						 <div class="list">
       						  <table>
       							<thead>
       							 <th><g:message code="default.DocumentType.label"/></th>
       							 <th><g:message code="default.View.label"/></th>
       							</thead>
       							<tbody>
       							<tr>
       							 <g:each in="${notificationsAttachmentsInstance}" status="j" var="notificationsAttachmentsInstance">
       							   <tr class="${(j % 2) == 0 ? 'odd' : 'even'}">
       							    <td>
       							      ${fieldValue(bean:notificationsAttachmentsInstance, field:'attachmentType.type')}
       							    </td>
       							    <td>
       							      <a href="${g.createLink(controller:'notificationsAttachments', action:'download', id:notificationsAttachmentsInstance.id)}"><g:message code="default.View.label" encodeAs="HTML" target="_blank"/></a>
       							    </td>
       							   </tr>
       							 </g:each>
       					        </tr>
       							</tbody>
       					      </table>
       						 </div>
                           </g:if>
                     	   <g:else>
     						 <g:message code="default.NoDocumentationAttached.label"/>
						   </g:else>
                     	  </td>
                     	</tr>
              			<tr class="prop">
                          <td valign="top" class="name"><g:message code="default.ApplicationForm.label"/></td>
                          <td><a href="${g.createLink(controller:'proposalApplication',action:'applicationForm')}"><g:message code="default.View.label"/></a></td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
        </div>
      </div>
    </body>
</html>
