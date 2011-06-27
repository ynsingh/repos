

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Notification.NotificationList.head"/></title>
 		<g:javascript library="jquery"/>   
    </head>
    <body>
    <div id="paginate">
      <g:subMenuNotification/>
        <div class="wrapper">
          <div class="body">
            <h1><g:message code="default.Notification.NotificationList.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${notificationInstanceList}">
              <div class="list">
              <div class="paginateButtons" style="border: 0px;height:14px;text-align:right" ><util:remotePaginate controller="notification" action="list" total="${notificationInstanceListTotal}" update="paginate"  prev="Prev" pageSizes="[5,10,20,40,50]"/><font style="font:8pt verdana;font-weight:bold;color:black">Total:${notificationInstanceListTotal}</font></div>
              
                <table cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        <th><g:message code="default.NotificationTitle.label"/></th>
                            <g:sortableColumn property="notificationCode" title="${message(code: 'default.NotificationCode.label')}" />
                   	        <g:sortableColumn property="notificationDate" title="${message(code: 'default.NotificationDate.label')}" />
                            <g:sortableColumn property="proposalSubmissionLastDate" title="${message(code: 'default.LastProposalSubmissionDate.label')}" />
                   	        <g:sortableColumn property="eligibilitydocument" title="${message(code: 'default.UploadAttachments.label')}"  />
                   	        <th><g:message code="default.publicYesNo.label"/></th>
                       		<th><g:message code="default.Publish.label"/></th>
                            <th><g:message code="default.ProposalReceived.label"/></th>
                   	        <th><g:message code="default.AssignNotificationtoApprovalAuthority.label"/></th>
                   	        <th><g:message code="default.Edit.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                      <% int j=0 %>
                      <g:each in="${notificationInstanceList}" status="i" var="notificationInstance">
                        <%  j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${j}</td>
                            <td>
                            <g:link action="showPartyNotifications" controller="notificationsEmails" id="${notificationInstance.id}">${fieldValue(bean:notificationInstance, field:'notificationTitle')}</g:link>
                            </td>
                            <td>${fieldValue(bean:notificationInstance, field:'notificationCode')}</td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${notificationInstance?.notificationDate}"/></td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${notificationInstance?.proposalSubmissionLastDate}"/></td>
                           <td><g:link action="create" controller='notificationsAttachments' id="${fieldValue(bean:notificationInstance, field:'id')}" params="[documentType:'Notification']"><g:message code="default.Attach.label"/></g:link></td>
                            <g:if test="${notificationInstance?.publicYesNo=='Y'}">
                               <td>Yes</td>
                            </g:if>
            				<g:else>
            					<td>No</td>
        					</g:else>   
                            <g:if test="${notificationInstance?.publishYesNo=='N'}">
                            	<td><g:link action="publishNotification" controller='notification' id="${fieldValue(bean:notificationInstance, field:'id')}"><g:message code="default.Publish.label"/></g:link></td>
                            </g:if>
            				<g:else>
            					<td>Published</td>
        					</g:else>
                            <td><g:link action="proposalList" controller='proposal' id="${fieldValue(bean:notificationInstance, field:'id')}"><g:message code="default.View.label"/></g:link></td>
                            
                            <td><g:link action="notificationAuthorityMap" controller='notification' id="${fieldValue(bean:notificationInstance, field:'id')}"><g:message code="default.AssignNotification.label"/></g:link></td>
                            
                            <g:if test="${notificationInstance?.publishYesNo=='N'}">
                            	<td><g:link action="edit" id="${fieldValue(bean:notificationInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                            </g:if>
            				<g:else>
            					<td></td>
        					</g:else>
                            
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
