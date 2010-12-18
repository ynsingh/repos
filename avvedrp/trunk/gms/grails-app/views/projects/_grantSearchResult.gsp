
<div class="body">
<g:if test="${grantAllocationInstanceList}">
             <div class="list">
                  <table>
                    <thead>
  						<tr>
                        
                   	        <th><g:message code="default.SINo.label"/></th>
                       
                   	        <th><g:message code="default.Name.label"/></th>
                                              
                   	        <th><g:message code="default.Code.label"/></th>
                	       
                   	       	<th><g:message code="default.GrantAgency.label"/></th>
                   	       	
                   	        <th><g:message code="default.Investigator.label"/></th>
                   	                         	       	
                   	       	<th><g:message code="default.DateOfAllocation.label"/></th>
                   	       	                    	       	                   	       	
                   	       	<th><g:message code="default.AmountAllocated.label"/></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="projectsInstance">
					 <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>
                            <g:if test="${(projectsInstance.projects.status == 'Closed')}">   
	                       				<g:link action="projectDash" controller='grantAllocation' id="${projectsInstance.projects.id}" params="[projectStatus:'Closed']">
		                         			${projectsInstance.projects.name} - ${fieldValue(bean:projectsInstance, field:'projects.code')}
		                         		</g:link>
		                       		</g:if>
		                       		<g:else>
		                         		<g:link action="projectDash" controller='grantAllocation' id="${projectsInstance.projects.id}">
		                         			${projectsInstance.projects.name} - ${fieldValue(bean:projectsInstance, field:'projects.code')}
		                         		</g:link>
		                         	</g:else>
                            
                            </td>
                        	 
                        	<td>${fieldValue(bean:projectsInstance, field:'projects.code')}</td>
                        	 
                        	<td>${fieldValue(bean:projectsInstance, field:'granter.nameOfTheInstitution')}</td>
                        	
                        	<td><%def projectsPIMap=ProjectsPIMap.find("from ProjectsPIMap PM where PM.projects.id="+projectsInstance.projects.id+"and PM.role='PI' and PM.activeYesNo='Y'")%>
                        	${projectsPIMap?.investigator?.name}
                        	</td>
                        	                        	 
                        	<td><g:formatDate date="${projectsInstance.DateOfAllocation}" format="dd/MM/yyyy"/></td>
                        	                          	                        	
                        	<td>${fieldValue(bean:projectsInstance, field:'amountAllocated')}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
           <div class="paginateButtons">
               
            </div> 
            </g:if>
        <g:else>
	    <g:message code="default.notfond.label"/>
	    </g:else>
        </div>
        