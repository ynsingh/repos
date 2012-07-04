

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.AccountHeads.CreateSubAccountHeads.head"/></title>         
    </head>
    <body>     
      <div class="wrapper">
        <table class="tablewrapper">
          <tr>
            <td>
             
              
               <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
              <g:hasErrors bean="${accountHeadsInstance}">
                 <div class="errors">
                    <g:renderErrors bean="${accountHeadsInstance}" as="list" />
                 </div>
              </g:hasErrors>
           <div class="body">
            <h1><g:message code="default.AccountHeads.AccountHeadsList.head"/></h1>
           
            <g:if test="${accountHeadsInstanceList}">
              <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
                   	        <g:sortableColumn property="code" title="${message(code: 'default.Code.label')}" />
                   	        <th><g:message code="default.SubAccountHeads.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                     <% int j=0 %>
                     <g:each in="${accountHeadsInstanceList}" status="i" var="accountHeadsInstance">
                     <%  j++ %>
                     <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                           <td>${j}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'name')}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'code')}</td>
                           <td><g:link  action="showSubAccountHeads"  id="${accountHeadsInstance.id}"><g:message code="default.AddSubAccountHeads.label"/></g:link></td>
                     </tr>
                     </g:each>
                    </tbody>
                </table>
              </div>
            </g:if>
            <g:else>
              <br><g:message code="default.NoRecordsAvailable.label"/></br>
            </g:else>
               
		     </td>
          </tr>
        </table>
      </div>
    </body>
</html>
