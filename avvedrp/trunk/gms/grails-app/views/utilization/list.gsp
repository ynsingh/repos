<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.UtilizationCertificateList.head"/></title>
    </head>
    <body>
    <div class="wrapper"> 
		<div class="tablewrapper">
        	<div class="body">
            	<h1><g:message code="default.UtilizationCertificateList.head"/></h1>
           		<g:if test="${flash.message}">
            		<div class="message">${flash.message}</div>
            	</g:if>
            	<g:if test="${utilizationInstanceList}">
            		<div class="list">
                		<table>
                    		<thead>
                       	 		<tr>
                        			<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                   	        		<g:sortableColumn property="projects" title="${message(code: 'default.Project.label')}" />
                   	        		<g:sortableColumn property="grantee" title="${message(code: 'default.Grantee.label')}" />
                   	        		<g:sortableColumn property="startDate" title="${message(code: 'default.StartDate.label')}" />
                   	        		<g:sortableColumn property="endDate" title="${message(code: 'default.EndDate.label')}" />
                   	        		<g:sortableColumn property="submittedDate" title="${message(code: 'default.SubmittedDate.label')}" />
                   	                <th><g:message code="default.UtilizationCertificate.label"/></th>
                   	        		<th><g:message code="default.StatementOfAccounts.label"/></th>
            	                </tr>
                			</thead>
                    		<tbody>
                    			<g:each in="${utilizationInstanceList}" status="i" var="utilizationInstance">
                        			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
			                            <td>${i+1}</td>
			                        
			                            <td>${fieldValue(bean:utilizationInstance, field:'projects.name')}</td>
			                            <td>${fieldValue(bean:utilizationInstance, field:'grantee.nameOfTheInstitution')}</td>
			                            <td><g:formatDate format="dd/MM/yyyy" date="${utilizationInstance.startDate}"/></td>
			                            <td><g:formatDate format="dd/MM/yyyy" date="${utilizationInstance.endDate}"/></td>
			                        	<td><g:formatDate format="dd/MM/yyyy" date="${utilizationInstance.submittedDate}"/></td>
			                           	<td><g:link  controller='grantAllocation' action="utilizationCertificate"  
			                           			params="[id:utilizationInstance.id]" 
			                           			target="_blank"><g:message code="default.View.label"/>
		                           			</g:link>                           
	                           			</td>
										<td><g:link  controller='grantAllocation' action="showReports"  
												params="[id:utilizationInstance.id]" 
												target="_blank"><g:message code="default.View.label"/>
											</g:link>                           
										</td>
								
			                          	<%-- <td><a href="${g.createLink(controller:'utilization',action:'download',id:utilizationInstance.id}">
			                          				<g:message code="default.View.label"/></a></td>
			                        	--%>
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
        </div>
    </body>
</html>
