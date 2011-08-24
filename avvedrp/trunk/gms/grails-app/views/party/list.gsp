<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Institution.InstitutionList.head"/></title>
    </head>
    <body>
    <div class="wrapper"> 
        <div class="body">
            <h1><g:message code="default.Institution.InstitutionList.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
		            <g:if test="${partyInstanceList}">
			            <div class="list">
			                <table cellspacing="0" cellpadding="0">
			                    <thead>
			                        <tr>
			                    	    <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
			                            <g:sortableColumn property="nameOfTheInstitution" title="${message(code: 'default.Name.label')}" />
			                            <g:sortableColumn property="code" title="${message(code: 'default.Code.label')}" />
			                            <g:sortableColumn property="address" title="${message(code: 'default.Address.label')}"/>  
			                            <g:sortableColumn property="phone" title="${message(code: 'default.Phone.label')}" />
			                            <g:sortableColumn property="email" title="${message(code: 'default.Email.label')}" />
			                            <th><g:message code="default.Edit.label"/></th>
			                        </tr>
			                    </thead>
			                    <tbody>
				                    <g:if test="${personRoleInstance.authority == 'ROLE_SUPERADMIN'}"> 
				                        <g:each in="${activepartyInstanceList}" status="i" var="activepartyInstance">
				                        	<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					                            <td>${(i + 1)}</td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:activepartyInstance, field:'nameOfTheInstitution')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:activepartyInstance, field:'code')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:activepartyInstance, field:'address')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:activepartyInstance, field:'phone')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:activepartyInstance, field:'email')}</span></td>
					                            <td class="actionButtons"><span class="actionButton"><g:link action="edit" id="${fieldValue(bean:activepartyInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></span></td>
					                        
					                        </tr>
					                    </g:each>
					            </g:if> 
					            <g:else>   
			                      <g:each in="${partyInstanceList}" status="i" var="partyInstance">
			                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                             		<td>${(i + 1)}</td>
			 		                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:partyInstance, field:'nameOfTheInstitution')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:partyInstance, field:'code')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:partyInstance, field:'address')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:partyInstance, field:'phone')}</span></td>
					                            <td class="actionButtons"><span class="actionButton">${fieldValue(bean:partyInstance, field:'email')}</span></td>
					                            <td class="actionButtons"><span class="actionButton"><g:link action="edit" id="${fieldValue(bean:partyInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></span></td>
			                         </tr>
			                    </g:each>
					                </g:else>   
			                    </tbody>
			                </table>
			            </div>
		            </g:if>
		            <g:else>
			            <br>
			              <g:message code="default.NoRecordsAvailable.label"/>
			            </br>
		            </g:else>
             </div>
        </div>
    </body>
</html>
