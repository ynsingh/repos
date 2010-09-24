

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ProposalList.head"/></title>
    </head>
    <body>
      <div class="wrapper">
        <div class="tablewrapper">
          <div class="body">
            <h1><g:message code="default.Proposal.ProposalList.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                          <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:proposalInstance, field:'notification.id')}"/>
                   	      <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	      <th><g:message code="default.Institution.label"/></th>
                          <th><g:message code="default.ViewProposal.label"/></th>
                   	    </tr>
                    </thead>
                    <tbody>
                      <% int j=0 %>
                      <g:each in="${proposalInstanceList}" status="i" var="proposalInstance">
                        <% j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <td>${j}</td>
                          <td>${fieldValue(bean:proposalInstance, field:'party.code')}</td>
                          <td><g:link action="show" id="${proposalInstance.id}"><g:message code="default.View.label"/></g:link></td>
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
