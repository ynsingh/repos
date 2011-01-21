

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantAgency.CreateGrantAgency.head"/></title>         
    </head>  
    <body>
      <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.GrantAgency.CreateGrantAgency.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table >
                        <tbody>
                        
                          <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameOfTheInstitution"><g:message code="default.Name.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'nameOfTheInstitution','errors')}">
                                    <input type="text" size="45" id="nameOfTheInstitution" name="nameOfTheInstitution" value="${fieldValue(bean:partyInstance,field:'nameOfTheInstitution')}"/>
                                </td>
                           </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Code.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:partyInstance,field:'code')}"/>
                                    <label for="code" style="color:blue;font-weight:bold"> <g:message code="default.grantAgency.code.label"/></label>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="default.Address.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'address','errors')}">
                                   <g:textArea name="address" value="${fieldValue(bean:partyInstance,field:'address')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone"><g:message code="default.Phone.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:partyInstance,field:'phone')}"/>
                                </td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="default.Email.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:partyInstance,field:'email')}"/>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateParty()"  /></span>
                </div>
            </g:form>
        </div>
        <div class="body">
            <h1><g:message code="default.GrantAgency.GrantAgencyList.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${partyInstanceList}">
              <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        <g:sortableColumn property="nameOfTheInstitution" title="${message(code: 'default.Name.label')}" />
                            <g:sortableColumn property="code" title="${message(code: 'default.Code.label')}" />
                            <g:sortableColumn property="address" title="${message(code: 'default.Address.label')}" />  
                            <g:sortableColumn property="phone" title="${message(code: 'default.Phone.label')}" />
                            <g:sortableColumn property="email" title="${message(code: 'default.Email.label')}" />
                            <th><g:message code="default.Edit.label"/></th>             	      
                        </tr>
                    </thead>
                    <tbody>
                      <% int j=0 %>
                      <g:each in="${partyInstanceList}" status="i" var="partyInstance">
                        <%  j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            
                           <td>${j}</td>
                           
                           <td>${fieldValue(bean:partyInstance, field:'nameOfTheInstitution')}</td>
                        
                           <td>${fieldValue(bean:partyInstance, field:'code')}</td>
                            
                           <td>${fieldValue(bean:partyInstance, field:'address')}</td>
                        
                           <td>${fieldValue(bean:partyInstance, field:'phone')}</td>
                            
                           <td>${fieldValue(bean:partyInstance, field:'email')}</td>
	                       
                           <td><g:link action="edit" id="${fieldValue(bean:partyInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                        
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
