

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ProposalList.head"/></title>
    	<g:javascript library="jquery"/>  
    </head>
    <body>
    	<div id="paginate">
    	<div class="wrapper"> 
          <div class="body">
            <h1><g:message code="default.Proposal.ProposalList.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${partyNotificationsInstance}">
              <div class="list">
              <div class="paginateButtons" style="border: 0px;height:14px;text-align:right" ><util:remotePaginate controller="notificationsEmails" action="partyNotificationsList" total="${partyNotificationsInstanceTotal}" update="paginate"  prev="Prev" pageSizes="[5,10,20,40,50]"/><font style="font:8pt verdana;font-weight:bold;color:black">Total:${partyNotificationsInstanceTotal}</font></div>
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"  />
                            <th><g:message code="default.NotificationTitle.label"/></th>
                            <g:sortableColumn property="notificationDate" title="${message(code: 'default.NotificationDate.label')}" />
                            <g:sortableColumn property="proposalSubmissionLastDate" title="${message(code: 'default.LastProposalSubmissionDate.label')}" />
                   	        <th><g:message code="default.Grantor.label"/></th>
                   	        <th><g:message code="default.NotificationDetails.label"/></th>
                   	        <th align="center"><img src="/gms/images/attach1.png"/></th>
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
                            <td>${fieldValue(bean:partyNotificationsInstance, field:'notificationTitle')}</td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${partyNotificationsInstance.notificationDate}"/></td>
                           	<td><g:formatDate format="dd-MM-yyyy" date="${partyNotificationsInstance.proposalSubmissionLastDate}"/></td>
                           	<g:hiddenField name="proposalSubmissionLastDate${i}" value="${partyNotificationsInstance.proposalSubmissionLastDate}" />
                           	<td>${fieldValue(bean:partyNotificationsInstance, field:'party.nameOfTheInstitution')}</td>
                           	<td><g:link action="showPartyNotifications" controller="notificationsEmails" id="${partyNotificationsInstance.id}"><g:message code="${message(code: 'default.View.label')}"/></g:link></td>
                           	<td><%def proposalStatus = Proposal.find("from Proposal where party.id="+session.Party+" and notification.id="+partyNotificationsInstance.id)%>
                           	    <g:if test="${proposalStatus}">
                           	      <g:link action="create" controller='notificationsAttachments' id="${fieldValue(bean:proposalStatus, field:'id')}" params="[documentType:'Proposal']"><img src="/gms/images/attach1.png"/></g:link>
                           	    </g:if>
                           	    <g:else>
                           	      <img src="/gms/images/attach1.png"/>
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
							    <g:form controller="proposal" action="save" id="${partyNotificationsInstance.id}">  
                     			
                     			<g:link action="uploadProposalApplication" controller="proposal" id="${partyNotificationsInstance.id}" onClick="return false;" ><g:message code="${message(code: 'default.Apply.label')}"/></g:link>  
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
       </div>
    </body>
</html>
