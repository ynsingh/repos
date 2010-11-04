

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Investigator.CreateInvestigator.head"/></title>         
    </head>
    <body>
      <div class="wrapper"> 
        <div class="body">
            <h1><g:message code="default.Investigator.CreateInvestigator.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${investigatorInstance}">
            <div class="errors">
                <g:renderErrors bean="${investigatorInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label"/></label>
                                </td>
                                
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'name','errors')}">
                                	<input type="text" id="name" name="name" value="${fieldValue(bean:investigatorInstance,field:'name')}">                        
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="designation"><g:message code="default.Designation.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'designation','errors')}">
                                    <input type="text" id="designation" name="designation" value="${fieldValue(bean:investigatorInstance,field:'designation')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party"><g:message code="default.Institution.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'party','errors')}">
                    				<strong>  ${fieldValue(bean:partyinstance,field:'code')} </strong>
                                     <input type="hidden" id="institution" name="institution" value="${fieldValue(bean:partyinstance,field:'id')}"/>
            						</td>
                            </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="department"><g:message code="default.Department.label"/></label>
                                   
                                </td>
                                 
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'department','errors')}">
                                  
                                    <g:select optionKey="id" optionValue="departmentCode" from="${departmentList}" name="department.id" value="${investigatorInstance?.department?.id}"></g:select>
                               		
                                </td>
                            </tr> 
                  			<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="default.Address.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'address','errors')}">
                                    <input type="text" id="address" name="address" value="${fieldValue(bean:investigatorInstance,field:'address')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="default.Email.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:investigatorInstance,field:'email')}"/>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="activeYesNo"><g:message code="default.Active.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:investigatorInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:investigatorInstance,field:'activeYesNo')}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validatePI()"/></span>
                </div>
            </g:form>
        </div>
          <div class="body">
            <h1></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${investigatorInstanceList}">
              <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                        
                   	        <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
                   	        
                   	        <g:sortableColumn property="designation" title="${message(code: 'default.Designation.label')}" />
                   	        
                   	        <th><g:message code="default.Department.label"/></th>
                   	    
                   	        <g:sortableColumn property="party" title="${message(code: 'default.Institution.label')}"/>
                        
                   	        <g:sortableColumn property="address" title="${message(code: 'default.Address.label')}" />
                                         
                   	        <g:sortableColumn property="email" title="${message(code: 'default.Email.label')}" />
                   	       
                   	        <g:sortableColumn property="activeYesNo" title="${message(code: 'default.Active.label')}" />
                   	       
                   	        <th><g:message code="default.Edit.label"/></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                     <% int j=0 %>
                     <g:each in="${investigatorInstanceList}" status="i" var="investigatorInstance">
                     <%  j++ %>
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${j}</td>
                        
                            <td>${fieldValue(bean:investigatorInstance, field:'name')}</td>
                            
                            <td>${fieldValue(bean:investigatorInstance, field:'designation')}</td>
                            
                            <td>${fieldValue(bean:investigatorInstance, field:'department.departmentCode')}</td>
                        
                            <td>${fieldValue(bean:investigatorInstance, field:'party.code')}</td>
                            
                            <td>${fieldValue(bean:investigatorInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:investigatorInstance, field:'email')}</td>
                            <td><g:if test="${fieldValue(bean:investigatorInstance, field:'activeYesNo') == 'Y'}">
    							  <g:message code="default.YES.label"/>
    							 </g:if>
    							 <g:else>
    							  <g:message code="default.NO.label"/>
    							 </g:else>
                           </td>
                            
                           <td><g:link action="edit" id="${fieldValue(bean:investigatorInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                        
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
