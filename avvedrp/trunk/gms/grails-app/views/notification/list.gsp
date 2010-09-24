

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Notification.NotificationList.head"/></title>
    </head>
    <body>
      <g:subMenuNotification/>
        <div class="wrapper">
          <div class="body">
            <h1><g:message code="default.Notification.NotificationList.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${notificationInstanceList}">
              <div class="list">
                <table cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        <g:sortableColumn property="project.name" title="${message(code: 'default.Projects.label')}" />
                            <g:sortableColumn property="project.projectType.type" title="${message(code: 'default.ProjectType.label')}" />
                   	        <g:sortableColumn property="notificationCode" title="${message(code: 'default.NotificationCode.label')}" />
                   	        <g:sortableColumn property="notificationDate" title="${message(code: 'default.NotificationDate.label')}" />
                            <g:sortableColumn property="proposalSubmissionLastDate" title="${message(code: 'default.LastProposalSubmissionDate.label')}" />
                   	        <g:sortableColumn property="applicationForm" title="${message(code: 'default.ApplicationForm.label')}" />
                   	        <g:sortableColumn property="eligibilitydocument" title="${message(code: 'default.UploadAttachments.label')}"  />
                       		<th><g:message code="default.Publish.label"/></th>
                            <th><g:message code="default.Proposals.label"/></th>
                   	        <th><g:message code="default.Edit.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                      <% int j=0 %>
                      <g:each in="${notificationInstanceList}" status="i" var="notificationInstance">
                        <%  j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${j}</td>
                            <td>${fieldValue(bean:notificationInstance, field:'project.name')}</td>
                            <td>${fieldValue(bean:notificationInstance, field:'project.projectType.type')}</td>
                            <td>${fieldValue(bean:notificationInstance, field:'notificationCode')}</td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${notificationInstance.notificationDate}"/></td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${notificationInstance.proposalSubmissionLastDate}"/></td>
                            <td> <g:if test="${notificationInstance.applicationForm}"><g:link action="downloadApplicationForm" controller='notification' id="${fieldValue(bean:notificationInstance, field:'id')}" params="[documentType:'Notification']"><g:message code="default.View.label"/></g:link> </g:if></td>
                            <td><g:link action="create" controller='notificationsAttachments' id="${fieldValue(bean:notificationInstance, field:'id')}" params="[documentType:'Notification']"><g:message code="default.Attach.label"/></g:link></td>
                            <td><g:link action="publishNotification" controller='notification' id="${fieldValue(bean:notificationInstance, field:'id')}"><g:message code="default.Publish.label"/></g:link></td>
                            <td><g:link action="proposalList" controller='proposal' id="${fieldValue(bean:notificationInstance, field:'id')}"><g:message code="default.View.label"/></g:link></td>
                            <td><g:link action="edit" id="${fieldValue(bean:notificationInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
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
