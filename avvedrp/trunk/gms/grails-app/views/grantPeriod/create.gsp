

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.GrantPeriod.CreateGrantPeriod.head"/></title>         
    </head>    
    <body>
        <div class="wrapper">
          <div class="body">
            <h1><g:message code="default.GrantPeriod.CreateGrantPeriod.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantPeriodInstance}">
              <div class="errors">
                <g:renderErrors bean="${grantPeriodInstance}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:grantPeriodInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate"><g:message code="default.StartDate.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'startDate','errors')}">
                                <calendar:datePicker name="startDate" defaultValue="${new Date()}" value="${grantPeriodInstance?.startDate}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                            
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate"><g:message code="default.EndDate.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'endDate','errors')}">
                                    <calendar:datePicker name="endDate" value="${grantPeriodInstance?.endDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="defaultYesNo"><g:message code="default.Default.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantPeriodInstance,field:'defaultYesNo','errors')}">
                                         <g:select name="defaultYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:grantPeriodInstance,field:'defaultYesNo')}" />
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateGrantPeriod()" /></span>
                </div>
            </g:form>
          </div>
        
        
          <div class="body">
            <h1><g:message code="default.GrantPeriod.GrantPeriodList.head/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${grantPeriodInstanceList}">
              <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                   	        <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
                        
                   	        <g:sortableColumn property="startDate" title="${message(code: 'default.StartDate.label')}" />
                   	         
                   	        <g:sortableColumn property="endDate" title="${message(code: 'default.EndDate.label')}" />

                            <g:sortableColumn property="defaultYesNo" title="${message(code: 'default.Default.label')}" />
                            
                   	        <th><g:message code="default.Edit.label"/></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                     <% int j=0 %>
                     <g:each in="${grantPeriodInstanceList}" status="i" var="grantPeriodInstance">
                       <%  j++ %>
                       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${j}</td>
                         
                            <td>${fieldValue(bean:grantPeriodInstance, field:'name')}</td>
                            
                            <td><g:formatDate format="dd/MM/yyyy" date="${grantPeriodInstance.startDate}"/></td>
                            
                            <td><g:formatDate format="dd/MM/yyyy" date="${grantPeriodInstance.endDate}"/></td>
                      	                         	
                            <td>
	                            <g:if test="${fieldValue(bean:grantPeriodInstance, field:'defaultYesNo') == 'Y'}">
	    						   <g:message code="default.YES.label"/>
	    					    </g:if>
	    					    <g:else>
	    						 <g:message code="default.NO.label"/>
	    					    </g:else>
                            </td>
                            <td><g:link action="edit" id="${fieldValue(bean:grantPeriodInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                        
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
