
<div class="body">
<g:if test="${grantAllocationInstanceList}">
             <div class="list">
                  <table>
                    <thead>
  						<tr>
                        
                   	        <th><g:message code="default.SINo.label"/></th>
                       
                   	        <th><g:message code="default.Name.label"/></th>
                                              
                   	        <th><g:message code="default.Code.label"/></th>
                	       
                   	       	<th><g:message code="default.ProjectType.label"/></th>
                   	       	
                   	       	<th><g:message code="default.StartDate.label"/></th>
                   	       	 
                   	       	<th><g:message code="default.EndDate.label"/></th>
                   	       	
                   	       	<th><g:message code="default.Active.label"/></th>
                        
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
                        	 
                        	<td>${fieldValue(bean:projectsInstance, field:'projects.projectType.type')}</td>
                        	 
                        	<td><g:formatDate date="${projectsInstance.projects.projectStartDate}" format="dd/MM/yyyy"/></td>
                        	  
                        	<td><g:formatDate date="${projectsInstance.projects.projectEndDate}" format="dd/MM/yyyy"/></td>
                        	
                        	<td>
	                        <g:if test="${fieldValue(bean:projectsInstance, field:'projects.activeYesNo') == 'Y'}">
	    							 <g:message code="default.YES.label"/>
	    							 </g:if>
	    							 <g:else>
	    							 <g:message code="default.NO.label"/>
	    							 </g:else>
                       		</td>
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
        