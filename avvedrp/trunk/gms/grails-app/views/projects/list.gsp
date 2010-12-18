<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.projects.list.head"/></title>
    </head>
    <body>
    
    <div class="wrapper"> 
       
        <div class="body">
        
            
            <h1><g:message code="default.projects.list.head"/></h1>        
            <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
            </g:if>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${grantAllocationWithprojectsInstanceList}">
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                   	              
                       		<%-- <g:sortableColumn property="parent" title="Main Project" /> --%>
                                 
                       
                   	        <g:sortableColumn property="projects.name" title="${message(code: 'default.Name.label')}"/>
                                              
                   	        <g:sortableColumn property="granter.code" title="${message(code: 'default.Grantor.label')}"/>
                   	        
                   	        <th><g:message code="default.Investigator.label"/></th>
                   	          
                	        <th align="center"><img src="/gms/images/attach1.png"/></th>
                   	        <g:if test="${(session.Role == 'ROLE_SITEADMIN')}">   
                   	       		<th><g:message code="default.Edit.label"/></th>
                   	        </g:if>  
                        
                        </tr>
                    </thead>
                    <tbody>
                    <% int j=0 %>
                    <g:each in="${grantAllocationWithprojectsInstanceList}" status="i" var="grantAllocationInstance">
                   
                        <g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'Y'}">
	                        <%  j++ %>
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                       
	                        
	                           <td>${j}</td>
	                                
	                                 <%-- <td>${fieldValue(bean:grantAllocationInstance, field:'projects.parent.code')}</td>--%>
	                               
	                        
	                           
	                         <td><g:if test="${fieldValue(bean:grantAllocationInstance, field:'projects.activeYesNo') == 'Y'}">
	                         		<g:if test="${(grantAllocationInstance.projects.status == 'Closed')}">   
	                       				<g:link action="projectDash" controller='grantAllocation' id="${grantAllocationInstance.projects.id}" params="[projectStatus:'Closed']">
		                         			${grantAllocationInstance.projects.name} - ${fieldValue(bean:grantAllocationInstance, field:'projects.code')}
		                         		</g:link>
		                       		</g:if>
		                       		<g:else>
		                         		<g:link action="projectDash" controller='grantAllocation' id="${grantAllocationInstance.projects.id}">
		                         			${grantAllocationInstance.projects.name} - ${fieldValue(bean:grantAllocationInstance, field:'projects.code')}
		                         		</g:link>
		                         	</g:else>	
	                         	 </g:if>
	                        </td>
	                           
	                           	<g:if test="${grantAllocationInstance.granter}">
	                           		<td>${fieldValue(bean:grantAllocationInstance, field:'granter.code')}</td>
	                           	</g:if>
	                           	<g:else>
	                          		<td>Self</td>
	                           </g:else>
	                           <td><g:if test="${pIMapList[i]!=null}">
	                         
	                           ${pIMapList[i].investigator.name}
	                           </g:if>
	                           </td>                           
	                           <td><g:if test="${Attachments.findByDomainId(grantAllocationInstance.projects.id)}">
	                           <g:link action="list" controller="attachments" id="${grantAllocationInstance.projects.id}">
	                           <img src="/gms/images/attach1.png"/></g:link>
	                           </g:if>
	                           <g:else>
	                          
	                           </g:else>
	                           </td>
	                        
	                        <g:if test="${(session.Role == 'ROLE_SITEADMIN')}">   
	                        	<g:if test="${(grantAllocationInstance.projects.status == 'Closed')}">   
	                       			<td><g:message code="default.Closed.label"/></td>
	                       		</g:if>
	                       		<g:else>
	                       			<td><g:link action="edit" id="${grantAllocationInstance.projects.id}"><g:message code="default.Edit.label" /></g:link></td>
	                       		</g:else>
	                        </g:if>
	                        </tr>
	                        
	                       </g:if>
	                      
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
