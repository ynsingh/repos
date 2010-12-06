

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
              <div class="body">
              <h1><g:message code="default.AccountHeads.CreateSubAccountHeads.head"/></h1>
              <g:hasErrors bean="${accountHeadsInstance}">
                 <div class="errors">
                    <g:renderErrors bean="${accountHeadsInstance}" as="list" />
                 </div>
              </g:hasErrors>
              <g:form action="save" method="post" >
                <div class="dialog">
                    <table width="950" cellspacing="4" cellpading="2">
                        <tbody>
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parent"><g:message code="default.MainAccount.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'parent','errors')}">
                                     ${fieldValue(bean:accountHeadsInstance,field:'parent.name')}
                                </td>
                                <td valign="top" class="name">
                                    <label for="parent"><g:message code="default.MainAccountCode.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'parent','errors')}">
                                    ${fieldValue(bean:accountHeadsInstance,field:'parent.code')}
                                    <g:hiddenField id="parentid" name="parent.id" value="${fieldValue(bean:accountHeadsInstance, field:'parent.id')}"/>
                                </td>
                                <td>
                                </td>
                                <td>
                                </td>
                              
                             </tr> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:accountHeadsInstance,field:'name')}"/>
                                </td>
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Code.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accountHeadsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:accountHeadsInstance,field:'code')}"/>
                                </td>
                                <td>
                                </td>
                                <td>
                                <td>
                                </td>
                             </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateAccountHead()" /></span>
                </div>
              </g:form>
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <div class="body">
               <h1><g:message code="default.AccountHeads.SubAccountHeadsList.head"/>   ${fieldValue(bean:accountHeadsInstance,field:'parent.code')}</h1>
               <g:if test="${flash.message}">
                 <div class="message">${flash.message}</div>
               </g:if>
               <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
                   	        <g:sortableColumn property="code" title="${message(code: 'default.Code.label')}" />
                   	        <th><g:message code="default.Edit.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                      <% int j=0 %>
                      <g:each in="${accountHeadsInstanceList}" status="i" var="accountHeadsInstance">
                      <% j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                           <td>${j}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'name')}</td>
                           <td>${fieldValue(bean:accountHeadsInstance, field:'code')}</td>
                           <td>
                               <input type="hidden" name="id" value="${accountHeadsInstance?.parent?.id}" />
                               <g:link action="edit" id="${accountHeadsInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                      </g:each>
                    </tbody>
                </table>
               </div>
              </div>
            </td>
          </tr>
        </table>
      </div>
    </body>
</html>
