

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Utilization List</title>
    </head>
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
        </div>
        <div class="tablewrapper">
        <div class="body">
            <h1>Utilization Certificate List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${utilizationInstanceList}">
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <g:sortableColumn property="projects" title="Project" />
                   	        
                   	        <g:sortableColumn property="grantee" title="Grantee" />
                   	        
                   	        <g:sortableColumn property="submittedDate" title="Submitted Date" />
                   	                                
                   	        <th>Utilization Certificate</th>
                   	        
                   	        <th>Statement Of Accounts</th>
                	                   	                               
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${utilizationInstanceList}" status="i" var="utilizationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>${fieldValue(bean:utilizationInstance, field:'projects.name')}</td>
                        
                            <td>${fieldValue(bean:utilizationInstance, field:'grantee.nameOfTheInstitution')}</td>
                        
                             <td><g:formatDate format="dd/MM/yyyy" date="${utilizationInstance.submittedDate}"/></td>
                            
                            <td><g:jasperReport jasper="UtilizationCertificate" format="PDF" name="Utilization Certificate" >
										            <input type="hidden" name="id" value="${utilizationInstance.projects.id}" />
										            <input type="hidden" name="projectID" value="${utilizationInstance.projects.id}" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
									  	         </g:jasperReport></td>
							 <td><g:jasperReport jasper="StatementOFAccounts" format="PDF" name="Statement Of Accounts" >
										            <input type="hidden" name="id" value="${utilizationInstance.projects.id}" />
										            <input type="hidden" name="projectID" value="${utilizationInstance.projects.id}" />
										             <input type="hidden" name="Path" value="${application.getRealPath("reports")}" />
									  	         </g:jasperReport></td>						
                          <%--  <td><a href="${g.createLink(controller:'utilization',action:'download',id:utilizationInstance.id}">View</a></td>
                        
                           --%>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            </g:if>
            <g:else>
            <br>
            No Records Available</br>
            </g:else>
            
            </div>
            </div>
        </div>
    </body>
</html>
