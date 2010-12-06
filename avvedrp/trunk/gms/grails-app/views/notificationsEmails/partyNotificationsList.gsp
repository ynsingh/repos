

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ProposalList.head"/></title>
    </head>
    <body>
    	<div class="wrapper"> 
          <div class="body">
            <h1><g:message code="default.Proposal.ProposalList.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${partyNotificationsInstance}">
              <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"  />
                            <th><g:message code="default.Projects.label"/></th>
                            <g:sortableColumn property="notificationDate" title="${message(code: 'default.NotificationDate.label')}" />
                            <g:sortableColumn property="proposalSubmissionLastDate" title="${message(code: 'default.LastProposalSubmissionDate.label')}" />
                   	        <th><g:message code="default.Grantor.label"/></th>
                   	        <th><g:message code="default.NotificationDetails.label"/></th>
                   	        <th><g:message code="default.UploadProposalDoc.label"/></th>
                   	        <th><g:message code="default.Status.label"/></th>
                   	        <th><g:message code="default.Apply.label"/></th>
                   	    </tr>
                    </thead>
                    <tbody>
                     <% int j=0 %>
                      <g:each in="${partyNotificationsInstance}" status="i" var="partyNotificationsInstance">
                        <% j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${j}</td>
                            <td>${fieldValue(bean:partyNotificationsInstance, field:'notification.project.name')}</td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${partyNotificationsInstance.notification.notificationDate}"/></td>
                           	<td><g:formatDate format="dd-MM-yyyy" date="${partyNotificationsInstance.notification.proposalSubmissionLastDate}"/></td>
                           	<td>${GrantAllocation.findByProjects(partyNotificationsInstance.notification.project).party.nameOfTheInstitution}</td>
                           	<td><g:link action="showPartyNotifications" controller="notificationsEmails" id="${partyNotificationsInstance.notification.id}"><g:message code="${message(code: 'default.View.label')}"/></g:link></td>
                           	<td><%def proposalStatus = Proposal.find("from Proposal where party.id="+session.Party+" and notification.id="+partyNotificationsInstance.notification.id)%>
                           	    <g:if test="${proposalStatus}">
                           	      <g:link action="create" controller='notificationsAttachments' id="${fieldValue(bean:proposalStatus, field:'id')}" params="[documentType:'Proposal']"><g:message code="default.Upload.label"/></g:link>
                           	    </g:if>
                           	    <g:else>
                           	      <g:message code="default.Upload.label"/>
                           	    </g:else>
                            </td>
                           	
                           	<td>
                           	 <g:if test="${proposalStatus?.lockedYN =='N'}">
                           	      <g:message code="default.Submitted.label"/>
                           	    </g:if>
                           	    <g:else>
                           	      <g:message code="default.NotSubmitted.label"/>
                           	    </g:else>
                           	</td>
							<td>
							    <g:form controller="proposal" action="save" id="${partyNotificationsInstance.notification.id}">  
                     			 <g:actionSubmit class="link" value="${message(code: 'default.Apply.button')}" action="save"/>  
                     			</g:form>
							</td>                        
                        </tr>
                      </g:each>
                    </tbody>
                </table>
              </div>
            </g:if>
            <g:else>
              <br><g:message code="default.NoRecordsAvailable.label"/></br>
            </g:else>
          </div>
       </div>
    </body>
</html>
